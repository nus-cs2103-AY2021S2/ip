public class Event extends Task{
    private String timeRange;

    public Event(String content,String timeRange){
        super(content);
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString()+ String.format(" (at:%s)", this.timeRange);
    }
}
