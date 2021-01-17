
public class EventTask extends Task {

    private String time;

    public EventTask(String info, String time) {
        super(info);
        this.time = time;
    }

    public Object getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }

}
