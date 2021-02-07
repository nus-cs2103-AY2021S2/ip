import java.util.ArrayList;

public class Parser {
    protected ArrayList<Task> taskList;
    protected TaskList tasks;
    protected Ui ui;
    //user commands
    private final static String listCommand = "list";
    private final static String doneCommand = "done";
    private final static String deleteCommand = "delete";
    private final static String FindCommand = "find"; //find tasks based on a keyword
    private final static String exitCommand = "bye"; //only command that will end chat
    private final static String ToDos = "todo"; //tasks without any date/time attached to it
    private final static String Deadlines = "deadline"; //tasks that need to be done before a specific date/time
    private final static String Events = "event"; //tasks that start at a specific time and ends at a specific time


    public Parser(ArrayList<Task> taskList, TaskList tasks, Ui ui) {
        this.taskList = taskList;
        this.tasks = tasks;
        this.ui = ui;
    }

    public String readCommand(String description) throws InvalidCommandException {

        if (description.equalsIgnoreCase(listCommand)) {
            return tasks.getTasks();

        } else if (description.equalsIgnoreCase(exitCommand)) {
            Duke.canExit = true;
            return ui.sayBye();

        } else if (description.toLowerCase().contains(doneCommand)) {
            try {
                int taskIndex = Integer.parseInt(
                        description.replaceAll("[^0-9]", ""));
                return tasks.updateTaskStatus(taskIndex);
            } catch (IndexOutOfBoundsException e) {
                return ui.taskNotExist();
            } catch (NumberFormatException e) {
                return ui.missingIndex();
            }

        } else if (description.toLowerCase().contains(deleteCommand)) {
            try {
                int taskIndex = Integer.parseInt(
                        description.replaceAll("[^0-9]", ""));
                return tasks.delete(taskIndex);
            } catch (IndexOutOfBoundsException e) {
                return ui.taskNotExist();
            } catch (NumberFormatException e) {
                return ui.missingIndex();
            }

        } else if (description.toLowerCase().contains(FindCommand)) {
            String[] seg = description.split(" ");
            String keyword = seg[seg.length - 1];
            return tasks.findTask(keyword);

        } else if (description.toLowerCase().contains(Deadlines)) {
            try {
                return tasks.addDeadlines(description);
            } catch (InvalidDeadlineException e) {
                return e.toString();
            }

        } else if (description.toLowerCase().contains(Events)) {
            try {
                return tasks.addEvents(description);
            } catch (InvalidEventException e) {
                return e.toString();
            }

        } else if (description.toLowerCase().contains(ToDos)) {
            try {
                return tasks.addTodos(description);
            } catch (InvalidTodoException e) {
                return e.toString();
            }

        } else {
            throw new InvalidCommandException();
        }
    }

}
