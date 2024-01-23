package br.com.ServiceEmailTrigger.controller;

import br.com.ServiceEmailTrigger.dto.EmailResetPasswordRequestDTO;
import br.com.ServiceEmailTrigger.dto.EmailResetPasswordResponseDTO;
import br.com.ServiceEmailTrigger.service.EmailTriggerService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTriggerController {

    @Autowired
    private EmailTriggerService service;

    @PostMapping(value = "/v1/email/reset/password")
    public ResponseEntity<EmailResetPasswordResponseDTO> sendEmail(@RequestBody EmailResetPasswordRequestDTO request) throws MessagingException {
        return ResponseEntity.status(HttpStatus.OK).body(service.sendEmailResetPassword(request));
    }

}
