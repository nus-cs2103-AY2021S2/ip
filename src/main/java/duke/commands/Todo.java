package duke.commands;

import duke.tasks.Task;

/**
 * An implementation of the duke.tasks.Task class that represents To-Do Tasks.
 * <p>
 * To-Do tasks are tasks that only take a description and track whether they are done or not.
 * <p>
 * The duke.commands.Todo class is visually represented with the prefix: [T]
 *
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
