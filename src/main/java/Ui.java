public class Ui {
    private static final String BORDER = "___________________________________________________________";

    public Ui() {

    }

    public void printMsg(String msg) {
        System.out.println(BORDER);
        System.out.println(msg);
        System.out.println(BORDER + "\n");
    }

    public void printWelcomeMsg(String botName) {
        printMsg(String.format("Meow, I'm %s\nWhat can I do for you today?", botName));
    }

    public void printGoodbyeMsg() {
        printMsg("Meow. Hope to see you again soon!");
    }

    public void printError(String msg) {
        printMsg(String.format("ERROR MEOW! %s", msg));
    }
}
