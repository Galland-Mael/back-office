package project.backoffice.dto;

import lombok.Data;
import project.backoffice.entity.Firmware;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Long firmwareId;
    private String firmwareVersion;
}
