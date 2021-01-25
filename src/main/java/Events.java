public class Events extends Task {
    String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    public Events(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }

    @Override
    public String getEmptyDescError() {
        return "Oops! Description of event " + super.getEmptyDescError();
    }

    @Override
    public String formatData() {
        return "E | " + super.formatData() + " | " + at;
    }
}
