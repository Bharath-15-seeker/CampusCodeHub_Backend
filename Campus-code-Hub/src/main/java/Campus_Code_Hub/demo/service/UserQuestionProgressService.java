package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.MyQuestionProgressResponse;
import Campus_Code_Hub.demo.dto.QuestionProgressRequest;
import Campus_Code_Hub.demo.model.Question;
import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.model.UserQuestionProgress;
import Campus_Code_Hub.demo.repository.QuestionRepository;
import Campus_Code_Hub.demo.repository.StudentRepository;
import Campus_Code_Hub.demo.repository.UserQuestionProgressRepository;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQuestionProgressService {

    private final UserQuestionProgressRepository progressRepository;
    private final QuestionRepository questionRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public void updateProgress(String email, QuestionProgressRequest request) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        UserQuestionProgress progress =
                progressRepository.findByUserAndQuestion(student, question)
                        .orElseGet(() -> {
                            UserQuestionProgress p = new UserQuestionProgress();
                            p.setUser(student);
                            p.setQuestion(question);
                            return p;
                        });

        progress.setSolved(request.isSolved());
        progressRepository.save(progress);
    }


    @Transactional(readOnly = true)
    public List<MyQuestionProgressResponse> getMyProgress(String email) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return progressRepository.findByUser(student)
                .stream()
                .map(progress -> {
                    MyQuestionProgressResponse dto = new MyQuestionProgressResponse();
                    dto.setQuestionId(progress.getQuestion().getId());
                    dto.setQuestionTitle(progress.getQuestion().getTitle());
                    dto.setDifficulty(progress.getQuestion().getDifficulty());
                    dto.setSolved(progress.isSolved());
                    return dto;
                })
                .toList();
    }
}

