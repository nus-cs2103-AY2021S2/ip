class InvalidEventException extends DukeException{
    protected String description;

    public InvalidEventException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String[] seg = description.split(" ");
        if (description.length() <= 6) {
            return "____________________________________________________________\n"
                    + "\u2639 OOPS!!! The description of an event cannot be empty :-( \n"
                    + "____________________________________________________________\n";
        } else {
            return "____________________________________________________________\n"
                    + "\u2639 OOPS!! format for event: event (task) /at yyyy-MM-ddTHH:mm\n"
                    + "____________________________________________________________\n";
        }
    }

}
