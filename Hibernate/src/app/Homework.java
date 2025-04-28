package app;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "homework")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Homework {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private LocalDate deadline;

    private int mark;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="student_id")
    @ToString.Exclude
    private Student student;
}