package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.*;
import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final StudentRepository studentRepository;

    /* =========================
       VIEW PROFILE
    ========================= */

    public StudentProfileDTO getProfile(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return mapToDTO(student);
    }

    /* =========================
       UPDATE PROFILE
    ========================= */

    public StudentProfileDTO updateProfile(Long studentId,
                                           UpdateProfileRequest request) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (request.getName() != null)
            student.setName(request.getName());

        if (request.getDepartment() != null)
            student.setDepartment(request.getDepartment());

        if (request.getYear() != null)
            student.setYear(request.getYear());

        if (request.getRegisterNumber() != null)
            student.setRegisterNumber(request.getRegisterNumber());

        if (request.getCodingProfileUrl() != null)
            student.setCodingProfileUrl(request.getCodingProfileUrl());


        studentRepository.save(student);

        return mapToDTO(student);
    }

    /* =========================
       MAPPER
    ========================= */

    private StudentProfileDTO mapToDTO(Student student) {
        return StudentProfileDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .department(student.getDepartment())
                .year(student.getYear())
                .registerNumber(student.getRegisterNumber())
                .codingProfileUrl(student.getCodingProfileUrl())
                .codingPoints(student.getCodingPoints())
                .aptitudePoints(student.getAptitudePoints())
                .build();
    }
}
