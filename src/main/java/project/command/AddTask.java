package project.command;

import project.task.Task;

public abstract class AddTask extends Command {
    public AddTask() {
        super();
    }

    public AddTask(String userInput) {
        super(userInput);
    }
    public abstract Task loadFromStorage(String line);
}
