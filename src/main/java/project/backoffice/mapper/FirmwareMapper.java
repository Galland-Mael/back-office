package project.backoffice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import project.backoffice.dto.FirmwareDTO;
import project.backoffice.dto.FirmwareVersionDTO;
import project.backoffice.dto.LibraryDTO;
import project.backoffice.dto.LightFirmwareDto;
import project.backoffice.entity.Firmware;
import project.backoffice.entity.Library;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface FirmwareMapper {
    FirmwareVersionDTO versionToDTO(Firmware firmware);

    @Mapping(source = "date", target = "date", qualifiedByName = "convertDateToString")
    FirmwareDTO toDTO(Firmware firmware);
    @Named("convertDateToString")
    static String convertDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }

}
