package duke.command;

import java.io.File;
import java.io.IOException;

import duke.duke.Duke;
import duke.storage.Storage;

/**
 * Creates a Todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Creates a {@code TodoCommand} object with a task description component only.
     * @param description task description
     */
    public TodoCommand(String description) {
        super("todo");
        this.description = description;
    }

    /**
     * Returns description of Todo task.
     * @return description of Todo task
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String run(File file, Duke bot) throws IOException {
        String output = bot.addTask(getDescription(), getCommand(), null);
        Storage.saveFile(file, bot);
        return output;
    }
}
