public class Deadline extends Task {

    public String date;

    Deadline(String description) {
        super(description.substring(9).split(" /by ")[0]);
        this.date = description.substring(9).split(" /by ")[1];
        isDone = false;
    }

    @Override
    public String getDate() {
        return " (by: " + date + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getDate() + "\n";
    }
}
