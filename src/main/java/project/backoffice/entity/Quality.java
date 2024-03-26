package project.backoffice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@SequenceGenerator(name = "quality_sequence", sequenceName = "quality_sequence", allocationSize = 1)
public class Quality
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quality_sequence")
    private Long id;
    private String name;
    @OneToMany(mappedBy="quality", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> users;
}
