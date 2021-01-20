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
    private final String ADD_TASK = "Got it. I've added this task:\n%s\nNow you have %d task(s) in the list";
    private final String TASK_DONE = "Nice! I've marked this task as done:\n%s";

    private final String currentMessage;
    private final List<Task> store;

    private Duke(String currentMessage, List<Task> newStore, boolean done) {
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
        } else if (tokens[0].equals("todo") || tokens[0].equals("deadline") || tokens[0].equals("event")) {
            List<Task> newStore = new ArrayList<>();
            for (Task t : this.store) {
                newStore.add(t.clone());
            }
            Task t = null;
            switch (tokens[0]) {
                case "todo":
                    t = new TaskTodo(input.substring("todo".length() + 1), false);
                    break;
                case "deadline":
                    String deadline = input.substring(input.indexOf("/by ") + 4);
                    t = new TaskDeadline(input.substring("deadline".length() + 1, input.indexOf(" /by ")),false, deadline);
                    break;
                case "event":
                    String time = input.substring(input.indexOf("/at ") + 4);
                    t = new TaskEvent(input.substring("event".length() + 1, input.indexOf(" /at ")), false, time);
                    break;
            }
            newStore.add(t);
            String response = String.format(ADD_TASK, t, newStore.size());
            newAgent = new Duke(response, newStore, false);
        } else if (tokens[0].equals("done")) {
            List<Task> newStore = new ArrayList<>();
            String response = "";
            Task t;
            for (int i = 0; i <  this.store.size(); i++) {
                if (i + 1 == Integer.parseInt(tokens[1])) {
                    newStore.add(this.store.get(i).setDone(true));
                    response = String.format(TASK_DONE, newStore.get(i));
                } else {
                    newStore.add(this.store.get(i).clone());
                }
            }
            newAgent = new Duke(response, newStore, false);
        } else {
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
        Duke agent = new Duke("", new ArrayList<Task>(), false);
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
