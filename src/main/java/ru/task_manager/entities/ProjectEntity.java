package ru.task_manager.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@Getter
@Setter
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(name = "project_title")
    private String title;

    @Column(name = "project_description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "users_projects",
            joinColumns = @JoinColumn(name = "id_of_project"),
            inverseJoinColumns = @JoinColumn(name = "id_of_user")
    )
    @Fetch(FetchMode.SUBSELECT)
    private List<UserEntity> usersEntity = new ArrayList<>();

    @OneToMany(mappedBy = "projectEntity", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<TaskEntity> taskEntities = new ArrayList<>();

    public void deleteUserFromProject(UserEntity userEntity){
        usersEntity.remove(userEntity);
    }
}
