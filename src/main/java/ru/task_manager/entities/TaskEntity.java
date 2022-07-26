package ru.task_manager.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.task_manager.factories.TaskType;

import javax.persistence.*;
import java.util.Date;

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
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "deadline")
    private Date deadline;
    @Column(name = "task_type")
    @Enumerated(value = EnumType.STRING)
    private TaskType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity user;

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
