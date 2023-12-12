package project.backoffice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
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
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String email;
    private String password;
    private String facebook_id;
    private String phone;
    private String token;
    private int admin;
    @Column(name="library_timestamp")
    private Date libraryTimestamp;
    private Date timestamp;
    private Date created;
    @ManyToOne
    @JoinColumn(name="quality_id")
    private Quality quality;
    @OneToMany(mappedBy="user")
    private List<Library> libraries;
    @OneToMany(mappedBy="user")
    private List<Shared> shareds;
}
