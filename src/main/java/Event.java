public class Event extends Task {

    public Event(String i) {
        super(i);
    }

    @Override
    public String toString() {
        String temp = String.format("[E]%s", super.toString());
        return formatString(temp, "/at");
    }
}
