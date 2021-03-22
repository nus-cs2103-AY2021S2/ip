package command;
import exception.MikeCommandExecutionException;
import mike.TaskList;

public interface Command {
    /**
     * Checks if chatbot should exit after command
     *
     * @return value of isExitCommand in Command.Command objects
     */
    boolean isExitCommand();

    /**
     * Executes the command on the taskList
     *
     * @return resultant tasklist after command is run
     */
    /*TODO change exception thrown*/
    TaskList runCommand(TaskList taskList) throws MikeCommandExecutionException;

    /**
     * Gets the response of the command
     *
     * @return String response of the command
     */
    String getResponse();
}
