package project.backoffice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shared {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String presets;
}
