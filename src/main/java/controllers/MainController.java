package controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import services.UserService;

/**
 * Главный контроллер, принимающий все запросы от пользователя
 * */
@RestController
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return null;
    }
}
