package ma.university.studentmanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String major;
    private boolean active = true;

    public Student(
            String firstName,
            String lastName,
            String email,
            Integer age,
            String major
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.major = major;
    }
}
