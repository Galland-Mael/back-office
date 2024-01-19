package project.backoffice.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import project.backoffice.dto.UserDTO;
import project.backoffice.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {LibraryMapper.class})
public interface UserMapper {
    UserDTO toDTO(User user);

    default Page<UserDTO> toDTOPage(Page<User> userPage) {
        List<UserDTO> userDtoList = userPage.getContent().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(userDtoList, userPage.getPageable(), userPage.getTotalElements());
    }
}
