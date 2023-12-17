package project.backoffice.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.backoffice.DTO.UserDTO;
import project.backoffice.Entity.User;

@Mapper(componentModel = "spring", uses = {LibraryMapper.class})
public interface UserMapper {
    UserDTO toDTO(User user);
}
