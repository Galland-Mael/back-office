package project.backoffice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String email;
    private String password;
    @Column(name="facebook_id")
    private String facebookId;
    private String phone;
    private String token;
    @Column(name="library_timestamp")
    private Date libraryTimestamp;
    private Date timestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="quality_id")
    private Quality quality;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", referencedColumnName = "id")
    private Library library;
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shared> shareds;
    @Enumerated(EnumType.STRING)
    private Role role;
}
