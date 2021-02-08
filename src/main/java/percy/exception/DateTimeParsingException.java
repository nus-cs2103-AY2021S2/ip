package percy.exception;

import percy.command.DeadlineCommand;
import percy.command.EventCommand;
import percy.command.TodoCommand;
import percy.ui.Ui;

import java.util.ArrayList;

public class DateTimeParsingException extends ParsingException {

    public DateTimeParsingException(String taskType) {
        super(taskType);
    }

    @Override
    public String toString() {
        ArrayList<String> arr = new ArrayList<String>();
        switch (taskType) {
        case EventCommand.COMMAND:
            arr.add("OOPS!!! The date and time format of an event is wrong.\n");
            arr.addAll(EventCommand.USAGE_GUIDE);
            return Ui.makeMsg(arr);
        case DeadlineCommand.COMMAND:
            arr.add("OOPS!!! The date and time format of an event is wrong.\n");
            arr.addAll(DeadlineCommand.USAGE_GUIDE);
            return Ui.makeMsg(arr);
        default:
            return Ui.makeMsg(arr);
        }
    }
}
