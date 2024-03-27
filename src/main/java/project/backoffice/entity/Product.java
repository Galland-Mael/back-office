package project.backoffice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "firmware_id", referencedColumnName = "id")
    private Firmware firmware;
}
