package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.LeaderboardResponse;
import Campus_Code_Hub.demo.model.EventType;
import Campus_Code_Hub.demo.repository.EventRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final EventRegistrationRepository registrationRepository;

    @Transactional(readOnly = true)
    public List<LeaderboardResponse> getCodingLeaderboard() {
        return registrationRepository.getLeaderboardByType(EventType.CODING);
    }

    @Transactional(readOnly = true)
    public List<LeaderboardResponse> getAptitudeLeaderboard() {
        return registrationRepository.getLeaderboardByType(EventType.APTITUDE);
    }
}
