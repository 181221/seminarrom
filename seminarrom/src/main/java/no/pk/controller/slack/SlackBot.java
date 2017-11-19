package no.pk.controller.slack;

import no.pk.bot.Bot;
import no.pk.bot.Controller;
import no.pk.bot.EventType;
import no.pk.bot.models.Event;
import no.pk.bot.models.Message;
import no.pk.controller.Scraper;
import no.pk.controller.attributter.Lenker;
import no.pk.controller.scraper.App;
import no.pk.model.Rom;
import no.pk.util.RomUtil;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import static no.pk.util.CsvReaderUtil.readCSVInternett;

/**
 * A Slack Bot sample. You can create multiple bots by just
 * extending {@link Bot} class like this one.
 *
 * @author ramswaroop
 * @version 1.0.0, 05/06/2016
 */
@Component
public class SlackBot extends Bot {

    private static final Logger logger = LoggerFactory.getLogger(SlackBot.class);

    /**
     * Slack token from application.properties file. You can get your bot token
     * next <a href="https://my.slack.com/services/new/bot">creating a new bot</a>.
     */
    @Value("${slackBotToken}")
    private String slackToken;

    @Override
    public String getSlackToken() {
        return slackToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }

    /**
     * Invoked when the bot receives a direct mention (@botname: message)
     * or a direct message. NOTE: These two event types are added by jbot
     * to make your task easier, Slack doesn't have any direct way to
     * determine these type of events.
     *
     * @param session
     * @param event
     */
    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE})
    public void onReceiveDM(WebSocketSession session, Event event) {
        reply(session, event, new Message("Hei, jeg heter " + slackService.getCurrentUser().getName() + ", hva kan jeg hjelpe deg med?"));
    }

    /**
     * Invoked when bot receives an event of type message with text satisfying
     * the pattern {@code ([a-z ]{2})(\d+)([a-z ]{2})}. For example,
     * messages like "ab12xy" or "ab2bc" etc will invoke this method.
     *
     * @param session
     * @param event
     */
    @Controller(events = EventType.MESSAGE, pattern = "^([a-z ]{2})(\\d+)([a-z ]{2})$")
    public void onReceiveMessage(WebSocketSession session, Event event, Matcher matcher) {
        reply(session, event, new Message("First group: " + matcher.group(0) + "\n" +
                "Second group: " + matcher.group(1) + "\n" +
                "Third group: " + matcher.group(2) + "\n" +
                "Fourth group: " + matcher.group(3)));
    }

    /**
     * Invoked when an item is pinned in the channel.
     *
     * @param session
     * @param event
     */
    @Controller(events = EventType.PIN_ADDED)
    public void onPinAdded(WebSocketSession session, Event event) {
        reply(session, event, new Message("Takk for at du merket innlegget, du finner alle merkede innlegg under detaljer på channelen."));
    }

    /**
     * Invoked when bot receives an event of type file shared.
     * NOTE: You can't reply to this event as bot doesn't send
     * a channel id for this event type. You can learn more about
     * <a href="https://api.slack.com/events/file_shared">file_shared</a>
     * event from Slack's Api documentation.
     *
     * @param session
     * @param event
     */
    @Controller(events = EventType.FILE_SHARED)
    public void onFileShared(WebSocketSession session, Event event) {
        logger.info("Fil delt: {}", event);
    }

    /**
     * Starter samtalen med Slack boten
     *
     * @param session
     * @param event
     */
    @Controller(pattern = "(finn seminar)", next = "finnDag")
    public void settOppLedigeSeminarRom(WebSocketSession session, Event event) {
        startConversation(event, "finnDag");
        reply(session, event, new Message("Hallaien, hvilken dag ønsker du å finne ledig rom på?"));
    }

    @Controller()
    public void finnDag(WebSocketSession session, Event event) throws IOException {
        if (event.getText().contains("idag")) {
            reply(session, event, new Message("Bare ett øyeblikk så skal jeg sjekke!"));
            String melding = App.hentDagensSeminarogAuditorieRom();
            App.sendViaPushbullet(melding);
            reply(session, event, new Message(melding));
        } else {
            reply(session, event, new Message("Beklager, @Peder har ikke implementert søk for andre dager enda.."));
        }
        stopConversation(event);
    }
}