package Campus_Code_Hub.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String difficulty;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Column(nullable = true)
    private String problemLink;

    @Column(nullable = true)
    private String VideoLink;

    @Column(nullable = true)
    private String optionA;
    @Column(nullable = true)
    private String optionB;
    @Column(nullable = true)
    private String optionC;
    @Column(nullable = true)
    private String optionD;

    @Column(nullable = true)
    private String correctOption;


    @Column(columnDefinition = "TEXT",nullable = true)
    private String explanation;     

    @ManyToOne
    @JsonIgnore
    private SubTopic subTopic;
}
