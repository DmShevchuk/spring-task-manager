package commands.impl;

import commands.Command;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

/**
 * Класс, выводящий все доступные команды, их описание и параметры
 */
@Component
public class Help extends Command {
    private String infoString;

    public Help() {
        super("help", "|| info about all commands", 0);
    }

    @Override
    public String execute() {
        return infoString;
    }

    public void initializeInfoString(Map<String, String> infoMap) {
        int spaceQuantity = 5;
        // Получение названия самой длинной команды
        Optional<String> maxLength = infoMap.keySet().stream().max(Comparator.comparing(String::length));

        // Количество пробелов между названием команды и её описанием
        int spacesBetweenWords = maxLength.get().length() + spaceQuantity;

        StringBuilder string = new StringBuilder();
        for (String key : new TreeSet<>(infoMap.keySet())) {
            string.append(key);
            string.append("_".repeat(Math.max(0, spacesBetweenWords - key.length())));
            string.append(infoMap.get(key));
            string.append("<br/>");
        }
        infoString = string.toString();
    }
}
