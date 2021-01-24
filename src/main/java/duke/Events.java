package duke;

public class Events extends Task {

    private String date;
  //  private String startTime;
 //   private String endTime;

    public Events(String title, Boolean b, String data) {
        super(title, b);
        this.date = data;
    }

    public Events(String title, String date) {
        super(title);
        this.date = date;
    }

    @Override
    public String changeFormat(){
        return "E" + super.changeFormat() + ":" + this.date;
    }

    @Override
    public String toString() {
        return  "[E]" + super.toString() + "(at:" + this.date + ")";
    }
}
