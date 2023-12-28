package project.backoffice.Mapper;

import org.mapstruct.Mapper;
import project.backoffice.DTO.PresetsStandardDTO;
import project.backoffice.Entity.PresetsStandard;

@Mapper(componentModel = "spring")
public interface PresetsStandardMapper {
    PresetsStandardDTO toDTO(PresetsStandard presets);
}

