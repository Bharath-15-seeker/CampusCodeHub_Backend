package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.CareerTrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerTrackRepository
        extends JpaRepository<CareerTrack, Long> {
}
