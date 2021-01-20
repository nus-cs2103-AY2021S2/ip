import java.util.Scanner;

public class Duke {

    private final boolean done;

    private final String GREETING = "Hello :)";
    private final String BYE = "See you next time :)";

    private final String currentMessage;

    public Duke(boolean done) {
        this.done = done;
        if (this.done) {
            currentMessage = BYE;
        } else {
            currentMessage = GREETING;
        }
    }

    public Duke(String currentMessage, boolean done) {
        this.currentMessage = currentMessage;
        this.done = done;
    }

    public String getGreeting() {
        return GREETING;
    }

    public Duke getResponse(String input) {
        if (!input.equals("bye")) {
            return new Duke(input, false);
        } else {
            return new Duke(true);
        }
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public static String getPrompt() {
        return ">";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke agent = new Duke(false);
        System.out.println(agent.getGreeting());
        while (!agent.done) {
            System.out.print(getPrompt());
            agent = agent.getResponse(scanner.nextLine());
            System.out.println(agent.getCurrentMessage());
        }
    }
}
