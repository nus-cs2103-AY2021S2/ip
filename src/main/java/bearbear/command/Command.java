package bearbear.command;

import java.io.File;
import java.io.IOException;

import bearbear.bearbear.BearBear;

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
     * @param bot A {@code BearBear} object.
     * @return Output message as String to be shown to user.
     * @throws IOException If error occurs while writing to file.
     */
    public abstract String run(File file, BearBear bot) throws IOException;
}
