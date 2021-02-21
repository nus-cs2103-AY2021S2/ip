package checklst.task;

import checklst.exception.ChecklstException;

/**
 * The Deadline class represents a basic Task.
 */
public class Todo extends Task {

    protected Todo(String name) {
        super(name);
    }

    protected Todo(String name, boolean completed) {
        super(name, completed);
    }

    /**
     * Creates a Todo Object.
     * @param name - Name of Todo.
     * @return - Todo Object.
     */
    public static Todo makeTodo(String name) throws ChecklstException {
        if (name.equals("")) {
            throw new ChecklstException("Todo needs a name!");
        } else if (name.contains(";")) {
            throw new ChecklstException("Sorry, Todo name cannot contain a \";\"!");
        }
        return new Todo(name);
    }

    /**
     * Parses a saved Todo String.
     * @param input Todo String.
     * @return Todo Object.
     */
    public static Todo parseTodo(String input) {
        String[] splitInput = input.split(" ; ");
        String name = splitInput[2];
        boolean completed = splitInput[1].equals("X") ? true : false;

        return new Todo(name, completed);
    }

    @Override
    public String export() {
        String output = "T ; ";

        if (this.isCompleted) {
            output += "X ; ";
        } else {
            output += "O ; ";
        }

        output += this.name;

        return output;
    }

    @Override
    public Task complete() {
        return new Todo(this.name, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
