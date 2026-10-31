package ma.university.studentmanagement.repository;

import ma.university.studentmanagement.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class StudentRepository {
    private final List<Student> students = new ArrayList<>();
    private long nextId = 1;

    public Student save(Student student) {
        student.setId(nextId++);
        students.add(student);
        return student;
    }

    public List<Student> findAll() {
        return students.stream().toList();
    }

    public Optional<Student> findById(Long id) {
        return students.stream()
                .filter(student -> Objects.equals(student.getId(), id))
                .findFirst();
    }

    public boolean deleteById(Long id) {
        return students.removeIf(student -> Objects.equals(student.getId(), id));
    }

    public boolean existsByEmail(String email) {
        return students.stream().anyMatch(student -> Objects.equals(student.getEmail(), email));
    }

    public Optional<Student> update(Long id, Student updatedStudent) {
        Optional<Student> existingStudent = students.stream()
                .filter(student -> Objects.equals(student.getId(), id))
                .findFirst();

        if (existingStudent.isEmpty()) return Optional.empty();

        Student student = existingStudent.get();

        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());
        student.setAge(updatedStudent.getAge());
        student.setMajor(updatedStudent.getMajor());
        student.setActive(updatedStudent.isActive());

        return Optional.of(student);
    }
}
