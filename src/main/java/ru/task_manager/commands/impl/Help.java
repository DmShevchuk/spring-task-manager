package ru.task_manager.commands.impl;

import lombok.Getter;
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
    private final String infoString;

    @Getter
    private final String name = "help";
    @Getter
    private final String info = "info about all commands";
    @Getter
    private final int argsQuantity = 0;

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
}
