package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.CreateAptitudeQuestionRequest;
import Campus_Code_Hub.demo.dto.CreateCodingQuestionRequest;
import Campus_Code_Hub.demo.model.Question;
import Campus_Code_Hub.demo.model.QuestionType;
import Campus_Code_Hub.demo.model.SubTopic;
import Campus_Code_Hub.demo.repository.QuestionRepository;
import Campus_Code_Hub.demo.repository.SubTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/questions")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class QuestionAdminController {

    private final SubTopicRepository subTopicRepository;
    private final QuestionRepository questionRepository;

    @PostMapping("/coding")
    public Question createCodingQuestion(@RequestBody CreateCodingQuestionRequest request) {

        SubTopic subTopic = subTopicRepository.findById(request.getSubTopicId())
                .orElseThrow(() -> new RuntimeException("SubTopic not found"));

        Question question = new Question();
        question.setTitle(request.getTitle());
        question.setDifficulty(request.getDifficulty());
        question.setType(QuestionType.CODING);
        question.setProblemLink(request.getProblemLink());
        question.setVideoLink(request.getVideoLink());
        question.setSubTopic(subTopic);

        return questionRepository.save(question);
    }

    @PostMapping("/aptitude")
    public Question createAptitudeQuestion(@RequestBody CreateAptitudeQuestionRequest request) {

        SubTopic subTopic = subTopicRepository.findById(request.getSubTopicId())
                .orElseThrow(() -> new RuntimeException("SubTopic not found"));

        Question question = new Question();
        question.setTitle(request.getTitle());
        question.setDifficulty(request.getDifficulty());
        question.setType(QuestionType.APTITUDE);

        question.setOptionA(request.getOptionA());
        question.setOptionB(request.getOptionB());
        question.setOptionC(request.getOptionC());
        question.setOptionD(request.getOptionD());
        question.setCorrectOption(request.getCorrectOption());

        question.setExplanation(request.getExplanation());
        question.setVideoLink(request.getVideoLink());
        question.setSubTopic(subTopic);

        return questionRepository.save(question);
    }

}

