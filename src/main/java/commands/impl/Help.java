package commands.impl;

import commands.Command;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

/**
 * Класс, выводящий все доступные команды, их описание и параметры
 * */
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
        StringBuilder string = new StringBuilder();
        for (String key : new TreeSet<>(infoMap.keySet())) {
            string.append(key)
                    .append(infoMap.get(key))
                    .append("<br/>");
        }
        infoString = string.toString();
    }
}
