public class Task {
    String description;
    private boolean completed;

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
            sb.append("[x] ");
        else
            sb.append("[ ] ");

        sb.append(this.description);
        return sb.toString();
    }
}
