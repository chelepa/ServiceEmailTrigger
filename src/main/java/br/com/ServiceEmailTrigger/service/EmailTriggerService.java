package br.com.ServiceEmailTrigger.service;

import br.com.ServiceEmailTrigger.dto.EmailResetPasswordRequestDTO;
import br.com.ServiceEmailTrigger.dto.EmailResetPasswordResponseDTO;
import jakarta.mail.MessagingException;

public interface EmailTriggerService {

    EmailResetPasswordResponseDTO sendEmailResetPassword(EmailResetPasswordRequestDTO request) throws MessagingException;
}
