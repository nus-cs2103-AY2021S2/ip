package task;

import simulator.DukeException;

public class Todo extends Task {

    protected String type = "T";

    public Todo(String[] input) throws DukeException {
        super(input);
        if (input.length != 0) {
        for (String s : input) {
            this.description.append(s + " ");
        }
    } else {
        throw new DukeException("☹ OOPS!!! Description of todo cannot be empty, please check!");
    }

    }
    
    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
