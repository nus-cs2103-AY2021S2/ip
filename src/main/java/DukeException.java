public class DukeException extends Exception {

    private boolean unknown;

    public DukeException(String message) {
        super(message);
        if (message.equals("unknown")) {
            unknown = true;
        } else {
            unknown = false;
        }
    }

    public String errorMessage() {
        if (unknown) {
            return " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        } else {
            return "☹ OOPS!!! The description of a " + this.getMessage() + " cannot be empty.";
        }
    }
}
