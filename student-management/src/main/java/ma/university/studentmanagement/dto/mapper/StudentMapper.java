package ma.university.studentmanagement.dto.mapper;

import ma.university.studentmanagement.dto.request.CreateStudentRequest;
import ma.university.studentmanagement.dto.request.UpdateStudentRequest;
import ma.university.studentmanagement.dto.response.StudentResponse;
import ma.university.studentmanagement.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toEntity(CreateStudentRequest request) {
        return new Student(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getAge(),
                request.getMajor()
        );
    }

    public StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getAge(),
                student.getMajor(),
                student.isActive()
        );
    }

    public void updateEntity(Student student, UpdateStudentRequest request) {
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());
        student.setMajor(request.getMajor());
        student.setActive(request.isActive());
    }
}
