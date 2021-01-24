package duke.command;

import duke.CommandType;
import duke.DukeException;
import duke.StringParser;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.DateTimeException;

public class AddCommand extends Command {

    private final String command;
    private final CommandType cmdType;

    public AddCommand(String command, CommandType cmdType) {
        this.command = command;
        this.cmdType = cmdType;
    }

    private Task convertToTodo() throws DukeException {
        if (command.length() <= 5) {
            throw new DukeException("Invalid argument: Argument field cannot be empty.");
        } else {
            String subStr = command.substring(5);
            if (StringParser.isBlank(subStr)) {
                throw new DukeException("Invalid argument: Content field is blank");
            } else {
                return new Todo(command.substring(5));
            }
        }
    }

    private Task convertToEvent() throws DukeException {
        if (command.length() <= 9) {
            throw new DukeException("Invalid argument: Argument field cannot be empty.");
        } else {
            int indexOfAt = command.toLowerCase().indexOf("/at");
            String subStrContent = command.substring(6, indexOfAt - 1);
            String subStrTime = command.substring(indexOfAt + 4);
            if (StringParser.isBlank(subStrContent)) {
                throw new DukeException("Void argument: Content field is blank");
            } else if (StringParser.isBlank(subStrTime)) {
                throw new DukeException("Void argument: Time field is blank");
            } else {
                try {
                    return new Event(subStrContent, StringParser.parseTime(subStrTime));
                } catch (DateTimeException e) {
                    throw new DukeException("Incorrect time format: Correct format is yyyy-MM-dd HHmm");
                }
            }
        }
    }

    private Task convertToDeadline() throws DukeException {
        if (command.length() <= 6) {
            throw new DukeException("Invalid argument: Argument field cannot be empty.");
        } else {
            int indexOfBy = command.toLowerCase().indexOf("/by");
            String subStrContent = command.substring(9, indexOfBy - 1);
            String subStrTime = command.substring(indexOfBy + 4);
            if (StringParser.isBlank(subStrContent)) {
                throw new DukeException("Void argument: Content field is blank");
            } else if (StringParser.isBlank(subStrTime)) {
                throw new DukeException("Void argument: Time field is blank");
            } else {
                try {
                    return new Deadline(subStrContent, StringParser.parseTime(subStrTime));
                } catch (DateTimeException e) {
                    throw new DukeException("Incorrect time format: Correct format is yyyy-MM-dd HHmm");
                }
            }
        }
    }


    @Override
    public void executeAndPrint(TaskList list, int length) throws DukeException {
        Task task;
        switch (this.cmdType) {
        case Todo:
            task = convertToTodo();
            break;
        case Event:
            task = convertToEvent();
            break;
        case Deadline:
            task = convertToDeadline();
            break;
        default:
            throw new DukeException("Unexpected value: " + this.cmdType);
        }
        list.addJob(task);
        System.out.print("Task added:\n" + StringParser.newLiner(task.toString(), length)
                + "Now you have " + list.getSize()
                + (list.getSize() == 1 ? " task in the list\n" : " tasks in the list\n"));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        switch (this.cmdType) {
        case Todo:
            return "Test usage: this is a TODO command";
        case Deadline:
            return "Test usage: this is a DEADLINE command";
        case Event:
            return "Test usage: this is an EVENT command";
        default:
            return "Error: this will never happen";
        }
    }
}
