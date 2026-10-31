package ma.university.studentmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String major;
    private boolean active;
}
