public class EmptyTaskException extends Exception{
    String type;

    public EmptyTaskException(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a " + type + " cannot be empty.";
    }
}
