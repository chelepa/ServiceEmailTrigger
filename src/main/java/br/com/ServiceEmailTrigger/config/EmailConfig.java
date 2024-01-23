package br.com.ServiceEmailTrigger.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EmailConfig {

    private final String emailFrom;

    public EmailConfig(@Value("${spring.mail.username}") String emailFrom){

        this.emailFrom = emailFrom;
    }
}
