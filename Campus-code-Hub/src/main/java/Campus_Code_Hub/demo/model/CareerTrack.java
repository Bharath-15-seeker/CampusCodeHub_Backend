package Campus_Code_Hub.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CareerTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    // MERN Stack, Java Full Stack, Data Analyst

    @Column(columnDefinition = "TEXT")
    private String description;

    private boolean active = true;

    @OneToMany(
            mappedBy = "careerTrack",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<CareerResource> resources;
}
