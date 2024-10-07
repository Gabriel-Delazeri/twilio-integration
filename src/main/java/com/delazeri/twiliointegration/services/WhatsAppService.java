package com.delazeri.twiliointegration.services;

import com.delazeri.twiliointegration.configuration.TwilioConfiguration;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class WhatsAppService {
    private final TwilioConfiguration twilioConfiguration;

    private String generateValidationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    public void sendWhatsAppValidationCode(String toNumber) {
        Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());

        String validationCode = generateValidationCode();

        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + toNumber),
                new PhoneNumber(twilioConfiguration.getWhatsappFrom()),
                "Your validation code is: *%s*".formatted(validationCode)
        ).create();

        System.out.println("Send message: " + message.getSid());

    }
}
