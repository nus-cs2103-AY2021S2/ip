package duke;

public class Task {
    String task;
    boolean isDone;

    Task() {
    }

    Task(String task) {
        this.task = task.trim();
        isDone = false;
    }

    public Task markDone() {
        isDone = true;
        return this;
    }
    static Task parseInput(String input) throws DukeIncompleteCommandException {
        Task task;
        if (input.contains("todo")) {
            task = ToDo.parseInput(input);
        } else if (input.contains("deadline")) {
            task = Deadline.parseInput(input);
        } else {
            task = Event.parseInput(input);
        }
        return task;
    }


    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }
    public String toFileString() {
        return toString();
    }
}
