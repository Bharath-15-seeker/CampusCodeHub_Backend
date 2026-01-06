package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.CreateTopicRequest;
import Campus_Code_Hub.demo.model.Sheet;
import Campus_Code_Hub.demo.model.Topic;
import Campus_Code_Hub.demo.repository.SheetRepository;
import Campus_Code_Hub.demo.repository.TopicRepository;
import lombok.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/topics")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class TopicAdminController {

    private final SheetRepository sheetRepository;
    private final TopicRepository topicRepository;

    @PostMapping
    public Topic createTopic(@RequestBody CreateTopicRequest request) {
        Sheet sheet = sheetRepository.findById(request.getSheetId())
                .orElseThrow(() -> new RuntimeException("Sheet not found"));

        Topic topic = new Topic();
        topic.setName(request.getName());
        topic.setOrderIndex(request.getOrderIndex());
        topic.setSheet(sheet);

        return topicRepository.save(topic);
    }
}

