package Campus_Code_Hub.demo.model;

import jakarta.persistence.*;


@Entity
public class SubTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            // Average, Time & Work
    private String youtubeLink;     // Learn concept video

    @ManyToOne
    private Topic topic;

    @OneToMany(mappedBy = "subTopic")
    private List<Question> questions;
}

