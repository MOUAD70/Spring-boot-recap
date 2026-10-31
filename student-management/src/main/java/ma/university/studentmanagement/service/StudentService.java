package ma.university.studentmanagement.service;

import ma.university.studentmanagement.dto.mapper.StudentMapper;
import ma.university.studentmanagement.dto.request.CreateStudentRequest;
import ma.university.studentmanagement.dto.request.UpdateStudentRequest;
import ma.university.studentmanagement.dto.response.StudentResponse;
import ma.university.studentmanagement.model.Student;
import ma.university.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponse createStudent(CreateStudentRequest request) {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Student student = studentMapper.toEntity(request);
        Student savedStudent = studentRepository.save(student);

        return studentMapper.toResponse(savedStudent);
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toResponse).toList();
    }

    public StudentResponse getStudentById(Long id) {
        return studentRepository.findById(id).map(studentMapper::toResponse).orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    public void deleteStudent(Long id) {
        boolean isDeleted = studentRepository.deleteById(id);

        if (!isDeleted) {
            throw new IllegalArgumentException("Student not found");
        }
    }

    public StudentResponse updateStudent(Long id, UpdateStudentRequest request) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));

        if (!student.getEmail().equalsIgnoreCase(request.getEmail()) && studentRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already taken");
        }

        studentMapper.updateEntity(student, request);

        Student updatedStudent = studentRepository.update(id, student)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        return studentMapper.toResponse(updatedStudent);
    }

}
