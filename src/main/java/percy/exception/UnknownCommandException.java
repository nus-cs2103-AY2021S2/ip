package percy.exception;

public class UnknownCommandException extends PercyException {
    public UnknownCommandException() {
    }

    @Override
    public String toString() {
        return " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
