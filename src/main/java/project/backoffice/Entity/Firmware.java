package project.backoffice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Firmware {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private String version;
    @Column(name="file_path")
    private String filePath;
    @OneToOne(mappedBy = "firmware")
    private Product product;

}
