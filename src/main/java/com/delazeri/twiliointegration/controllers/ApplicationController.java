package com.delazeri.twiliointegration.controllers;

import com.delazeri.twiliointegration.services.WhatsAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ApplicationController {
    private final WhatsAppService whatsAppService;

    @GetMapping(value = "{toNumber}")
    public ResponseEntity<?> postMessage(
            @PathVariable final String toNumber
    ) {
        this.whatsAppService.sendWhatsAppValidationCode(toNumber);

        return ResponseEntity.ok().build();
    }
}
