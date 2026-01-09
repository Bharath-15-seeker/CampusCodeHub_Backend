package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.CreateTopicRequest;
import Campus_Code_Hub.demo.dto.UpdateTopicRequest;
import Campus_Code_Hub.demo.model.Sheet;
import Campus_Code_Hub.demo.model.Topic;
import Campus_Code_Hub.demo.repository.SheetRepository;
import Campus_Code_Hub.demo.repository.TopicRepository;
import lombok.*;

import org.springframework.http.ResponseEntity;
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


    @PutMapping("/{topicId}")
    public Topic updateTopic(
            @PathVariable Long topicId,
            @RequestBody UpdateTopicRequest request
    ) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        if (request.getName() != null) {
            topic.setName(request.getName());
        }
        if (request.getOrderIndex() != null) {
            topic.setOrderIndex(request.getOrderIndex());
        }

        return topicRepository.save(topic);
    }

    @DeleteMapping("/{topicId}")
    public ResponseEntity<String> deleteTopic(@PathVariable Long topicId) {
        topicRepository.deleteById(topicId);
        return ResponseEntity.ok("Topic deleted successfully");
    }
}

