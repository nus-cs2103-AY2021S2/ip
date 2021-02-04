package duke.ui;

public class JavafxUi extends Ui {
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
        printIndentOutput("What can I do for you?");
    }
}
