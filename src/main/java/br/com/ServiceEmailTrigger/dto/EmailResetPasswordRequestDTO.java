package br.com.ServiceEmailTrigger.dto;

import lombok.Data;

@Data
public class EmailResetPasswordRequestDTO {

    private String email;
    private String name;
    private String url;
}
