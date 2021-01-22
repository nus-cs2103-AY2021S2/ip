import javax.xml.crypto.Data;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Alice {

    private static final String OS = System.getProperty("os.name").toLowerCase(Locale.ROOT);

    private static final String GREETING = "Hello!";
    private static final String BYE = "See you next time!";
    private static final String TASK_ADD = "Got it. I've added this task:\n%s\nNow you have %d task(s) in the list";
    private static final String TASK_DONE = "Nice! I've marked this task as done:\n%s";
    private static final String TASK_DELETE = "I've deleted this task:\n%s";

    private static final Pattern TODO_REGEX = Pattern.compile("todo\\s+(.*)");
    private static final Pattern DEADLINE_REGEX = Pattern.compile("deadline\\s+(.*)\\s+/by\\s+(.*)");
    private static final Pattern EVENT_REGEX = Pattern.compile("event\\s+(.*)\\s+/at\\s+(.*)");
    private static final Pattern DONE_REGEX = Pattern.compile("done\\s+(\\d+)");

    private final boolean done;
    private final String currentMessage;
    private final List<Task> store;

    private Alice() {
        this.currentMessage = getGreeting();
        byte[] data = DataHandler.loadBytes(getDataPath());
        if (data == null) {
            DataHandler.InitSave(getDataPath());
        }
        this.store = (ArrayList<Task>)DataHandler.deserialize(data, ArrayList.class);
        this.done = false;
    }

    private Alice(String currentMessage, List<Task> newStore, boolean done) {
        this.currentMessage = currentMessage;
        this.store = newStore;
        this.done = done;
    }

    private static String getDataPath() {
        if (OS.contains("win")) {
            return String.join(File.separator, "%USERPROFILE%", ".alice_save", "data", "save_data");
        } else {
            return String.join(File.separator, "~", ".alice_save", "data", "save_data");
        }
    }

    private String getGreeting() {
        return GREETING;
    }

    private Alice processAdd(String command) {
        Alice newAgent;
        Matcher matcher;
        String[] tokens = command.split("\\s+");
        try {
            Task task;
            switch (tokens[0]) {
                case "todo":
                    matcher = TODO_REGEX.matcher(command);
                    if (!matcher.find()) {
                        throw new AliceException("todo Usage: todo [activity]");
                    }
                    task = new TaskTodo(matcher.group(1).trim(), false);
                    break;
                case "deadline":
                    matcher = DEADLINE_REGEX.matcher(command);
                    if (!matcher.find()) {
                        throw new AliceException("deadline Usage: deadline [activity] /by [deadline]");
                    }
                    task = new TaskDeadline(matcher.group(1).trim(), false, matcher.group(2).trim());
                    break;
                case "event":
                    matcher = EVENT_REGEX.matcher(command);
                    if (!matcher.find()) {
                        throw new AliceException("event Usage: event [activity] /at [time]");
                    }
                    task = new TaskEvent(matcher.group(1).trim(), false, matcher.group(2).trim());
                    break;
                default: throw new IllegalStateException();
            }
            List<Task> newStore = this.store.stream().map(Task::clone).collect(Collectors.toList());
            newStore.add(task);
            String response = String.format(TASK_ADD, task, newStore.size());
            newAgent = new Alice(response, newStore, false);
        }
        catch (AliceException aliceException) {
            newAgent = new Alice(aliceException.getMessage(), this.store, this.done);
        } catch (IllegalStateException illegalStateException) {
            newAgent = new Alice("Something that was not supposed to happen happened", this.store, this.done);
        }
        return newAgent;
    }

    private Alice processBye(String command) {
        return new Alice(BYE, this.store, true);
    }

    private Alice processList(String command) {
        String response;
        if (this.store.size() <= 0) {
            response = "Your schedule is empty";
        } else {
            response = IntStream.range(0, store.size())
                    .mapToObj(i -> (i + 1) + "." + this.store.get(i))
                    .collect(Collectors.joining("\n"));
        }
        return new Alice(response, this.store, false);
    }

    private Alice processDone(String command) {
        Alice newAgent;
        Matcher matcher;
        String[] tokens = command.split("\\s+");
        try {
            matcher = DONE_REGEX.matcher(command);
            if (!matcher.find()) {
                throw new AliceException("done Usage: done [index]");
            }
            int index = Integer.parseInt(tokens[1]);
            if (index < 0 || index > this.store.size()) {
                throw new AliceException(
                        String.format("Index %d out of bounds for schedule of size %d.", index, this.store.size()));
            }
            List<Task> newStore = this.store.stream().map(Task::clone).collect(Collectors.toList());
            newStore.set(index - 1, newStore.get(index - 1).setDone(true));
            String response = String.format(TASK_DONE, newStore.get(index - 1));
            newAgent = new Alice(response, newStore, this.done);
        }
        catch (NumberFormatException numberFormatException) {
            newAgent = new Alice("Invalid number", this.store, this.done);
        }
        catch (AliceException aliceException) {
            newAgent = new Alice(aliceException.getMessage(), this.store, this.done);
        }
        return newAgent;
    }

    private Alice processDelete(String command) {
        Alice newAgent;
        String[] tokens = command.split("\\s+");
        try {
            int index = Integer.parseInt(tokens[1]);
            if (index < 0 || index > this.store.size()) {
                throw new AliceException(
                        String.format("Index %d out of bounds for schedule of size %d.", index, this.store.size()));
            }
            List<Task> newStore = this.store.stream().map(t -> t.clone()).collect(Collectors.toList());
            newStore.remove(index - 1);
            String response = String.format(TASK_DELETE, this.store.get(index - 1));
            newAgent = new Alice(response, newStore, this.done);
        }
        catch (NumberFormatException numberFormatException) {
            newAgent = new Alice("Invalid number", this.store, this.done);
        }
        catch (AliceException aliceException) {
            newAgent = new Alice(aliceException.getMessage(), this.store, this.done);
        }
        return newAgent;
    }

    private Alice processEcho(String command) {
        return new Alice(command, this.store, this.done);
    }

    private Alice process(String input) {
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
        List<Task> list = new ArrayList<>();
        list.add(new TaskDeadline("Hello", "a"));
        list.add(new TaskEvent("Hello", "b"));
        list.add(new TaskTodo("todo"));
        @SuppressWarnings("unchecked")
        List<Task> l = (ArrayList<Task>)DataHandler.deserialize(
                DataHandler.serialize(list),
                ArrayList.class
        );
        System.out.println(l);
        Scanner scanner = new Scanner(System.in);
        Alice agent = new Alice();
        System.out.println(agent.getCurrentMessage());
        while (!agent.done) {
            System.out.print(getPrompt());
            try {
                agent = agent.process(scanner.nextLine());
            } catch (NoSuchElementException noSuchElementException) {
                agent = agent.process("bye");
            }
            System.out.println(agent.getCurrentMessage());
        }
    }
}
