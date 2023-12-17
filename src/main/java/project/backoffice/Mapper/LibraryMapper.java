package project.backoffice.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.backoffice.DTO.LibraryDTO;
import project.backoffice.Entity.Library;

@Mapper(componentModel = "spring",uses = {UserMapper.class})

public interface LibraryMapper {
    LibraryDTO toDTO(Library library);
}
