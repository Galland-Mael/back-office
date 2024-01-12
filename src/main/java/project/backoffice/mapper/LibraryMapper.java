package project.backoffice.mapper;

import org.mapstruct.Mapper;
import project.backoffice.dto.LibraryDTO;
import project.backoffice.entity.Library;

@Mapper(componentModel = "spring",uses = {UserMapper.class})

public interface LibraryMapper {
    LibraryDTO toDTO(Library library);
}
