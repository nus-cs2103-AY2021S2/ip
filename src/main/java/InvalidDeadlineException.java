class InvalidDeadlineException extends DukeException {
    protected String description;

    public InvalidDeadlineException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String[] seg = description.split(" ");
        if (description.length() <= 9) {
            return "____________________________________________________________\n"
                    + "\u2639 OOPS!!! The description of a deadline cannot be empty :-( \n"
                    + "____________________________________________________________\n";
        } else {
            return "____________________________________________________________\n"
                    + "\u2639 OOPS!! format for deadline: deadline (task) /by yyyy-MM-dd\n"
                    + "____________________________________________________________\n";
        }
    }

}
