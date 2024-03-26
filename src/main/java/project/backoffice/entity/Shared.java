package project.backoffice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@SequenceGenerator(name = "shared_sequence", sequenceName = "shared_sequence", allocationSize = 1)
public class Shared {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shared_sequence")
    private Long id;
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;
    private String presets;
}
