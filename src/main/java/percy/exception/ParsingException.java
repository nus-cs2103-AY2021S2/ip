package percy.exception;

import percy.command.EventCommand;
import percy.command.TodoCommand;
import percy.command.DeadlineCommand;


public class ParsingException extends PercyException {
    private String taskType;

    /**
     * Constructs NoDescriptionException.
     * @param taskType the type of task e.g. event, deadline, todo etc.
     */
    public ParsingException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        switch (taskType) {
        case TodoCommand.COMMAND:
            return " ☹ OOPS!!! The description of a " + taskType + " cannot be empty.";
        default:
            return " ☹ OOPS!!! The description or date/time of a " + taskType + " cannot be empty.";
        }
    }
}
