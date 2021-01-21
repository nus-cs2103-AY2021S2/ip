package percy.exception;

public abstract class PercyException extends Exception {
    public PercyException() {
    }

    @Override
    public abstract String toString();
}
