import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String CHATBOT_NAME = "Mantaro";
    private static boolean isActive = true;

    private static List<Task> tasks = new ArrayList<>();

    /**
     * Print an adding message corresponding to the added Task's type
     * @param task Task that was added
     */
    private static void printTaskAdding(Task task) {
        System.out.println("___________________________________________________________");
        System.out.println("Got it meow. I've added this task:");
        System.out.printf("  [%s][%s] %s\n", task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
        System.out.println("___________________________________________________________\n");
    }

    /**
     * Add a ToDo into the internal list
     * @param desc Description of the ToDo
     */
    private static void addToDo(String desc) throws DukeCommandException {
        if(desc.length() == 0) {
            throw new DukeCommandException("todo", desc, "The details of a ToDo cannot be empty.");
        } else {
            ToDo newToDo = new ToDo(desc);
            tasks.add(newToDo);
            printTaskAdding(newToDo);
        }
    }

    /**
     * Add a Deadline into the internal list
     * @param params Parameters of Deadline (Description, Date/Time) in String form that will be processed
     */
    private static void addDeadline(String params) throws DukeCommandException {
        if(params.length() == 0) {
            throw new DukeCommandException("deadline", params, "The details of a Deadline cannot be empty.");
        } else if(!params.contains("/by") || params.split(" /by ").length != 2) {
            throw new DukeCommandException("deadline", params, "Proper description and date/time must be given for a " +
                    "Deadline.");
        } else {
            String[] splits = params.split(" /by ");

            Deadline newDeadline = new Deadline(splits[0], splits[1]);
            tasks.add(newDeadline);

            printTaskAdding(newDeadline);
        }
    }

    /**
     * Add an Event into the internal list
     * @param params Parameters of Event (Description, Date/Time range) in String form that will be processed
     */
    private static void addEvent(String params) throws DukeCommandException {
        if(params.length() == 0) {
            throw new DukeCommandException("event", params, "The details of a Event cannot be empty.");
        } else if(!params.contains("/at") || params.split(" /at ").length != 2) {
            throw new DukeCommandException("event", params, "Proper description and date/time range must be given for" +
                    " a Event.");
        } else {
            String[] splits = params.split(" /at ");

            Event newEvent = new Event(splits[0], splits[1]);
            tasks.add(newEvent);

            printTaskAdding(newEvent);
        }
    }

    /**
     * Mark a task as completed
     * @param param Parameter in String form to complete a task (Index)
     */
    private static void doneTask(String param) throws DukeCommandException {
        if(!param.matches("-?(0|[1-9]\\d*)")) {
            throw new DukeCommandException("done", param, "Please provide an actual number for the task you are done " +
                    "with.");
        }

        int index = Integer.valueOf(param) - 1;

        if(tasks.size() == 0){
            throw new DukeCommandException("done", param, "There are no task to be completed.");
        } else if(index < 0 || index >= tasks.size()) {
            throw new DukeCommandException("done", param, "Please enter a valid task index ranging " +
                    "from 1 to " + String.valueOf(tasks.size()) + " (inclusive).");
        } else {
            Task task = tasks.get(index);
            task.markAsDone();

            System.out.println("___________________________________________________________");
            System.out.println("Good job meow, I've marked this task as done:");
            System.out.printf("%d.[%s][%s] %s\n", index + 1, task.getTypeSymbol(), task.getStatusSymbol(),
                    task.getDesc());
            System.out.println("___________________________________________________________\n");
        }
    }

    private static void deleteTask(String param) throws DukeCommandException {
        if(!param.matches("-?(0|[1-9]\\d*)")) {
            throw new DukeCommandException("delete", param, "Please provide an actual number for the task you are " +
                    "deleting.");
        }

        int index = Integer.valueOf(param) - 1;

        if(tasks.size() == 0){
            throw new DukeCommandException("delete", param, "There are no task to be deleted.");
        } else if(index < 0 || index >= tasks.size()) {
            throw new DukeCommandException("delete", param, "Please enter a valid task index ranging " +
                    "from 1 to " + String.valueOf(tasks.size()) + " (inclusive).");
        } else {
            Task deletedTask = tasks.remove(index);

            System.out.println("___________________________________________________________");
            System.out.println("Noted meow. I've removed this task:");
            System.out.printf("  [%s][%s] %s\n", deletedTask.getTypeSymbol(), deletedTask.getStatusSymbol(),
                    deletedTask.getDesc());
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            System.out.println("___________________________________________________________\n");
        }
    }

    /**
     * List down all tasks in the internal list
     */
    private static void listTasks() {
        System.out.println("___________________________________________________________");
        System.out.println("Meow, here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.[%s][%s] %s\n", i + 1, task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
        }
        System.out.println("___________________________________________________________\n");
    }

    /**
     * Lifecycle of the chatbot
     * @param args
     */
    public static void main(String[] args) {
        // Opening message
        System.out.println("___________________________________________________________");
        System.out.printf("Meow, I'm %s\nWhat can I do for you today?\n", CHATBOT_NAME);
        System.out.println("___________________________________________________________\n");

        // Scan user input as a command
        Scanner scanner = new Scanner(System.in);
        String command = "";

        // Respond to user's commands and exit when user enters bye
        while(isActive) {
            command = scanner.nextLine();

            try {
                if(command.matches("^todo($|.+$)")) {
                    addToDo(command.substring(4).stripLeading());
                } else if(command.matches("^deadline($|.+$)")) {
                    addDeadline(command.substring(8).stripLeading());
                } else if(command.matches("^event($|.+$)")) {
                    addEvent(command.substring(5).stripLeading());
                } else if(command.matches("^done($|.+$)")) {
                    doneTask(command.substring(4).stripLeading());
                } else if(command.matches("^delete($|.+$)")) {
                    deleteTask(command.substring(6).stripLeading());
                } else if(command.equals("list")) {
                    listTasks();
                } else if(command.equals("bye")) {
                    isActive = false;
                } else {
                    System.out.println("___________________________________________________________");
                    System.out.println("No such command, Please try again with another command meow.");
                    System.out.println("___________________________________________________________\n");
                }
            } catch(DukeCommandException e) {
                System.out.println("___________________________________________________________");
                System.out.printf("ERROR MEOW! %s\n", e.getMessage());
                System.out.println("___________________________________________________________\n");
            }
        }

        // Closing message
        System.out.println("___________________________________________________________");
        System.out.println("Meow. Hope to see you again soon!");
        System.out.println("___________________________________________________________");
    }
}
