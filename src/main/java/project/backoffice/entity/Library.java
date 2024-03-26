package project.backoffice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "library_sequence", sequenceName = "library_sequence", allocationSize = 1)

public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "library_sequence")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String Json;
}
