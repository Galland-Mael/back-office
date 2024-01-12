package project.backoffice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "presets_standard")
public class PresetsStandard {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String json;
}
