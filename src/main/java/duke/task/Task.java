package duke.task;

import java.time.LocalDate;

public abstract class Task {
    private String tag;
    private boolean isComplete;
    private String detail;
    protected LocalDate date;

    protected Task(String tag, String detail, LocalDate date) {
        this.tag = tag;
        this.isComplete = false;
        this.detail = detail;
        this.date = date;
    }

    public void setCompletion(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getTag() {
        return this.tag;
    }

    public boolean checkIsComplete() {
        return this.isComplete;
    }

    public String getDetail() {
        return this.detail;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String status = this.isComplete ? "[X] " : "[ ] ";
        return "[" + this.tag + "]" + status + this.detail;
    }
}
