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
}
