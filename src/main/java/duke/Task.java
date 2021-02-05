package duke;

public class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatus() {
        String isDoneSymbol = isDone ? "[X] " : "[ ] ";
        return isDoneSymbol + this.description;
    }

    public String saveStatus() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.description + "\n";
    }

    public boolean doesDescriptionContain(String word) {
        return this.description.contains(word);
    }
}
