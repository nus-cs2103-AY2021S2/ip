public class Deadline extends Task {
    private String date;

    public Deadline(String input, String date) {
        super(input);
        this.date = date;
    }

    public Deadline(String input, String date, int done) {
        super(input);
        this.date = date;
        if (done == 1) {
            this.doTask();
        }
    }

    @Override
    public String taskSave() {
        return "D" + super.taskSave() + " | " + date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
