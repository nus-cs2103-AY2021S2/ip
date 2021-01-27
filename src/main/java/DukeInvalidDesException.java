public class DukeInvalidDesException extends DukeException{
    private String keyword;

    public DukeInvalidDesException(String keyword) {
        super("This is a Duke missing description exception.");
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
