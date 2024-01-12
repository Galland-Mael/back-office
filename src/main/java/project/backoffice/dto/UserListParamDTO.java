package project.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserListParamDTO {
    Integer page;
    String sortBy;
    Integer sortOrder;
    Integer numberOfElements;
    String searchTerm;
}
