package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.CreateCareerResourceRequest;
import Campus_Code_Hub.demo.dto.CreateCareerTrackRequest;
import Campus_Code_Hub.demo.dto.UpdateCareerResourceRequest;
import Campus_Code_Hub.demo.dto.UpdateCareerTrackRequest;
import Campus_Code_Hub.demo.model.CareerResource;
import Campus_Code_Hub.demo.model.CareerTrack;
import Campus_Code_Hub.demo.service.CareerTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/career-tracks")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class CareerTrackAdminController {

    private final CareerTrackService service;


    @PostMapping
    public ResponseEntity<CareerTrack> createTrack(
            @RequestBody CreateCareerTrackRequest request
    ) {
        return ResponseEntity.ok(service.createTrack(request));
    }

    @PostMapping("/{trackId}/resources")
    public ResponseEntity<CareerResource> addResource(
            @PathVariable Long trackId,
            @RequestBody CreateCareerResourceRequest request
    ) {
        return ResponseEntity.ok(service.addResource(trackId, request));
    }

    @DeleteMapping("/resources/{resourceId}")
    public ResponseEntity<String> deleteResource(
            @PathVariable Long resourceId
    ) {
        service.deleteResource(resourceId);
        return ResponseEntity.ok("Career resource deleted successfully");
    }

    // 🗑️ Delete a career track
    @DeleteMapping("/tracks/{trackId}")
    public ResponseEntity<String> deleteTrack(
            @PathVariable Long trackId
    ) {
        service.deleteTrack(trackId);
        return ResponseEntity.ok("Career track deleted successfully");
    }

    // ✏️ Update Career Track
    @PutMapping("/tracks/{trackId}")
    public ResponseEntity<CareerTrack> updateTrack(
            @PathVariable Long trackId,
            @RequestBody UpdateCareerTrackRequest request
    ) {
        return ResponseEntity.ok(
                service.updateTrack(trackId, request)
        );
    }

    // ✏️ Update Career Resource
    @PutMapping("/resources/{resourceId}")
    public ResponseEntity<CareerResource> updateResource(
            @PathVariable Long resourceId,
            @RequestBody UpdateCareerResourceRequest request
    ) {
        return ResponseEntity.ok(
                service.updateResource(resourceId, request)
        );
    }
}

