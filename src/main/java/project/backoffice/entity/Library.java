package project.backoffice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String Json;
}
