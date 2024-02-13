package project.backoffice.mapper;

import org.mapstruct.Mapper;
import project.backoffice.dto.FirmwareVersionDTO;
import project.backoffice.entity.Firmware;

@Mapper(componentModel = "spring")
public interface FirmwareMapper {
    FirmwareVersionDTO versionToDTO(Firmware firmware);
}
