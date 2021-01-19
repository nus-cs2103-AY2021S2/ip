public class Task {
    String description;
    protected boolean completed;

    Task() {
        description = "";
        completed = false;
    }

    Task(String description) {
        this.description = description;
        completed = false;
    }

    public void markCompleted() {
        completed = true;
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
