package project.backoffice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private User user;
    private String Json;
}
