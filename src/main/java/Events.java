public class Events extends Task {

    private String date;
    private String startTime;
    private String endTime;

    public Events(String title, String date, String startTime, String endTime) {
        super(title);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return  "\t [E]" + super.toString() + "(by:" + this.date + " " + this.startTime + "-" + this.endTime + ")";
    }
}
