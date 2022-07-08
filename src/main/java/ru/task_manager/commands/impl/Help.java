package ru.task_manager.commands.impl;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import java.util.Map;

/**
 * Класс, выводящий все доступные команды, их описание и параметры
 */
@RequiredArgsConstructor
public class Help extends Command {
    private final Map<String, String> commandInfoMap;

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String commandName: commandInfoMap.keySet()){
            stringBuilder.append(commandName)
                    .append("------>")
                    .append(commandInfoMap.get(commandName))
                    .append("<br/>");
        }
        return stringBuilder.toString();
    }
}
