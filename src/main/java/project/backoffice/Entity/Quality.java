package project.backoffice.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quality
{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy="quality", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;
}
