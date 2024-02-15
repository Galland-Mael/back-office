package project.backoffice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResetPasswordRequestDTO {
    private UserDTO user;
    private String code;
    private LocalDateTime expirationDate;
}
