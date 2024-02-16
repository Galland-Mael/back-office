package project.backoffice.dto;

import lombok.Data;

@Data
public class UserAuthDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private QualityDTO quality;
    private String token;
}
