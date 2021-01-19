public class Events extends Task {

    private String date;
  //  private String startTime;
 //   private String endTime;

    public Events(String title, String date) {
        super(title);
        this.date = date;
    }

    @Override
    public String toString() {
        return  "[E]" + super.toString() + "(at:" + this.date + ")";
    }
}
