public class Deadline extends Task {
    private String endDate;

    public Deadline(String desc, String endDate) {
        super(desc);
        this.endDate = endDate;
    }

    public static Deadline newInstance(String args) {
        String[] argArr = args.split("/");
        for (String s: argArr) {
            if (s.startsWith("by ")) {
                return new Deadline(argArr[0], s.substring(3));
            }
        }
        return new Deadline(argArr[0], "N/A");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + endDate + ")";
    }
}
