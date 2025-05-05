package app;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", nullable=false)
    private String firstName;

    @Column(name="last_name",  nullable=false)
    private String lastName;

    @Column(nullable=false, unique=true)
    private String email;

    @OneToMany(mappedBy="student", cascade=CascadeType.ALL, orphanRemoval=true)
    @ToString.Exclude
    private Set<Homework> homeworks = new HashSet<>();

    public void addHomework(Homework hw) {
        hw.setStudent(this);
        homeworks.add(hw);
    }

    public void removeHomework(Homework hw) {
        if (homeworks.remove(hw)) {
            hw.setStudent(null);
        }
    }
}