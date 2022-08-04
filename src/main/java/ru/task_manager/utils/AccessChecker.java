package ru.task_manager.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task_manager.dto.user.UserDetailsImpl;
import ru.task_manager.services.TaskService;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class AccessChecker {
    private final TaskService taskService;

    public boolean canWorkWithUser(@NotNull UserDetailsImpl userDetails,
                                   @NotNull Long id){
        return id.equals(userDetails.getAppUserDTO().getId());
    }

    public boolean isTaskBelongToUser(@NotNull UserDetailsImpl userDetails,
                                      @NotNull Long id) {
        return taskService.getTaskById(id)
                .getUser()
                .getId()
                .equals(userDetails.getAppUserDTO().getId());
    }
}
