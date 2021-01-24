//Event Class with Date and Duration
 public class Events extends Task{
    protected String timing;
    public Events(String name, String timing) {
        super(name);
        this.timing = timing;
    }
    public String getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString()+" (at: "+timing+")";
    }
}
