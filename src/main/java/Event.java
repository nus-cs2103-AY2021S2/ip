public class Event extends Task {
    public String event;

    public Event(String str) {
        super(str);//super must be 1st line..
        String[] split = str.split("/");
        if(split.length < 2) {
            throw new IllegalArgumentException();
        }
        this.task = split[0].trim();
        this.event = split[1].trim();
    }

    public Event(String[] str, boolean isDone) {//call from harddisc
        super(str[2], isDone);
        this.event = str[3];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +" "+ "(at: " + event + ")";
    }
}
