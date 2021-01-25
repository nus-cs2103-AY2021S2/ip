public class Task {
    protected String description;
    protected boolean completed;

    Task() {
        description = "";
        completed = false;
    }

    Task(String description) {
        this.description = description.stripLeading().stripTrailing();
        completed = false;
    }

    Task(String description, Boolean completion) {
        this.description = description.stripLeading().stripTrailing();
        this.completed = completion;
    }

    public void markCompleted() {
        completed = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean getCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.completed)
            sb.append("[T] [x] ");
        else
            sb.append("[T] [ ] ");

        sb.append(this.description);
        return sb.toString();
    }
}
