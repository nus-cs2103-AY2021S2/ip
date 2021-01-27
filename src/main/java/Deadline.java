class Deadline extends Task {
    String date;

    Deadline(String t, String due) {
        super(t);
        this.date = due;
    }

    Deadline(String task, Boolean isCompleted, String dueDate) {
        super(task, isCompleted); 
        this.date = dueDate;
    }

    public static Deadline readTask(String input) {
        String[] list = input.split(", ", 4);
        return new Deadline(list[2], Boolean.parseBoolean(list[1]), list[3]);
    }

    public String toCommand() {
        return this.getClass().toString() + ", " + this.getCompleted() + ", " + this.task + ", " + this.date;
    }

    @Override
    public String toString() {
        return "[D]" + this.completedBox() + this.task + "(by: " + this.date + ")";
    }
}