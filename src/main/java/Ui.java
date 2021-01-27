public class Ui {
    private static final String SPACING = "\t\t";
    private static final String GO_TO_NEXT_LINE = "\n";

    public Ui() {

    }

    public void showError(String errorMsg) {
        System.out.println(SPACING + errorMsg + GO_TO_NEXT_LINE);
    }

    public void welcomeMsg() {
        String text = SPACING + "Hello! I'm Duke"
                    + GO_TO_NEXT_LINE + SPACING
                    + "What can I do for you?"
                    + GO_TO_NEXT_LINE;
        System.out.println(text);
    }

    public void say(String msg) {
        System.out.println(SPACING + msg + GO_TO_NEXT_LINE);
    }

    public void loadingSuccess() {
        System.out.println(SPACING + "Existing file successfully loaded" + GO_TO_NEXT_LINE);
    }

    public void loadingFailure() {
        System.out.println(SPACING + "Existing file could not be found. New file will be created"
                + GO_TO_NEXT_LINE);
    }

    public void byeMsg() {
        System.out.println(SPACING + "See you again soon!");
    }

}
