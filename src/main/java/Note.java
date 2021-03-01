/**
 * The Note class is a child class of the Task Class,
 * it specifies the task as an Event using [N]
 */
class Note extends Task {

    public Note(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[N]" + super.toString();
    }
}
