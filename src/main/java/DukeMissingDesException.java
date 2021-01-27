public class DukeMissingDesException extends DukeException{
    private String keyword;

    public DukeMissingDesException(String keyword) {
        super("This is a Duke missing description exception.");
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
