package no.pk.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

class TwilioSMS {
    // Find your Account Sid and Token at twilio.com/user/account
    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void SendSMS(String melding) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(System.getenv("TLF_TIL")),
                new PhoneNumber(System.getenv("TLF_FRA")), melding).create();
        System.out.println(message.getStatus());
    }


}
