package commands;

public interface Command {
    String execute();
    void setAdditionalArgs(String string);
}
