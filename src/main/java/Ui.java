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

    public void printAddMsg(Task task, int tasksSize) {
        System.out.println(BORDER);
        System.out.println("Got it meow. I've added this task:");
        System.out.printf("  [%s][%s] %s\n", task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
        System.out.printf("Now you have %d tasks in the list.\n", tasksSize);
        System.out.println(BORDER + "\n");
    }

    public void printError(String msg) {
        printMsg(String.format("ERROR MEOW! %s", msg));
    }
}
