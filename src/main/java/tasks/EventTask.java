package tasks;

public class EventTask extends Task{
    private String timing;

    public EventTask(String description, String timing) {
        super(description, "[E]");
        this.timing = timing;
    }

    public String getTiming() {
        int splitIndex = this.timing.indexOf(" ");
        String at = this.timing.substring(0, splitIndex);
        String time = this.timing.substring(splitIndex);
        return "(" + at + ":" + time + ")";
    }

    public char getType() {
        return type.charAt(1);
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
