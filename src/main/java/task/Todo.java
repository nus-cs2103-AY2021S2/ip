package task;

import simulator.DukeException;

public class Todo extends Task {

    public Todo(String input) throws DukeException {
        this.type = "T";
        if (input.length() != 0) {
            this.description = input;
        } else {
            throw new DukeException("☹ OOPS!!! Description of todo cannot be empty, please check!");
        }

    }

    public Todo(String status, String input) throws DukeException {
        this.type = "T";
        this.isDone = status.equals("complete");
        if (input.length() != 0) {
            this.description = input;
        } else {
            throw new DukeException("☹ OOPS!!! Description of todo cannot be empty, please check!");
        }
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
