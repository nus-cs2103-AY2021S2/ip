class InvalidTodoException extends DukeException {

    @Override
    public String toString() {
        return "\u2639 OOPS!!! The description of a todo cannot be empty...";
    }
}
