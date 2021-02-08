package percy.exception;

import java.util.ArrayList;

import percy.command.*;
import percy.ui.Ui;



public class EmptyArgumentsException extends PercyException {
    protected String taskType;

    /**
     * Constructs NoDescriptionException.
     * @param taskType the type of task e.g. event, deadline, todo etc.
     */
    public EmptyArgumentsException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        ArrayList<String> arr = new ArrayList<>();
        switch (taskType) {
        case TodoCommand.COMMAND:
            arr.add("☹ OOPS!!! The description of a todo cannot be empty.\n");
            arr.addAll(TodoCommand.USAGE_GUIDE);
            return Ui.makeMsg(arr);
        case EventCommand.COMMAND:
            arr.add("☹ OOPS!!! The description or date/time of an event cannot be empty.\n");
            arr.addAll(EventCommand.USAGE_GUIDE);
            return Ui.makeMsg(arr);
        case DeadlineCommand.COMMAND:
            arr.add("☹ OOPS!!! The description or date/time of a deadline cannot be empty.\n");
            arr.addAll(DeadlineCommand.USAGE_GUIDE);
            return Ui.makeMsg(arr);
        case FindCommand.COMMAND:
            arr.add("OOPS!!! The keyword is missing.\n");
            arr.addAll(FindCommand.USAGE_GUIDE);
            return Ui.makeMsg(arr);
        case DeleteCommand.COMMAND:
            arr.add("OOPS!!! No task number given was given.\n");
            arr.addAll(DeleteCommand.USAGE_GUIDE);
            return Ui.makeMsg(arr);
        case DoneCommand.COMMAND:
            arr.add("OOPS!!! No task number was given.\n");
            arr.addAll(DoneCommand.USAGE_GUIDE);
            return Ui.makeMsg(arr);
        default:
            return Ui.makeMsg(arr);
        }
    }
}
