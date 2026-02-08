package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.SheetProgressDTO;
import Campus_Code_Hub.demo.model.Question;
import Campus_Code_Hub.demo.model.SheetType;
import Campus_Code_Hub.demo.repository.QuestionRepository;
import Campus_Code_Hub.demo.repository.UserQuestionProgressRepository;
import Campus_Code_Hub.demo.repository.UserQuestionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SheetProgressService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserQuestionProgressRepository statusRepository;

    public SheetProgressDTO getProgress(Long userId, SheetType sheetType) {

        long total = questionRepository.countBySheetType(sheetType);

        List<Long> questionIds = questionRepository
                .findBySheetType(sheetType)
                .stream()
                .map(Question::getId)
                .toList();

        long solved = questionIds.isEmpty() ? 0 :
                statusRepository.countByUserIdAndSolvedTrueAndQuestionIdIn(
                        userId, questionIds
                );

        int percentage = total == 0 ? 0 : (int) ((solved * 100) / total);

        SheetProgressDTO dto = new SheetProgressDTO();
        dto.setSheetType(sheetType.name());
        dto.setTotalQuestions(total);
        dto.setSolvedQuestions(solved);
        dto.setProgressPercentage(percentage);

        return dto;
    }
}
