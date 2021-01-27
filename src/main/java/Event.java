public class Event extends Task {
    protected String at;

    public Event(String description, int status, String at) {
        super(description,status);
        this.at = at;
    }

    @Override
    public String toTxt(){
        return "E " + super.toTxt() + " | " + at + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
