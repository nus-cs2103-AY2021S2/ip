package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public boolean contains(String keyword) {
        String[] descriptionArray = this.description.split(" ");
        for (String s : descriptionArray) {
            if (keyword.equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description;
    }
}
