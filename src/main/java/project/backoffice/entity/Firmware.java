package project.backoffice.entity;


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
    private String dataFilePath;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}
