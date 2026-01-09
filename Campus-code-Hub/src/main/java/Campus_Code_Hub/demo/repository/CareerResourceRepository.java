package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.CareerResource;
import Campus_Code_Hub.demo.model.CareerTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerResourceRepository
        extends JpaRepository<CareerResource, Long> {

    List<CareerResource> findByCareerTrackOrderByOrderIndexAsc(
            CareerTrack careerTrack
    );
}

