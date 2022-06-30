package users;

import utils.CommandLine;
import utils.InputParser;

/**
 * Класс для получения полей объекта класса {@link User} из ввода пользователя
 */
public class UserFactory {
    private final CommandLine commandLine;

    public UserFactory(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    public User.UserBuilder getUser() {
        InputParser inputParser = new InputParser(commandLine);

        User.UserBuilder userBuilder = User.builder();

        userBuilder.name(inputParser.parseString("Name:"));

        return userBuilder;
    }
}
