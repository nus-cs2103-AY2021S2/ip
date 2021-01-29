public class EmptyArgumentException extends Exception {
    @Override
    public String getMessage() {
        return "cannot have empty description";
    }
}
