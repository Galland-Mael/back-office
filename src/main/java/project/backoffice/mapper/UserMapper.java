package project.backoffice.mapper;

import org.mapstruct.Mapper;
import project.backoffice.dto.UserDTO;
import project.backoffice.entity.User;

@Mapper(componentModel = "spring", uses = {LibraryMapper.class})
public interface UserMapper {
    UserDTO toDTO(User user);
}
