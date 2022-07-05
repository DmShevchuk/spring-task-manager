package ru.task_manager.commands.impl;

import org.springframework.stereotype.Component;
import ru.task_manager.commands.Command;
import ru.task_manager.exceptions.IncorrectArgsQuantityException;

import java.util.Map;
import java.util.TreeSet;

/**
 * Класс, выводящий все доступные команды, их описание и параметры
 */
@Component
public class Help extends Command {
    private String infoString;

    public Help() {
        super("help", "info about all commands", 0);
    }

    @Override
    public String execute() {
        if (args.length != argsQuantity) {
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }
        return infoString;
    }

    public void initializeInfoString(Map<String, String> infoMap) {
        StringBuilder string = new StringBuilder();
        for (String key : new TreeSet<>(infoMap.keySet())) {
            string.append(key)
                    .append("-")
                    .append(infoMap.get(key));
        }
        infoString = string.toString();
    }
}
