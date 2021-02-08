package duke;

class Task {
    String task;
    boolean isDone;

    Task() {
    }

    Task(String task) {
        this.task = task.trim();
        isDone = false;
    }

    Task markDone() {
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
    static Task fileReader(String line) {
        if (line.contains("TODO")) {
            return ToDo.fileReader(line);
        } else if (line.contains("DDLN")) {
            return Deadline.fileReader(line);
        } else {
            return Event.fileReader(line);
        }
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }
    public String stringToSave() {
        return "";
    }
}
