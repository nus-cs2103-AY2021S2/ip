package duke.command;

import java.io.File;
import java.io.IOException;

import duke.duke.Duke;
import duke.exceptions.InvalidArgumentException;
import duke.storage.Storage;

/**
 * Creates a Deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    /**
     * Creates a {@code DeadlineCommand} object with a task description and deadline date component.
     * @param description task description
     * @param deadline deadline of task
     */
    public DeadlineCommand(String description, String deadline) {
        super("deadline");
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Returns description of deadline task.
     * @return deadline task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns deadline of task.
     * @return task deadline
     */
    public String getDeadline() {
        return deadline;
    }

    public static String[] validateArgument(String userInput) throws InvalidArgumentException {
        String[] userInputArr = userInput.split("/by");
        if (userInputArr.length == 1) {
            throw new InvalidArgumentException("Please input task due date using '/by (date)'!\n");
        }
        String[] str = userInputArr[0].split(" ", 2);
        if (str.length == 1) {
            throw new InvalidArgumentException("Please input task description!\n");
        }
        return userInputArr;
    }

    @Override
    public String run(File file, Duke bot) throws IOException {
        String output = bot.addTask(getDescription(), getCommand(), getDeadline());
        Storage.saveFile(file, bot);
        return output;
    }
}
