package ru.task_manager.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @OneToMany(mappedBy = "user")
    @Fetch(FetchMode.JOIN)
    private List<TaskEntity> taskEntityList = new ArrayList<>();

    @ManyToMany(mappedBy = "usersEntity")
    @Fetch(FetchMode.SUBSELECT)
    private List<ProjectEntity> projects = new ArrayList<>();

    public void deleteProjectFromList(ProjectEntity projectEntity){
        projects.remove(projectEntity);
    }

    private UserEntity(Long id,
                       String name,
                       String middleName,
                       String lastName,
                       String email,
                       String password) {
        this.id = id;
        this.name = name;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String middleName;
        private String lastName;
        private String email;
        private String password;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(
                    id,
                    name,
                    middleName,
                    lastName,
                    email,
                    password);
        }
    }
}
