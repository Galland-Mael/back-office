package project.backoffice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "firmware_sequence", sequenceName = "firmware_sequence", allocationSize = 1)
public class Firmware {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "firmware_sequence")
    private Long id;
    private Date date;
    private String version;
    @Column(name="file_path")
    private String filePath;
    @OneToOne(mappedBy = "firmware")
    @JsonIgnore
    private Product product;

}
