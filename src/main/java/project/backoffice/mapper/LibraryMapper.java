package project.backoffice.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mapstruct.*;
import project.backoffice.dto.LibraryDTO;
import project.backoffice.entity.Library;
import project.backoffice.enumeration.JsonTypeEnum;
import project.backoffice.helper.JsonHelper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface LibraryMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "json", target = "library", qualifiedByName = "convertStringToJson")
    LibraryDTO toDTO(Library library);

    @Mapping(source = "library", target = "json", qualifiedByName = "convertJsonToString")
    Library toEntity(LibraryDTO libraryDTO);

    @Named("convertStringToJson")
    static Object convertStringToJson(String library) throws JsonProcessingException {
        return JsonHelper.getJsonStringAsObject(library, JsonTypeEnum.LIBRARY);
    }

    @Named("convertJsonToString")
    static String convertJsonToString(Object library) throws JsonProcessingException {
        return JsonHelper.JsonToString(library);
    }
}
