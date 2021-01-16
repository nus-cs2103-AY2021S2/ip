public class Events extends Task{
    protected String timing;
    public Events(String name, String timing) {
        super(name);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString()+" (at: "+timing+")";
    }
}
