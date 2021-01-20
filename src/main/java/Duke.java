import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {

    private class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    private final boolean done;

    private final String GREETING = "Hello :)";
    private final String BYE = "See you next time :)";
    private final String TASK_ADD = "Got it. I've added this task:\n%s\nNow you have %d task(s) in the list";
    private final String TASK_DONE = "Nice! I've marked this task as done:\n%s";
    private final String TASK_DELETE = "I've deleted this task:\n%s";

    private static final Pattern TODO_REGEX = Pattern.compile("todo\\s+(.*)");
    private static final Pattern DEADLINE_REGEX = Pattern.compile("deadline\\s+(.*)\\s+/by\\s+(.*)");
    private static final Pattern EVENT_REGEX = Pattern.compile("event\\s+(.*)\\s+/at\\s+(.*)");

    private final String currentMessage;
    private final List<Task> store;

    private Duke(String currentMessage, List<Task> newStore, boolean done) {
        this.currentMessage = currentMessage;
        this.store = newStore;
        this.done = done;
    }

    private String getGreeting() {
        return GREETING;
    }

    private Duke processAdd(String command) {
        Duke newAgent;
        Matcher matcher;
        String[] tokens = command.split("\\s+");
        try {
            Task task;
            switch (tokens[0]) {
                case "todo":
                    matcher = TODO_REGEX.matcher(command);
                    if (!matcher.find()) {
                        throw new DukeException("todo Usage: todo [activity]");
                    }
                    task = new TaskTodo(matcher.group(1), false);
                    break;
                case "deadline":
                    matcher = DEADLINE_REGEX.matcher(command);
                    if (!matcher.find()) {
                        throw new DukeException("deadline Usage: deadline [activity] /by [deadline]");
                    }
                    task = new TaskDeadline(matcher.group(1), false, matcher.group(2));
                    break;
                case "event":
                    matcher = EVENT_REGEX.matcher(command);
                    if (!matcher.find()) {
                        throw new DukeException("event Usage: event [activity] /at [time]");
                    }
                    task = new TaskEvent(matcher.group(1), false, matcher.group(2));
                    break;
                default: throw new IllegalStateException();
            }
            List<Task> newStore = this.store.stream().map(t -> t.clone()).collect(Collectors.toList());
            newStore.add(task);
            String response = String.format(TASK_ADD, task, newStore.size());
            newAgent = new Duke(response, newStore, false);
        }
        catch (DukeException dukeException) {
            newAgent = new Duke(dukeException.getMessage(), this.store, this.done);
        } catch (IllegalStateException illegalStateException) {
            newAgent = new Duke("Something that was not supposed to happen happened", this.store, this.done);
        }
        return newAgent;
    }

    private Duke processBye(String command) {
        return new Duke(BYE, this.store, true);
    }

    private Duke processList(String command) {
        String response;
        if (this.store.size() <= 0) {
            response = "Your schedule is empty";
        } else {
            response = IntStream.range(0, store.size())
                    .mapToObj(i -> (i + 1) + "." + this.store.get(i))
                    .collect(Collectors.joining("\n"));
        }
        return new Duke(response, this.store, false);
    }

    private Duke processDone(String command) {
        Duke newAgent;
        String[] tokens = command.split("\\s+");
        try {
            int index = Integer.parseInt(tokens[1]);
            if (index < 0 || index > this.store.size()) {
                throw new DukeException(
                        String.format("Index %d out of bounds for schedule of size %d.", index, this.store.size()));
            }
            List<Task> newStore = this.store.stream().map(t -> t.clone()).collect(Collectors.toList());
            newStore.set(index - 1, newStore.get(index - 1).setDone(true));
            String response = String.format(TASK_DONE, this.store.get(index - 1));
            newAgent = new Duke(TASK_DONE, newStore, this.done);
        }
        catch (NumberFormatException numberFormatException) {
            newAgent = new Duke("Invalid number", this.store, this.done);
        }
        catch (DukeException dukeException) {
            newAgent = new Duke(dukeException.getMessage(), this.store, this.done);
        }
        return newAgent;
    }

    private Duke processDelete(String command) {
        Duke newAgent;
        String[] tokens = command.split("\\s+");
        try {
            int index = Integer.parseInt(tokens[1]);
            if (index < 0 || index > this.store.size()) {
                throw new DukeException(
                        String.format("Index %d out of bounds for schedule of size %d.", index, this.store.size()));
            }
            List<Task> newStore = this.store.stream().map(t -> t.clone()).collect(Collectors.toList());
            newStore.remove(index - 1);
            String response = String.format(TASK_DELETE, this.store.get(index - 1));
            newAgent = new Duke(response, newStore, this.done);
        }
        catch (NumberFormatException numberFormatException) {
            newAgent = new Duke("Invalid number", this.store, this.done);
        }
        catch (DukeException dukeException) {
            newAgent = new Duke(dukeException.getMessage(), this.store, this.done);
        }
        return newAgent;
    }

    private Duke processEcho(String command) {
        return new Duke(command, this.store, this.done);
    }

    private Duke process(String input) {
        String[] tokens = input.split("\\s+");
        switch (tokens[0]) {
            case "todo":
            case "deadline":
            case "event":
                return processAdd(input);
            case "done":
                return processDone(input);
            case "delete":
                return processDelete(input);
            case "bye":
                return processBye(input);
            case "list":
                return processList(input);
            default:
                return processEcho(input);
        }
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
