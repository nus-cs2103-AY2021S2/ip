public class DukeNoDescriptionException extends DukeException {
    private final String typeOfTask;

    public DukeNoDescriptionException(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public String toString() {
        return String.format("☹ OOPS!!! The description of a %s cannot be empty.", typeOfTask);
    }
}
