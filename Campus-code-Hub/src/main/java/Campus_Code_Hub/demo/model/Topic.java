package Campus_Code_Hub.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "topics")
@Data
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;   // Array, Time & Work, Percentage

    private int orderIndex;

    @ManyToOne
    @JoinColumn(name = "sheet_id")
    private Sheet sheet;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<SubTopic> subTopics;
}

