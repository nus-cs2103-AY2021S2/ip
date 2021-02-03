package duke.command;

import java.io.File;
import java.io.IOException;

import duke.duke.Duke;

/**
 * Represents a user command.
 */
public abstract class Command {
    protected final String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    /**
     * Runs the command.
     * @param file The file that task information is stored and retrieved from.
     * @param bot A duke object.
     * @return Output message as String to be shown to user.
     * @throws IOException If error occurs while writing to file.
     */
    public abstract String run(File file, Duke bot) throws IOException;
}
