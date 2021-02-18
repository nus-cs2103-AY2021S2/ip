package bearbear.command;

import java.io.File;
import java.io.IOException;

import bearbear.bearbear.BearBear;
import bearbear.storage.Storage;

/**
 * Creates a Todo task.
 */
public class TodoCommand extends Command {
    private static final String usageMessage = "Command: todo <task_description>\n"
            + "Description: Adds a todo task with no specified time to task list\n";
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

    public static String getUsageMessage() {
        return usageMessage;
    }

    @Override
    public String run(File file, BearBear bot) throws IOException {
        String output = bot.addTask(getDescription(), getCommand(), null);
        Storage.saveFile(file, bot);
        return output;
    }
}
