package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.QuestionResponse;
import Campus_Code_Hub.demo.dto.SheetResponse;
import Campus_Code_Hub.demo.dto.SubTopicResponse;
import Campus_Code_Hub.demo.dto.TopicResponse;
import Campus_Code_Hub.demo.model.Sheet;
import Campus_Code_Hub.demo.model.SheetType;
import Campus_Code_Hub.demo.model.Topic;
import Campus_Code_Hub.demo.repository.SheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SheetService {

    private final SheetRepository sheetRepository;

    public SheetResponse getSheet(SheetType sheetType) {

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

                                            qr.setOptionA(q.getOptionA());
                                            qr.setOptionB(q.getOptionB());
                                            qr.setOptionC(q.getOptionC());
                                            qr.setOptionD(q.getOptionD());
                                            return qr;
                                        }).toList();

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
