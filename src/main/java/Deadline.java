public class Deadline extends Task {

    public Deadline(String i) {
        super(i);
    }

    @Override
    public String toString() {
        String temp = String.format("[D]%s", super.toString());
        if (temp.contains("/by")) {
            temp = temp.replace("/by", "(by:") + ")";
        }
        return temp;
    }
}
