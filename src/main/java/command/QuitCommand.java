package command;

import task.TaskManager;
import util.Parser;

import java.util.HashMap;
import java.util.List;

/**
 * Command to signal to the application to terminate.
 */
public class QuitCommand extends Command {
    public static final String COMMAND_STRING = "quit";
    public static final CommandType COMMAND_TYPE = CommandType.QUIT;

    /**
     * Constructs a QuitCommand from a commandMap.
     *
     * @param commandMap CommandMap representing the instruction.
     * @return QuitCommand based on the commandMap.
     */
    public static QuitCommand fromCommandMap(HashMap<String, List<String>> commandMap) {
        assert Parser.extractCommandString(commandMap).equals(COMMAND_STRING)
                : COMMAND_STRING + "CommandFlag does not match";

        return new QuitCommand();
    }

    /**
     * Creates a String listing all the Tasks in the supplied TaskManager. Does
     * not actually terminate the application. Termination should be handled by
     * the user class.
     *
     * @param taskManager Does not perform any action on the TaskManager, but
     *                    still required for abstraction purposes.
     * @return String to indicate that the application should terminate.
     */
    @Override
    public String execute(TaskManager taskManager) {
        return "See you again soon!";
    }

    /**
     * Indicates that this Command should signal to the application that it
     * should terminate.
     *
     * @return True.
     */
    @Override
    public boolean isQuitCommand() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}
