package ru.task_manager.commands.impl;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task_manager.commands.Command;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Класс, выводящий все доступные команды, их описание и параметры
 */
@Component
public class Help extends Command {
    private String infoString;

    public Help(List<Command> commandList){
        StringBuilder stringBuilder = new StringBuilder();
        for(Command command: commandList){
            stringBuilder.append(command.getName())
                    .append("------>")
                    .append(command.getInfo())
                    .append("<br/>");
        }
        infoString = stringBuilder.toString();
    }

    @Override
    public String execute(String[] args) {
        isArgQuantityCorrect();
        return infoString;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getInfo() {
        return "info about all commands";
    }

    @Override
    public int getArgsQuantity() {
        return 0;
    }
}
