package duke.task;

import duke.DukeException;
import duke.data.Data;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public static Task createTodo(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("There's not enough information about your Todo order!");
        }
        return new Todo(command[1]);
    }
    public String getFormattedString() {
        return "TODO" + Data.splitter + (isDone? "1" : "0") + Data.splitter+ description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + getDate() + "\n";
    }
}
