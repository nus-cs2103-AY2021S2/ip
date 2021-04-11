package duke.ui;

public class JavafxUi extends Ui {
    public static final String INTRO_MESSAGE = "What can I do for you?";

    private String replyString;

    public JavafxUi() {
        this.replyString = "";
    }

    public String getReplyString() {
        return replyString;
    }

    public void resetReplyString() {
        this.replyString = "";
    }

    @Override
    public void printIndentOutput(String output) {
        this.replyString += output + "\n";
    }

    @Override
    public void printHorizontalLine() {
        // Do nothing
    }

    @Override
    public void printIntro() {
        printIndentOutput(INTRO_MESSAGE);
    }
}
