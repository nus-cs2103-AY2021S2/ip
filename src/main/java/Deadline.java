public class Deadline extends Task{
    String datetime;

    Deadline(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.completed)
            sb.append("[D] [x] ");
        else
            sb.append("[D] [ ] ");

        sb.append(this.description
                + " by: "
                + this.datetime);
        return sb.toString();
    }
}
