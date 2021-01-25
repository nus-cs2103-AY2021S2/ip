package main.java.command;

import main.java.TaskManager;
import main.java.Ui;

public class WrongCommand extends Command{
    String message;
    public WrongCommand() {

    }

    public WrongCommand(String message) {

    }

    @Override
    public void execute(TaskManager tm, Ui ui) {
        if (message != null) {
            ui.displayWrongCommand(message);
        } else {
            ui.displayWrongCommand();
        }
    }
}
