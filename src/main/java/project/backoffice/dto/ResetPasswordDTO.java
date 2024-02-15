package project.backoffice.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String email;
    private String password;
    private String token;
}
