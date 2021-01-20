import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {

    private final boolean done;

    private final String GREETING = "Hello :)";
    private final String BYE = "See you next time :)";

    private final String currentMessage;
    private final List<String> store;

    private Duke(String currentMessage, List<String> newStore, boolean done) {
        this.currentMessage = currentMessage;
        this.store = newStore;
        this.done = done;
        if (this.done) {
            currentMessage = BYE;
        }
    }

    private String getGreeting() {
        return GREETING;
    }

    private Duke process(String input) {
        Duke newAgent;
        String[] tokens = input.split(" ");
        if (input.equals("bye")) {
            newAgent = new Duke(BYE, this.store, true);
        } else if (input.equals("list")) {
            String response = IntStream.range(0, store.size())
                    .mapToObj(i -> (i + 1) + ". " + this.store.get(i))
                    .collect(Collectors.joining("\n"));
            newAgent = new Duke(response, this.store, false);
        }
        else if (tokens[0].equals("add")) {
            List<String> newStore = new ArrayList<>(this.store);
            String content = input.substring(4);
            newStore.add(content);
            String response = "added: " + content;
            newAgent = new Duke(response, newStore, false);
        }
        else {
            newAgent = new Duke(input, this.store, false);
        }
        return newAgent;
    }

    private String getCurrentMessage() {
        return currentMessage;
    }

    private static String getPrompt() {
        return ">";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke agent = new Duke("", new ArrayList<String>(), false);
        System.out.println(agent.getGreeting());
        while (!agent.done) {
            System.out.print(getPrompt());
            try {
                agent = agent.process(scanner.nextLine());
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("EOF");
                System.exit(0);
            }
            System.out.println(agent.getCurrentMessage());
        }
    }
}
