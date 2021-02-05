public class Deadline extends Task {

    Deadline(String description) {
        super(description);
    }

    Deadline(String description, String eventDate) {
        super(description, eventDate);
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s(by:%s)", this.getTaskType(),
                this.getStatusIcon(), this.getDescription(), this.getEventDate());
    }


}
