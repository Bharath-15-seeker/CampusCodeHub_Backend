package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.*;
import Campus_Code_Hub.demo.model.CareerResource;
import Campus_Code_Hub.demo.model.CareerTrack;
import Campus_Code_Hub.demo.repository.CareerResourceRepository;
import Campus_Code_Hub.demo.repository.CareerTrackRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CareerTrackService {

    private final CareerTrackRepository trackRepository;
    private final CareerResourceRepository resourceRepository;

    // ADMIN – create track
    public CareerTrack createTrack(CreateCareerTrackRequest request) {
        CareerTrack track = new CareerTrack();
        track.setName(request.getName());
        track.setDescription(request.getDescription());
        return trackRepository.save(track);
    }

    // STUDENT – view all tracks
    public List<CareerTrackResponse> getAllTracks() {
        return trackRepository.findAll()
                .stream()
                .filter(CareerTrack::isActive)
                .map(t -> {
                    CareerTrackResponse dto = new CareerTrackResponse();
                    dto.setId(t.getId());
                    dto.setName(t.getName());
                    dto.setDescription(t.getDescription());
                    return dto;
                }).toList();
    }

    // STUDENT – view roadmap
    public List<CareerResourceResponse> getResources(Long trackId) {

        CareerTrack track = trackRepository.findById(trackId)
                .orElseThrow(() -> new RuntimeException("Career track not found"));

        return resourceRepository
                .findByCareerTrackOrderByOrderIndexAsc(track)
                .stream()
                .map(r -> {
                    CareerResourceResponse dto = new CareerResourceResponse();
                    dto.setId(r.getId());
                    dto.setTitle(r.getTitle());
                    dto.setDescription(r.getDescription());
                    dto.setYoutubeLink(r.getYoutubeLink());
                    dto.setOrderIndex(r.getOrderIndex());
                    return dto;
                }).toList();
    }

    // ADMIN – add resource
    public CareerResource addResource(
            Long trackId,
            CreateCareerResourceRequest request
    ) {
        CareerTrack track = trackRepository.findById(trackId)
                .orElseThrow(() -> new RuntimeException("Career track not found"));

        CareerResource resource = new CareerResource();
        resource.setTitle(request.getTitle());
        resource.setDescription(request.getDescription());
        resource.setYoutubeLink(request.getYoutubeLink());
        resource.setOrderIndex(request.getOrderIndex());
        resource.setCareerTrack(track);

        return resourceRepository.save(resource);
    }


    @Transactional
    public void deleteResource(Long resourceId) {

        CareerResource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        resourceRepository.delete(resource);
    }

    // 🗑️ Delete career track (resources auto-deleted)
    @Transactional
    public void deleteTrack(Long trackId) {

        CareerTrack track = trackRepository.findById(trackId)
                .orElseThrow(() -> new RuntimeException("Career track not found"));

        trackRepository.delete(track);
    }

    @Transactional
    public CareerTrack updateTrack(
            Long trackId,
            UpdateCareerTrackRequest request
    ) {
        CareerTrack track = trackRepository.findById(trackId)
                .orElseThrow(() -> new RuntimeException("Career track not found"));

        if (request.getName() != null) {
            track.setName(request.getName());
        }

        if (request.getDescription() != null) {
            track.setDescription(request.getDescription());
        }

        if (request.getActive() != null) {
            track.setActive(request.getActive());
        }

        return trackRepository.save(track);
    }

    @Transactional
    public CareerResource updateResource(
            Long resourceId,
            UpdateCareerResourceRequest request
    ) {
        CareerResource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Career resource not found"));

        if (request.getTitle() != null) {
            resource.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            resource.setDescription(request.getDescription());
        }

        if (request.getYoutubeLink() != null) {
            resource.setYoutubeLink(request.getYoutubeLink());
        }

        if (request.getOrderIndex() != null) {
            resource.setOrderIndex(request.getOrderIndex());
        }

        return resourceRepository.save(resource);
    }

}

