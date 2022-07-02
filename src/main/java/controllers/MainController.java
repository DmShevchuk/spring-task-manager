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

    @GetMapping("/")
    public String index(@RequestParam String line) {
        try {
            return lineParser.parse(line.strip());
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
