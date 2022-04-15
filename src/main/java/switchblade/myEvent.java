package switchblade;

/**
 * Tasks with 2 dates will use an Event, class is named switchblade.myEvent to avoid confusion with Java defined one
 *
 * @author leeyueyang
 */
public class myEvent extends Task {
    DateTime startDatetime, endDatetime;

    myEvent(String description, String startDatetimeInput, String endDatetimeInput) {
        super(description);

        String[] startDatetimeArr = startDatetimeInput.split("\\s+");
        String[] endDatetimeArr = endDatetimeInput.split("\\s+");

        if (startDatetimeArr.length == 2 && endDatetimeArr.length == 2) {
            this.startDatetime = new DateTime(startDatetimeArr[0], startDatetimeArr[1]);
            this.endDatetime = new DateTime(endDatetimeArr[0], endDatetimeArr[1]);
        } else {
            this.startDatetime = new DateTime(startDatetimeArr[0]);
            this.endDatetime = new DateTime(endDatetimeArr[0]);
        }
    }

    myEvent(String description, String[] datetimeInputArr, Boolean completion) {
        super(description, completion);

        String[] startDatetimeArr = datetimeInputArr[0].split(", ");
        String[] endDatetimeArr = datetimeInputArr[1].split(", ");

        if (startDatetimeArr.length == 2 && endDatetimeArr.length == 2) {
            this.startDatetime = new DateTime(startDatetimeArr[0], startDatetimeArr[1]);
            this.endDatetime = new DateTime(endDatetimeArr[0], endDatetimeArr[1]);
        } else {
            this.startDatetime = new DateTime(startDatetimeArr[0]);
            this.endDatetime = new DateTime(endDatetimeArr[0]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // changed according to completion
        if (this.completed) {
            sb.append("[E] [x] ");
        } else {
            sb.append("[E] [ ] ");
        }

        sb.append(this.description
                + " at: "
                + this.startDatetime
                + " to "
                + this.endDatetime);
        return sb.toString();
    }
}
