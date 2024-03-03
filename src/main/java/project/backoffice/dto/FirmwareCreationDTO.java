package project.backoffice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FirmwareCreationDTO {

    @Getter
    @Setter
    private String date;

    @Getter
    @Setter
    private String version;

    @Setter
    @Getter
    private MultipartFile file;

    @Getter
    @Setter
    private Integer productId;

}
