package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.QuestionResponse;
import Campus_Code_Hub.demo.dto.SheetResponse;
import Campus_Code_Hub.demo.dto.SubTopicResponse;
import Campus_Code_Hub.demo.dto.TopicResponse;
import Campus_Code_Hub.demo.model.*;
import Campus_Code_Hub.demo.repository.SheetRepository;
import Campus_Code_Hub.demo.repository.StudentRepository;
import Campus_Code_Hub.demo.repository.UserQuestionProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SheetService {

    private final StudentRepository studentRepository;
    private final SheetRepository sheetRepository;
    private final UserQuestionProgressRepository questionProgressRepository;

    public SheetResponse getSheet(SheetType sheetType, String email) {

        // ✅ FIX 1: unwrap Optional
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Sheet sheet = sheetRepository.findBySheetType(sheetType)
                .orElseThrow(() -> new RuntimeException("Sheet not found"));

        SheetResponse sheetDto = new SheetResponse();
        sheetDto.setId(sheet.getId());
        sheetDto.setSheetType(sheet.getSheetType().name());
        sheetDto.setTitle(sheet.getTitle());

        List<TopicResponse> topics = sheet.getTopics().stream()
                .sorted(Comparator.comparingInt(Topic::getOrderIndex))
                .map(topic -> {
                    TopicResponse topicDto = new TopicResponse();
                    topicDto.setId(topic.getId());
                    topicDto.setName(topic.getName());
                    topicDto.setOrderIndex(topic.getOrderIndex());

                    List<SubTopicResponse> subTopics = topic.getSubTopics().stream()
                            .map(subTopic -> {
                                SubTopicResponse subDto = new SubTopicResponse();
                                subDto.setId(subTopic.getId());
                                subDto.setName(subTopic.getName());
                                subDto.setYoutubeLink(subTopic.getYoutubeLink());

                                List<QuestionResponse> questions = subTopic.getQuestions().stream()
                                        .map(q -> {
                                            QuestionResponse qr = new QuestionResponse();
                                            qr.setId(q.getId());
                                            qr.setTitle(q.getTitle());
                                            qr.setDifficulty(q.getDifficulty());
                                            qr.setProblemLink(q.getProblemLink());
                                            qr.setVideoLink(q.getVideoLink());
                                            qr.setCorrectOption(q.getCorrectOption());
                                            qr.setOptionA(q.getOptionA());
                                            qr.setOptionB(q.getOptionB());
                                            qr.setOptionC(q.getOptionC());
                                            qr.setOptionD(q.getOptionD());
                                            qr.setSheetType(QuestionType.APTITUDE);
                                            // ✅ FIX 2: use student.getId()
                                            boolean solved = questionProgressRepository
                                                    .findByUserIdAndQuestionId
                                                            (student.getId(), q.getId())
                                                    .map(UserQuestionProgress::isSolved)
                                                    .orElse(false);

                                            qr.setSolved(solved);

                                            return qr;
                                        })
                                        .toList();

                                subDto.setQuestions(questions);
                                return subDto;
                            }).toList();

                    topicDto.setSubTopics(subTopics);
                    return topicDto;
                }).toList();

        sheetDto.setTopics(topics);
        return sheetDto;
    }

}
