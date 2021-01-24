public class Event extends DatedTask {

    public Event(String task, String date) throws DukeException {
        super(task, date);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString();
    }
}


