package Campus_Code_Hub.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "question_id"}
        )
)
@Data
public class UserQuestionProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student user;

    @ManyToOne
    private Question question;

    private boolean solved;
}

