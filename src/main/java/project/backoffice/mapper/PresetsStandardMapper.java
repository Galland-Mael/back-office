package project.backoffice.mapper;

import org.mapstruct.Mapper;
import project.backoffice.dto.PresetsStandardDTO;
import project.backoffice.entity.PresetsStandard;

@Mapper(componentModel = "spring")
public interface PresetsStandardMapper {
    PresetsStandardDTO toDTO(PresetsStandard presets);
}

