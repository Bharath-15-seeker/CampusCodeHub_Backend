package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.CareerResourceResponse;
import Campus_Code_Hub.demo.dto.CareerTrackResponse;
import Campus_Code_Hub.demo.service.CareerTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/career-tracks")
@RequiredArgsConstructor
public class CareerTrackController {

    private final CareerTrackService service;

    @GetMapping
    public ResponseEntity<List<CareerTrackResponse>> getTracks() {
        return ResponseEntity.ok(service.getAllTracks());
    }

    @GetMapping("/{trackId}")
    public ResponseEntity<List<CareerResourceResponse>> getRoadmap(
            @PathVariable Long trackId
    ) {
        return ResponseEntity.ok(service.getResources(trackId));
    }
}

