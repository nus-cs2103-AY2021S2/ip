package task;


import java.time.LocalDate;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String parseToCSVRow() {
        return "T," + super.isDone() + "," + super.getTaskName();
    }

    @Override
    public LocalDate getTaskTime() {
        return null;
    }
}
