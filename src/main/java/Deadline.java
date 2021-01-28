public class Deadline extends Task {

    public Deadline(String i) {
        super("D", i);
    }

    public Deadline(String[] arr) {
        super("D", arr);
    }

    @Override
    public String toString() {
        if (this.getDate().equals(" ")) {
            return super.toString();
        } else {
            return String.format("%s (by: %s)", super.toString(), this.getDate());
        }
    }

}
