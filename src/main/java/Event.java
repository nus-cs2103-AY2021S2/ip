public class Event extends Task {
    protected String at;

    public Event(String des, String at) {
        super(des);
        this.at = at;
    }

    /**
     * makes an event with the string given
     * @param line input string that will be split into the task description and date
     * @return an Event
     * @throws DukeException
     */

    public static Event makeEvent(String line) throws DukeException {
        if (line.equals("")) {
            throw new DukeException("☹ OOPS!!!The description of a todo cannot be empty.\n");
        } else {
            String[] split = line.split("/at");
            String task = split[0];
            String date = split[1];
            return new Event(task, date);
        }
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
