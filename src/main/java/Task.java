import java.util.*;

public class Task {
    private final String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false; // tasks always start as not done
    }

    public boolean isDone() { return done; }

    public void finish() {
        if (this.done) {
            System.out.println("That task's already done!");
        } else {
            this.done = true;
            System.out.println("Congrats! The following task has been marked as done:");
            System.out.println("  " + this.toString());
        }
    }

    private String getStatus() {
        if (this.done) {
            return "X";
        } else {
            return " ";
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), description);
    }
}
