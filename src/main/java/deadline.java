public class deadline extends listItem {
    private final String date;

    public deadline(String task, String inputDate) {
        super(task);
        this.date = inputDate;
    }

    @Override
    public String toString() {
        return "[D]" + (super.isDoneStatus() == true ? "[X] " : "[ ] ") + super.getTask() + " (by: " + date + ")";
    }
}
