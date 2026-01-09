package Campus_Code_Hub.demo.model;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---------------------------
       AUTH DETAILS
    --------------------------- */

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    /* ---------------------------
       BASIC PROFILE
    --------------------------- */

    @Column(nullable = false)
    private String name;

    private String department;
    private int year;
    private String registerNumber;


    /* ---------------------------
       CODING PLATFORM PROFILE
    --------------------------- */


    // LEETCODE / GEEKSFORGEEKS

    private String codingProfileUrl;


    /* ---------------------------
       POINTS SYSTEM
    --------------------------- */

    @Column(name = "coding_points", nullable = false)
    private int codingPoints = 0;

    @Column(name = "aptitude_points", nullable = false)
    private int aptitudePoints = 0;


    /* ---------------------------
       AUDIT FIELDS
    --------------------------- */

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}


