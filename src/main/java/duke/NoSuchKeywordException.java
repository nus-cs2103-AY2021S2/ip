package duke;

public class NoSuchKeywordException extends DukeException {
    private final String keyword;

    public NoSuchKeywordException(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the string representation of the exception where no task containing the keyword is found.
     *
     * @return String.
     */
    @Override
    public String toString() {
        String str = "I am afraid to inform you that I could not find a task that matched '" + keyword
                + "'.\nPerhaps you should consider a better word to search.";
        return str;
    }
}
