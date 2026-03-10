package Campus_Code_Hub.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class CareerResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    // "HTML Basics", "React Fundamentals"

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "youtube_link")
    private String youtubeLink;

    private int orderIndex; // roadmap order

    @ManyToOne
    @JsonIgnore
    private CareerTrack careerTrack;
}
