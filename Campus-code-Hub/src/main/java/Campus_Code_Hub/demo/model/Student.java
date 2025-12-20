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

    // Email is your login username
    @Column(nullable = false, unique = true)
    private String email;

    // BCrypt hashed password
    @Column(nullable = false)
    private String password;

    // Basic student profile fields
    @Column(nullable = false)
    private String name;

    private String department;
    private int year;  // 1,2,3,4
    private String registerNumber;

    // Role (ADMIN / STUDENT)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    /* ---------------------------
       STREAK SYSTEM
    --------------------------- */

    // Current consecutive streak days
    @Column(nullable = false)
    private int currentStreak = 0;

    // Highest streak ever
    @Column(nullable = false)
    private int highestStreak = 0;

    // Last date they logged in or performed streak action
    private LocalDateTime lastStreakUpdate;


    /* ---------------------------
       POINTS FOR LEADERBOARD
    --------------------------- */

    @Column(nullable = false)
    private int points = 0;


    /* ---------------------------
       EVENT ENROLLMENT MAPPING
    --------------------------- */
    //@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    //private List<EventEnrollment> enrollments;


    /* ---------------------------
       AUDIT FIELDS
    --------------------------- */
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    /* ---------------------------
       JPA LIFECYCLE HOOKS
    --------------------------- */
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

