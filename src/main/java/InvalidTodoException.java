class InvalidTodoException extends DukeException {

    @Override
    public String toString() {
        return "____________________________________________________________\n"
                + "\u2639 OOPS!!! The description of a todo cannot be empty :-( \n"
                + "____________________________________________________________\n";
    }
}
