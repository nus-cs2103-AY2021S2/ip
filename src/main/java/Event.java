class Event extends Task {
    String date;

    Event(String task, String date) {
        super(task);
        this.date = date;
    }

    Event(String task, Boolean isCompleted, String date){
        super(task, isCompleted);
        this.date = date;
    }

    public static Event readTask(String input) {
        String[] list = input.split(", ", 4);
        return new Event(list[2], Boolean.parseBoolean(list[1]), list[3]);
    }

    public String toCommand() {
        return this.getClass().toString() + ", " + this.getCompleted() + ", " + this.task + ", " + this.date;
    }
    @Override
    public String toString() {
        return "[E]" + this.completedBox() + this.task + "(at: " + this.date + ")";
    }
}