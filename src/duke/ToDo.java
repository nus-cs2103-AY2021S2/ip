package duke;

import java.lang.Throwable;
public class ToDo extends Task {
    ToDo() {

    }
    ToDo(String input) throws DukeIncompleteCommandException {
        input = input.substring(4).trim();
        if (input.equals("")) {
            throw new DukeIncompleteCommandException();
        }
        this.task = input.trim();
        this.isDone = false;
    }
    static ToDo fileReader(String line) {
        ToDo todo = new ToDo();
        if (line.charAt(5) == 'X') {
            todo.isDone = true;
        } else {
            todo.isDone = false;
        }
        todo.task = line.substring(7).trim();
        return todo;
    }

    @Override
    public String toString() {
        return "TODO" + super.toString();
    }
}
