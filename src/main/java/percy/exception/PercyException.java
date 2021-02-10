package percy.exception;

public class PercyException extends Exception {
    private String s;

    public PercyException() {
    }

    public PercyException(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
