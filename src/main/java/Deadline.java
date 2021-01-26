class Deadline extends Task {
    String date;

    Deadline(String t, String due) {
        super(t);
        this.date = due;
    }

    @Override
    public String toString() {
        return "[D]" + this.completedBox() + this.task + "(by: " + this.date + ")";
    }
}