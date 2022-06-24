package commands.impl;

import commands.Command;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

public class Help extends Command {
    private final Map<String, String> infoMap;
    private String infoString;

    public Help(Map<String, String> infoMap) {
        super("help", "|| info about all commands", 0);
        this.infoMap = infoMap;
        initializeInfoString();
    }

    @Override
    public String execute() {
        return infoString;
    }

    private void initializeInfoString() {
        int spaceQuantity = 5;
        // Получение названия самой длинной команды
        Optional<String> maxLength = infoMap.keySet().stream().max(Comparator.comparing(String::length));

        // Количество пробелов между названием команды и её описанием
        int spacesBetweenWords = maxLength.get().length() + spaceQuantity;

        StringBuilder string = new StringBuilder();
        for (String key : new TreeSet<String>(infoMap.keySet())) {
            // Указание количества пробелов между названием команды и её описанием
            String settings = "%-" + spacesBetweenWords + "s";

            string.append(String.format(settings, key));
            string.append(infoMap.get(key)).append("\n");
        }
        infoString = string.toString();
    }
}
