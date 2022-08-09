package ru.task_manager.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.task_manager.factories.TaskType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "task_title")
    private String title;

    @Column(name = "task_description")
    private String description;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "task_type")
    @Enumerated(value = EnumType.STRING)
    private TaskType type;

    @ManyToOne
    @JoinColumn(name = "id_of_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_of_project")
    private ProjectEntity projectEntity;

    @OneToMany(mappedBy = "taskEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<CommentEntity> comments = new ArrayList<>();


    private TaskEntity(Long id, String title, String description, Date deadline, TaskType type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.type = type;
    }

    public static class Builder {
        private Long id;
        private String title;
        private String description;
        private Date deadline;
        private TaskType taskType;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDeadline(Date deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder setTaskType(TaskType taskType) {
            this.taskType = taskType;
            return this;
        }

        public TaskEntity build() {
            return new TaskEntity(
                    id,
                    title,
                    description,
                    deadline,
                    taskType
            );
        }
    }
}
