package duke.task;

public class ToDo extends Task {
    public ToDo(String input) {
        super(input);
    }

    public ToDo(String input, int done) {
        super(input);
        if (done == 1) {
            this.doTask();
        }
    }

    @Override
    public String saveTask() {
        return "T" + super.saveTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
