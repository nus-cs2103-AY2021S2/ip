import java.time.LocalDate;
import java.util.ArrayList;

// DEALS WITH MAKING SENSE OF USER'S COMMAND
public class Parser {

    protected Storage storage;
    protected Ui ui;
    protected TaskList tasks;

    public Parser(Storage storage, Ui ui, TaskList tasks) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
    }

    static String parseCommandType(String command) {
        String[] tokenizedCommand = command.split(" ");
        return tokenizedCommand[0];
    }

    static int parseTaskIndex(String command) {
        String[] tokenizedCommand = command.split(" ");
        return Integer.parseInt(tokenizedCommand[1]);
    }

    static String parseTodoDescription(String command) {
        return command.substring(5);
    }

    static String parseDeadlineDescription(String command) {
        return command.substring(9).split(" /by ")[0];
    }

    static LocalDate parseDeadlineDate(String command) {
        String stringDate = command.substring(9).split(" /by ")[1];
        return LocalDate.parse(stringDate);
    }

    static String parseEventDescription(String command) {
        return command.substring(6).split(" /at ")[0];
    }

    static String parseEventDate(String command) {
        return command.substring(6).split(" /at ")[1];
    }

    static String parseKeyword(String command) {
        return command.substring(5);
    }

    static boolean hasSaved(String command) {
        return command.equals("save");
    }

    static boolean hasExited(String command) {
        return command.equals("exit");
    }

    static boolean isList(String commandType) {
        return commandType.equals("list");
    }

    static boolean isClear(String commandType) {
        return commandType.equals("clear");
    }

    static boolean isDone(String commandType) {
        return commandType.equals("done");
    }

    static boolean isTodo(String commandType) {
        return commandType.equals("todo");
    }

    static boolean isDeadline(String commandType) {
        return commandType.equals("deadline");
    }

    static boolean isEvent(String commandType) {
        return commandType.equals("event");
    }

    static boolean isDelete(String commandType) {
        return commandType.equals("delete");
    }

    static boolean isFind(String commandType) {
        return commandType.equals("find");
    }

    private Task getTask(String command) {
        int taskIndex = Parser.parseTaskIndex(command) - 1;
        return this.tasks.list.get(taskIndex);
    }

    private void clearList() {
        this.tasks.list.clear();
    }

    private ArrayList<Task> getMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.list) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    private void addTodoTask(String command) {
        String description = Parser.parseTodoDescription(command);
        Task task = new Todo(description);
        this.tasks.addTask(task);
    }

    private void addDeadlineTask(String command) {
        String deadlineDescription = Parser.parseDeadlineDescription(command);
        LocalDate deadlineDate = Parser.parseDeadlineDate(command); // INPUT DATE IS YYYY-MM-DD
        Task task = new Deadline(deadlineDescription, deadlineDate);
        this.tasks.addTask(task);
    }

    private void addEventTask(String command) {
        String eventDescription = Parser.parseEventDescription(command);
        String eventDate = Parser.parseEventDate(command);
        Task task = new Event(eventDescription, eventDate);
        this.tasks.addTask(task);
    }

    private void deleteTask(String command) {
        int taskIndex = Parser.parseTaskIndex(command) - 1;
        tasks.removeTask(taskIndex);
    }

    public String parseCommand(String command) {
        assert !command.isEmpty() : "Command is empty.";
        String commandType = Parser.parseCommandType(command);
        try {
            if (Parser.isList(commandType)) {
                // SHOW LIST
                return this.ui.showList(tasks.list);
            } else if (Parser.isClear(commandType)) {
                // CLEAR LIST
                clearList();
                return ui.showClear();
            } else if (Parser.isDone(commandType)) {
                // MARK TASK AS COMPLETE
                Task completedTask = getTask(command);
                completedTask.markAsDone();
                return ui.showDone(completedTask);
            } else if (Parser.isTodo(commandType)) {
                // ADD TODO TASK
                try {
                    addTodoTask(command);
                    return ui.showAddTask(tasks.list);
                } catch (StringIndexOutOfBoundsException e) {
                    return ui.showInvalidTodo();
                }
            } else if (Parser.isDeadline(commandType)) {
                // ADD DEADLINE TASK
                addDeadlineTask(command);
                return ui.showAddTask(tasks.list);
            } else if (Parser.isEvent(commandType)) {
                // ADD EVENT TASK
                addEventTask(command);
                return ui.showAddTask(tasks.list);
            } else if (Parser.isDelete(commandType)) {
                // DELETE A TASK
                Task deletedTask = getTask(command);
                deleteTask(command);
                return ui.showDeleteTask(tasks.list, deletedTask);
            } else if (Parser.isFind(commandType)) {
                // SEARCH FOR TASK USING KEYWORD
                String keyword = Parser.parseKeyword(command);
                ArrayList<Task> matchingTasks = getMatchingTasks(keyword);
                return ui.showMatchingTasks(matchingTasks);
            } else {
                throw new DukeException(ui.showInvalidCommand());
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
