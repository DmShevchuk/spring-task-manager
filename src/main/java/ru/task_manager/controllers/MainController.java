package ru.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.task_manager.commands.CommandInvoker;
import ru.task_manager.services.UserService;
import ru.task_manager.utils.LineHandler;

/**
 * Главный контроллер, принимающий все запросы от пользователя
 */
@RestController
@RequiredArgsConstructor
public class MainController {
    private final LineHandler lineHandler;
    private final CommandInvoker commandInvoker;
    private final UserService userService;
    /**
     * Примеры задания запросов:<br/>
     * http://localhost:8080/?line=add_user,Ivan Petrov <br/>
     * http://localhost:8080/?line=add_task, Do homework, Write a lot of code, 04.07.2022, new, 1 <br/>
     * http://localhost:8080/?line=show_tasks <br/>
     * http://localhost:8080/?line=show_users <br/>
     * http://localhost:8080/?line=change_task, 1, Do homework, Write a lot of code, 04.07.2022, done, 1 <br/>
     * http://localhost:8080/?line=delete_task_by_id,1 <br/>
     */
    @GetMapping("/")
    public String index(@RequestParam String line) {
        try {
            String[] args = lineHandler.parse(line.strip());
            return commandInvoker.invoke(args);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/users_tasks")
    public String index(@RequestParam(name = "type", required = false) String type,
                        @RequestParam(name= "min_date", required = false) String minDate,
                        @RequestParam(name = "max_date", required = false) String maxDate){
        try {
            return commandInvoker.invoke(
                    new String[]{"find_user_with_max_task_quantity",
                    type,
                    minDate,
                    maxDate}
            );
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
