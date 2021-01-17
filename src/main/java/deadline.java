public class deadline extends listItem {
    private final String date;

    public deadline(String task, String inputDate) {
        super(task);
        this.date = inputDate;
    }

    public deadline(String task, String inputDate, boolean isDone) {
        super(task, isDone);
        this.date = inputDate;
    }

    @Override
    public listItem markAsDone(){
        return new deadline(super.getTask(), this.date, true);
    }

    @Override
    public String toString() {
        return "[D]" + (super.isDoneStatus() == true ? "[X] " : "[ ] ") + super.getTask() + " (by: " + date + ")";
    }
}
