package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tasks.TaskType;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date deadline;
    private TaskType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
