public class event extends listItem {
    private final String date;

    public event(String task, String inputDate) {
        super(task);
        this.date = inputDate;
    }

    public event(String task, String inputDate, boolean isDone) {
        super(task, isDone);
        this.date = inputDate;
    }

    @Override
    public listItem markAsDone(){
        return new event(super.getTask(), this.date, true);
    }

    @Override
    public String toString() {
        return "[E]" + (super.isDoneStatus() == true ? "[X] " : "[ ] ") + super.getTask() + " (at: " + date + ")";
    }
}
