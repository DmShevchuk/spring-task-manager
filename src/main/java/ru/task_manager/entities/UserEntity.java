package ru.task_manager.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "user_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Fetch(FetchMode.JOIN)
    private List<TaskEntity> taskEntityList;

    private UserEntity(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public static class Builder{
        private Long id;
        private String name;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public UserEntity build(){
            return new UserEntity(id, name);
        }
    }
}
