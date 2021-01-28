package Duke.Exception;

public class EmptyFindContent extends Exception {
    @Override
    public String getMessage() {
        return "Find command must follow by a keyword!";
    }
}
