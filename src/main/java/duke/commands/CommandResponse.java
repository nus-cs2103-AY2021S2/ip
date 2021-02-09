package duke.commands;

/**
 * Stores the responses after executing a command.
 */
public class CommandResponse {

    private final String response;

    /** Indicator used to differentiate exit command */
    private final boolean shouldExit;

    /**
     * Constructor for CommandResponse, specifying the description.
     *
     * @param response response of the {@code Command}
     */
    public CommandResponse(String response) {
        this.response = response;
        this.shouldExit = false;
    }

    /**
     * Constructor for CommandResponse, specifying the description and should exit.
     *
     * @param response response of the {@code Command}
     * @param shouldExit indicate whether is it an {@code ExitCommand}
     */
    public CommandResponse(String response, boolean shouldExit) {
        this.response = response;
        this.shouldExit = shouldExit;
    }

    public boolean isShouldExit() {
        return shouldExit;
    }

    public String toString() {
        return response;
    }
}
