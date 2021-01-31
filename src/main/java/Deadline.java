class Deadline extends Task {

    public Deadline(String description, String date, String time) {
        super(description, date, time, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
