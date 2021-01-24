package main.java;

public class ByeCommand extends Command {
    ByeCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.speak("Goodbye for now, we will meet again.");
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
