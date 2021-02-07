class InvalidEventException extends DukeException{
    protected String description;

    public InvalidEventException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String[] seg = description.split(" ");
        if (description.length() <= 6) {
            return "\u2639 OOPS!!! The description of an event cannot be empty...";
        } else {
            return "\u2639 OOPS!! format for event: event (task) /at yyyy-MM-ddTHH:mm";
        }
    }

}
