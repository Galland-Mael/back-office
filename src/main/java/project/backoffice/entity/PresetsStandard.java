package project.backoffice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "presets_standard")
@SequenceGenerator(name = "presets_standard_sequence", sequenceName = "presets_standard_sequence", allocationSize = 1)
public class PresetsStandard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presets_standard_sequence")
    private Long id;
    private String type;
    private String json;
}
