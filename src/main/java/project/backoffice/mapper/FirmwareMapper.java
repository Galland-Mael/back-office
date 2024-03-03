package project.backoffice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import project.backoffice.dto.*;
import project.backoffice.entity.Firmware;
import project.backoffice.helper.DateHelper;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface FirmwareMapper {
    FirmwareVersionDTO versionToDTO(Firmware firmware);

    @Mapping(source = "date", target = "date", qualifiedByName = "convertDateToString")
    FirmwareDTO toDTO(Firmware firmware);

    @Mapping(source = "date", target = "date", qualifiedByName = "convertDateToString")
    LightFirmwareDto toLightDTO(Firmware firmware);

    @Named("convertDateToString")
    static String convertDateToString(Date date) {
        return DateHelper.convertDateToString(date);
    }

    Firmware toEntity(FirmwareCreationDTO firmwareDTO);
}
