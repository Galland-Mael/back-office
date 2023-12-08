package project.backoffice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "facebook_id")
    private String facebookId;
    private String password;
    private String phone;
    @ManyToOne
    @JoinColumn(name="quality_id")
    private Quality quality;
    private String token;
    private int admin;
    @Column(name = "library_timestamp")
    private Timestamp libraryTimestamp;
    private Timestamp timestamp;
    @Column(name = "is_active")
    private boolean isActive;
    @OneToMany(mappedBy = "user")
    private List<Shared> sharedList;
}
