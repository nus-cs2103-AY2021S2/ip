package percy.exception;

import java.util.ArrayList;

import percy.command.DeleteCommand;
import percy.command.DoneCommand;
import percy.ui.Ui;

public class InvalidArgumentsException extends PercyException {
    protected String taskType;

    /**
     * Constructs NoDescriptionException.
     * @param taskType the type of task e.g. event, deadline, todo etc.
     */
    public InvalidArgumentsException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        ArrayList<String> arr = new ArrayList<>();
        switch (taskType) {
        case DoneCommand.COMMAND:
            arr.add("☹ OOPS!!! The number given is invalid. The number must be in range of the list.\n");
            arr.addAll(DoneCommand.USAGE_GUIDE);
            return Ui.makeMsg(arr);
        case DeleteCommand.COMMAND:
            arr.add("☹ OOPS!!! The number given is invalid. the number must be in range of the list.\n");
            arr.addAll(DeleteCommand.USAGE_GUIDE);
            return Ui.makeMsg(arr);
        default:
            return Ui.makeMsg(arr);
        }
    }
}
