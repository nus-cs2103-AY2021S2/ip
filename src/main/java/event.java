public class event extends listItem {
    private final String date;

    public event(String task, String inputDate) {
        super(task);
        this.date = inputDate;
    }

    @Override
    public String toString() {
        return "[E]" + (super.isDoneStatus() == true ? "[X] " : "[ ] ") + super.getTask() + " (at: " + date + ")";
    }
}
