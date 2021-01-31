import java.util.ArrayList;

public class Parser {
    protected ArrayList<Task> taskList;
    protected TaskList tasks;
    //user commands
    private final static String listCommand = "list";
    private final static String doneCommand = "done";
    private final static String deleteCommand = "delete";
    private final static String FindCommand = "find"; //find tasks based on a keyword
    private final static String exitCommand = "bye"; //only command that will end chat
    private final static String ToDos = "todo"; //tasks without any date/time attached to it
    private final static String Deadlines = "deadline"; //tasks that need to be done before a specific date/time
    private final static String Events = "event"; //tasks that start at a specific time and ends at a specific time


    public Parser(ArrayList<Task> taskList, TaskList tasks) {
        this.taskList = taskList;
        this.tasks = tasks;
    }

    public void readCommand(String description) throws InvalidCommandException{

        if (description.equalsIgnoreCase(listCommand)) {
            tasks.getTasks();

        } else if (description.equalsIgnoreCase(exitCommand)) {
            Duke.canExit = true;

        } else if (description.toLowerCase().contains(doneCommand)) {
            try {
                int taskIndex = Integer.parseInt(
                        description.replaceAll("[^0-9]", ""));
                tasks.updateTaskStatus(taskIndex);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(
                        "\n___________________________________________________________________\n"
                                + "\u2639 OOPS!!! the task you are referring to does not seem to exist :-( \n"
                                + "___________________________________________________________________\n");
            } catch (NumberFormatException e) {
                System.out.println(
                        "\n___________________________________________________________________\n"
                                + "\u2639 OOPS!!! you need to enter the index of the task :-( \n"
                                + "___________________________________________________________________\n");
            }

        } else if (description.toLowerCase().contains(deleteCommand)) {
            try {
                int taskIndex = Integer.parseInt(
                        description.replaceAll("[^0-9]", ""));
                tasks.Delete(taskIndex);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(
                        "\n___________________________________________________________________\n"
                                + "\u2639 OOPS!!! the task you are referring to does not seem to exist :-( \n"
                                + "___________________________________________________________________\n");
            } catch (NumberFormatException e) {
                System.out.println(
                        "\n___________________________________________________________________\n"
                                + "\u2639 OOPS!!! you need to enter the index of the task :-( \n"
                                + "___________________________________________________________________\n");
            }

        } else if (description.toLowerCase().contains(FindCommand)) {
            String[] seg = description.split(" ");
            String keyword = seg[seg.length - 1];
            tasks.findTask(keyword);

        } else if (description.toLowerCase().contains(Deadlines)) {
            try {
                tasks.addDeadlines(description);
            } catch (InvalidDeadlineException e) {
                System.out.println(e.toString());
            }

        } else if (description.toLowerCase().contains(Events)) {
            try {
                tasks.addEvents(description);
            } catch (InvalidEventException e) {
                System.out.println(e.toString());
            }

        } else if (description.toLowerCase().contains(ToDos)) {
            try {
                tasks.addTodos(description);
            } catch (InvalidTodoException e) {
                System.out.println(e.toString());
            }

        } else {
            throw new InvalidCommandException();
        }
    }

}
