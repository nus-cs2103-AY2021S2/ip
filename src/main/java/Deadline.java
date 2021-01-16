public class Deadline extends Task {
    public String dateTime;

    public Deadline(String desc, String dateTime) {
        super(desc);
        this.dateTime = dateTime;
    }

    @Override
    public String getDesc() {
        return this.desc + " (by: " + this.dateTime + ")";
    }

    @Override
    public String getTypeSymbol() {
        return "D";
    }
}
