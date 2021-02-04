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
        return LocalDate.parse(command.substring(9).split(" /by ")[1]);
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

    static int parseRescheduleIndex(String command) {
        return Integer.parseInt(command.substring(11).split(" /to ")[0]);
    }

    static LocalDate parseRescheduleTime(String command) {
        return LocalDate.parse(command.substring(11).split(" /to ")[1]);
    }

    static boolean hasSaved(String command) {
        return command.equals("save");
    }

    static boolean hasExited(String command) {
        return command.equals("exit");
    }

    public String parseCommand(String command) {
        String commandType = Parser.parseCommandType(command);
        try {
            if (commandType.equals("list")) {
                // SHOW LIST
                return this.ui.showList(tasks.list);
            } else if (commandType.equals("clear")) {
                // CLEAR LIST
                tasks.list.clear();
                return ui.showClear();
            } else if (commandType.equals("done")) {
                // MARK TASK AS COMPLETE
                int taskIndex = Parser.parseTaskIndex(command) - 1;
                Task completedTask = tasks.list.get(taskIndex);
                completedTask.markAsDone();
                return ui.showDone(completedTask);
            } else if (commandType.equals("todo")) {
                // ADD TODO TASK
                try {
                    String description = Parser.parseTodoDescription(command);
                    Task task = new Todo(description);
                    tasks.addTask(task);
                    return ui.showAddTask(tasks.list);
                } catch (StringIndexOutOfBoundsException e) {
                    return ui.showInvalidTodo();
                }
            } else if (commandType.equals("deadline")) {
                // ADD DEADLINE TASK
                String deadlineDescription = Parser.parseDeadlineDescription(command);
                LocalDate deadlineDate = Parser.parseDeadlineDate(command); // INPUT DATE IS YYYY-MM-DD
                Task task = new Deadline(deadlineDescription, deadlineDate);
                tasks.addTask(task);
                return ui.showAddTask(tasks.list);
            } else if (commandType.equals("event")) {
                // ADD EVENT TASK
                String eventDescription = Parser.parseEventDescription(command);
                String eventDate = Parser.parseEventDate(command);
                Task task = new Event(eventDescription, eventDate);
                tasks.addTask(task);
                return ui.showAddTask(tasks.list);
            } else if (commandType.equals("delete")) {
                // DELETE A TASK
                int taskIndex = Parser.parseTaskIndex(command) - 1;
                Task deletedTask = tasks.list.get(taskIndex);
                tasks.removeTask(taskIndex);
                return ui.showDeleteTask(tasks.list, deletedTask);
            } else if (commandType.equals("find")) {
                // SEARCH FOR TASK USING KEYWORD
                String keyword = Parser.parseKeyword(command);
                ArrayList<Task> matchingTasks = new ArrayList<>();
                for (Task task : tasks.list) {
                    if (task.description.contains(keyword)) {
                        matchingTasks.add(task);
                    }
                }
                return ui.showMatchingTasks(matchingTasks);
            } else if (commandType.equals("reschedule")) {
                // RESCHEDULE DEADLINE TASK
                int taskIndex = Parser.parseRescheduleIndex(command) - 1;
                if (tasks.list.get(taskIndex) instanceof Deadline) {
                    LocalDate updated = Parser.parseRescheduleTime(command);
                    ((Deadline) tasks.list.get(taskIndex)).updateDate(updated);
                    return ui.showRescheduledTask(tasks.list, taskIndex);
                } else {
                    throw new DukeException(ui.showInvalidType());
                }
            } else {
                throw new DukeException(ui.showInvalidCommand());
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
