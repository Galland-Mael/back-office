package project.backoffice.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.backoffice.DTO.LibraryDTO;
import project.backoffice.Entity.Library;

@Mapper(componentModel = "spring", uses = {LibraryMapper.class})

public interface LibraryMapper {
    LibraryMapper INSTANCE = Mappers.getMapper(LibraryMapper.class);

    LibraryDTO toDTO(Library library);
}
