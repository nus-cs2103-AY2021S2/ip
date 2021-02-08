package duke;

import java.lang.Throwable;
public class ToDo extends Task {
    ToDo() {

    }
    static ToDo parseInput(String input) throws DukeIncompleteCommandException {
        ToDo todo = new ToDo();
        input = input.substring(4).trim();
        if (input.equals("")) {
            throw new DukeIncompleteCommandException();
        }
        todo.task = input.trim();
        todo.isDone = false;
        return todo;
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
    public String stringToSave() {
        return "TODO" + super.toString();
    }
}
