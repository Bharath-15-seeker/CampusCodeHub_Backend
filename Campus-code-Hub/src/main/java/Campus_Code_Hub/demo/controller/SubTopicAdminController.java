package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.CreateSubTopicRequest;
import Campus_Code_Hub.demo.dto.UpdateSubTopicRequest;
import Campus_Code_Hub.demo.model.SubTopic;
import Campus_Code_Hub.demo.model.Topic;
import Campus_Code_Hub.demo.repository.SubTopicRepository;
import Campus_Code_Hub.demo.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/subtopics")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class SubTopicAdminController {

    private final TopicRepository topicRepository;
    private final SubTopicRepository subTopicRepository;

    @PostMapping
    public SubTopic createSubTopic(@RequestBody CreateSubTopicRequest request) {

        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        SubTopic subTopic = new SubTopic();
        subTopic.setName(request.getName());
        subTopic.setYoutubeLink(request.getYoutubeLink());
        subTopic.setTopic(topic);

        return subTopicRepository.save(subTopic);
    }

    @PutMapping("/{subTopicId}")
    public SubTopic updateSubTopic(
            @PathVariable Long subTopicId,
            @RequestBody UpdateSubTopicRequest request
    ) {
        SubTopic subTopic = subTopicRepository.findById(subTopicId)
                .orElseThrow(() -> new RuntimeException("SubTopic not found"));

        if (request.getName() != null) {
            subTopic.setName(request.getName());
        }
        if (request.getYoutubeLink() != null) {
            subTopic.setYoutubeLink(request.getYoutubeLink());
        }

        return subTopicRepository.save(subTopic);
    }

    @DeleteMapping("/{subTopicId}")
    public ResponseEntity<String> deleteSubTopic(
            @PathVariable Long subTopicId
    ) {
        subTopicRepository.deleteById(subTopicId);
        return ResponseEntity.ok("SubTopic deleted successfully");
    }
}

