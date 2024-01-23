package br.com.ServiceEmailTrigger.service;

import br.com.ServiceEmailTrigger.config.EmailConfig;
import br.com.ServiceEmailTrigger.dto.EmailResetPasswordRequestDTO;
import br.com.ServiceEmailTrigger.dto.EmailResetPasswordResponseDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class EmailTriggerServiceImpl implements EmailTriggerService{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private EmailConfig EmailConfig;

    @Override
    public EmailResetPasswordResponseDTO sendEmailResetPassword(EmailResetPasswordRequestDTO request) throws MessagingException {
        log.info("EmailTriggerServiceImpl.SendEmailResetPassword - Start - EmailRequestDTO: [{}]", request);
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);

        helper.setSubject("Redefinicão de Credenciais");
        helper.setText(getTemplateEmail(request.getName(), request.getUrl(), request.getEmail()), true);
        helper.setTo(request.getEmail());
        helper.setFrom(EmailConfig.getEmailFrom());

        mailSender.send(msg);

        log.info("EmailTriggerServiceImpl.SendEmailResetPassword - End - ");
        return new EmailResetPasswordResponseDTO("Email Enviado Com Sucesso");
    }

    @SneakyThrows
    private String getTemplateEmail(String user, String url_token, String emailTo) {
        Map<String, String> response = new HashMap<>();
        response.put("name", user);
        response.put("email", emailTo);
        response.put("url", url_token);
        var template = freeMarkerConfigurer.getConfiguration().getTemplate("/email/EmailResetPassword.html");
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, response);
    }
}
