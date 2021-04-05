package duke.task;

public class ToDo extends Task {
    public ToDo(String input) {
        super(input);
    }

    public ToDo(String input, int done, String reminderDate) {
        super(input);
        if (done == 1) {
            this.doTask();
        }
        if (!reminderDate.equals("0")) {
            this.addReminder(new ParseDates().parseString(reminderDate));
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
