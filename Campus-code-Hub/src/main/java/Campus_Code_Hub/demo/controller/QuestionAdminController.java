package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.CreateAptitudeQuestionRequest;
import Campus_Code_Hub.demo.dto.CreateCodingQuestionRequest;
import Campus_Code_Hub.demo.dto.UpdateQuestionRequest;
import Campus_Code_Hub.demo.model.Question;
import Campus_Code_Hub.demo.model.QuestionType;
import Campus_Code_Hub.demo.model.SubTopic;
import Campus_Code_Hub.demo.repository.QuestionRepository;
import Campus_Code_Hub.demo.repository.SubTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{questionId}")
    public Question updateQuestion(
            @PathVariable Long questionId,
            @RequestBody UpdateQuestionRequest request
    ) {
        Question q = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        if (request.getTitle() != null) q.setTitle(request.getTitle());
        if (request.getDifficulty() != null) q.setDifficulty(request.getDifficulty());
        if (request.getProblemLink() != null) q.setProblemLink(request.getProblemLink());
        if (request.getVideoLink() != null) q.setVideoLink(request.getVideoLink());

        if (request.getOptionA() != null) q.setOptionA(request.getOptionA());
        if (request.getOptionB() != null) q.setOptionB(request.getOptionB());
        if (request.getOptionC() != null) q.setOptionC(request.getOptionC());
        if (request.getOptionD() != null) q.setOptionD(request.getOptionD());

        if (request.getCorrectOption() != null) q.setCorrectOption(request.getCorrectOption());
        if (request.getExplanation() != null) q.setExplanation(request.getExplanation());

        return questionRepository.save(q);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(
            @PathVariable Long questionId
    ) {
        questionRepository.deleteById(questionId);
        return ResponseEntity.ok("Question deleted successfully");
    }
}

