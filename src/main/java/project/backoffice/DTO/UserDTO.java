package project.backoffice.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
