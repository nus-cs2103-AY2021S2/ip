public class Event extends Task {
    private String date;

    public Event(String input, String date) {
        super(input);
        this.date = date;
    }

    public Event(String input, String date, int done) {
        super(input);
        this.date = date;
        if (done == 1) {
            this.doTask();
        }
    }

    @Override
    public String taskSave() {
        return "E" + super.taskSave() + " | " + date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
