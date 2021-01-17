public class Deadline extends Task {

    public String date;

    Deadline(String title) {
        super(title.substring(9).split(" /by ")[0]);
        this.date = title.substring(9).split(" /by ")[1];
        isDone = false;
    }

    @Override
    public String getDate() {
        return " (by: " + date + ")";
    }

    @Override
    public String toString() {
        String check = isDone ? "[X] " : "[ ] ";
        return "[D]" + check + title + getDate() + "\n";
    }
}
