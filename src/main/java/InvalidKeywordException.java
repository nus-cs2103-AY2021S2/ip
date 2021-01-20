public class InvalidKeywordException extends Exception {
    private static final String SPACE = "     ";
    private static final String LINE = "\n     <<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>\n";

    public InvalidKeywordException() {
        super(LINE + SPACE + "The supported keywords are: todo, deadline, event, done, list, delete, bye only." + LINE);
    }

    public void printMessage() {
        System.out.println(super.getMessage());
    }
}
