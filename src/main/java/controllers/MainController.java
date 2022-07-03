package controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.LineHandler;

/**
 * Главный контроллер, принимающий все запросы от пользователя
 * */
@RestController
@RequiredArgsConstructor
public class MainController {
    private final LineHandler lineParser;

    /**
     * Примеры задания запросов:<br/>
     * http://localhost:8080/?line=add_user,Ivan Petrov <br/>
     * http://localhost:8080/?line=add_task, Do homework, Write a lot of code, 04.07.2022, new, 1 <br/>
     * http://localhost:8080/?line=show_tasks <br/>
     * http://localhost:8080/?line=show_users <br/>
     * http://localhost:8080/?line=change_task_by_id, 1, Do homework, Write a lot of code, 04.07.2022, done, 1 <br/>
     * http://localhost:8080/?line=delete_task_by_id,1 <br/>
     * */
    @GetMapping("/")
    public String index(@RequestParam String line) {
        try {
            return lineParser.parse(line.strip());
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
