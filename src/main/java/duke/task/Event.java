package duke;

class Event extends Task{
    private String timeRange;

    public Event(String content,String timeRange){
        super(content);
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString()+ String.format(" (at:%s)", this.parseDate(this.timeRange.strip()));
    }

    @Override
    public String toFileString() {
        String done;
        if (this.getDone()) {
            done = "1";
        } else {
            done = "0";
        }
        String string = "E|"+ done + "|" + this.getDesc() + "|" + this.timeRange;
        return string;
    }
}
