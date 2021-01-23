import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Alice {

    private static final String GREETING = "Hello!";
    private static final String BYE = "See you next time!";
    private static final String TASK_ADD = "Got it. I've added this task:\n%s\nNow you have %d task(s) in the list";
    private static final String TASK_DONE = "Nice! I've marked this task as done:\n%s";
    private static final String TASK_DELETE = "I've deleted this task:\n%s";

    private static final Pattern DONE_REGEX = Pattern.compile("done\\s+(\\d+)");

    private final boolean done;
    private final boolean hasDelta;
    private final String currentMessage;
    private final TaskList data;

    public Alice(TaskList newData, boolean done) {
        this.currentMessage = String.format("Initialized with %d tasks", newData.count());
        this.data = newData;
        this.done = done;
        this.hasDelta = false;
    }

    public Alice(String currentMessage, TaskList newData, boolean done, boolean hasDelta) {
        this.currentMessage = currentMessage;
        this.data = newData;
        this.done = done;
        this.hasDelta = hasDelta;
    }

    public boolean isDone() {
        return done;
    }

    public boolean hasDelta() {
        return hasDelta;
    }

    public TaskList getData() {
        return this.data;
    }

    public String getGreeting() {
        return GREETING;
    }

    private Alice processAdd(String command) {
        Alice newAgent;
        try {
            Task task = TaskBuilder.buildTask(command);
            List<Task> newStore = this.data.getTasks().stream().map(Task::clone).collect(Collectors.toList());
            newStore.add(task);
            String response = String.format(TASK_ADD, task, newStore.size());
            newAgent = new Alice(response, new TaskList(newStore), false, true);
        }
        catch (AliceException aliceException) {
            newAgent = new Alice(aliceException.getMessage(), this.data, this.done, false);
        } catch (IllegalStateException illegalStateException) {
            newAgent = new Alice("Something that was not supposed to happen happened", this.data, this.done, false);
        }
        return newAgent;
    }

    private Alice processBye(String command) {
        return new Alice(BYE, this.data, true, false);
    }

    private Alice processList(String command) {
        String response;
        if (this.data.getTasks().size() <= 0) {
            response = "Your schedule is empty";
        } else {
            response = IntStream.range(0, data.getTasks().size())
                    .mapToObj(i -> (i + 1) + "." + this.data.getTasks().get(i))
                    .collect(Collectors.joining("\n"));
        }
        return new Alice(response, this.data, this.done, false);
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
            if (index < 0 || index > this.data.getTasks().size()) {
                throw new AliceException(String.format("Index %d out of bounds for schedule of size %d.",
                        index, this.data.getTasks().size()));
            }
            List<Task> dataList = this.data.getTasks().stream().map(Task::clone).collect(Collectors.toList());
            dataList.set(index - 1, dataList.get(index - 1).setDone(true));
            String response = String.format(TASK_DONE, dataList.get(index - 1));
            newAgent = new Alice(response, new TaskList(dataList), this.done, true);
        }
        catch (NumberFormatException numberFormatException) {
            newAgent = new Alice("Invalid number", this.data, this.done, false);
        }
        catch (AliceException aliceException) {
            newAgent = new Alice(aliceException.getMessage(), this.data, this.done, false);
        }
        return newAgent;
    }

    private Alice processDelete(String command) {
        Alice newAgent;
        String[] tokens = command.split("\\s+");
        try {
            int index = Integer.parseInt(tokens[1]);
            if (index < 0 || index > this.data.getTasks().size()) {
                throw new AliceException(String.format("Index %d out of bounds for schedule of size %d.",
                        index, this.data.getTasks().size()));
            }
            List<Task> dataList = this.data.getTasks().stream().map(Task::clone).collect(Collectors.toList());
            dataList.remove(index - 1);
            String response = String.format(TASK_DELETE, this.data.getTasks().get(index - 1));
            newAgent = new Alice(response, new TaskList(dataList), this.done, true);
        }
        catch (NumberFormatException numberFormatException) {
            newAgent = new Alice("Invalid number", this.data, this.done, false);
        }
        catch (AliceException aliceException) {
            newAgent = new Alice(aliceException.getMessage(), this.data, this.done, false);
        }
        return newAgent;
    }

    private Alice processEcho(String command) {
        return new Alice(command, this.data, this.done, false);
    }

    public Alice process(String input) {
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

    public String getCurrentMessage() {
        return currentMessage;
    }

    public static String getPrompt() {
        return ">";
    }

    public static void main(String[] args) {

    }
}
