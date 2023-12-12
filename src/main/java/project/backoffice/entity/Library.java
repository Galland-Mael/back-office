package project.backoffice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String Json;
}
