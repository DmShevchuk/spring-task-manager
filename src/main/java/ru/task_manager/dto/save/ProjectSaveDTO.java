package ru.task_manager.dto.save;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectSaveDTO {
    @NotBlank(message = "Title not specified!")
    private String title;
    @NotBlank(message = "Description not specified!")
    private String description;

    private List<Long> usersIdList;
}
