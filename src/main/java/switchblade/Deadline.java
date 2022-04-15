package switchblade;

/**
 * Tasks with 1 datetime will use a switchblade.Deadline object
 *
 * @author leeyueyang
 */
public class Deadline extends Task{
    DateTime datetime;

    Deadline(String description, String datetimeInput) {
        super(description);

        String[] datetime = datetimeInput.split("\\s", 2);
        if (datetime.length == 2) {
            this.datetime = new DateTime(datetime[0], datetime[1]);
        } else {
            this.datetime = new DateTime(datetime[0]);
        }
    }

    Deadline(String description, String datetimeInput, Boolean completion) {
        super(description, completion);

        String[] datetime = datetimeInput.split(", ", 2);
        if (datetime.length == 2) {
            this.datetime = new DateTime(datetime[0], datetime[1]);
        } else {
            this.datetime = new DateTime(datetime[0]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.completed) {
            sb.append("[D] [x] ");
        } else {
            sb.append("[D] [ ] ");
        }

        sb.append(this.description).append(" by: ").append(this.datetime);
        return sb.toString();
    }
}
