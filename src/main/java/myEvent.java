public class myEvent extends Task {
    String datetime;

    myEvent(String description, String datetime) {
        super(description);
        this.datetime = datetime.stripLeading().stripTrailing();
    }

    myEvent(String description, String datetime, Boolean completion) {
        super(description, completion);
        this.datetime = datetime.stripLeading().stripTrailing();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.completed)
            sb.append("[E] [x] ");
        else
            sb.append("[E] [ ] ");

        sb.append(this.description
                + " at: "
                + this.datetime);
        return sb.toString();
    }
}
