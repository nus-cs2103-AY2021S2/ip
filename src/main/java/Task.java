enum TaskType
{
    ToDo, Deadline, Event;
}

public class Task {
    private final String description;
    private boolean isDone;
    private final int id;

    Task(String description, int id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
    }

    int getID() {
        return this.id;
    }

    void markAsDone() {
        this.isDone = true;
    }

    String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
