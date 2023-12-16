package project.backoffice.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import project.backoffice.DTO.UserDTO;
import project.backoffice.Entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapperInterface {
    UserMapperInterface INSTANCE = Mappers.getMapper(UserMapperInterface.class);

    UserDTO mapToUserDTO(User user);
    default Page<UserDTO> mapToUserDTOPage(Page<User> userPage) {
        List<UserDTO> userDtoList = userPage.getContent().stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(userDtoList, userPage.getPageable(), userPage.getTotalElements());
    }
}
