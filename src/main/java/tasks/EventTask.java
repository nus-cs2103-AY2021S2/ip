package tasks;

public class EventTask extends Task{
    private String type;
    private String timing;

    public EventTask(String description, String timing) {
        super(description);
        this.type = "[E]";
        this.timing = timing;
    }

    public String getTiming() {
        int splitIndex = this.timing.indexOf(" ");
        String at = this.timing.substring(0, splitIndex);
        String time = this.timing.substring(splitIndex);
        return "(" + at + ":" + time + ")";
    }

    @Override
    public void getStatusAndTask() {
        System.out.println("      " + this.type + this.getStatus() + this.description + this.getTiming());
    }

    @Override
    public String toString() {

        return "       " + this.type + super.toString().trim() + " " + getTiming();
    }
}
