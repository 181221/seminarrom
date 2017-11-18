package no.pk.bot;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class EventTypeTest {

    /**
     * Number of events in {@link EventType} should be
     * equal to the number of events supported by bot
     * plus 3 added by JBot.
     */
    @Test
    public void numberOfEventsType() {
        EventType[] events = EventType.values();
        assertEquals(events.length, 71);
    }

}