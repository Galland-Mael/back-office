package project.backoffice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FirmwareDTO {
    private Long id;
    private String date;
    private String version;
    private String content;
}

