import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String CHATBOT_NAME = "Mantaro";
    private static final String TASKSLIST_PATH = "data/duke.txt";

    private static boolean isActive = true;
    private static final List<Task> tasks = new ArrayList<>();

    private static final Ui ui = new Ui();

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
     * @throws DukeCommandException if no description is given
     */
    private static void addToDo(String desc) throws DukeCommandException {
        if(desc.length() == 0) {
            throw new DukeCommandException("todo", desc, "The description of a ToDo cannot be empty.");
        } else {
            ToDo newToDo = new ToDo(desc);
            tasks.add(newToDo);
            printTaskAdding(newToDo);
        }
    }

    /**
     * Add a Deadline into the internal list
     * @param params Parameters of Deadline in String form that will be processed
     * @throws DukeCommandException if one or more parameters are missing or date time form was not followed
     */
    private static void addDeadline(String params) throws DukeCommandException {
        if(params.length() == 0) {
            throw new DukeCommandException("deadline", params, "The details of a Deadline cannot be empty.");
        } else if(!params.contains("/by") || params.split(" /by ").length != 2) {
            throw new DukeCommandException("deadline", params, "Description and date/time must be given for a " +
                    "Deadline.");
        } else if(!params.split(" /by ")[1].matches("^(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-" +
                "([1-9][0-9][0-9][0-9]) ([1-9]|1[0-2])(AM|PM)")) {
            throw new DukeCommandException("deadline", params, "Date time format is incorrect, try to follow the " +
                    "format of dd-mm-yyyy hAM/PM.");
        } else {
            String[] splits = params.split(" /by ");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
            LocalDateTime dateTime = LocalDateTime.parse(splits[1], formatter);

            Deadline newDeadline = new Deadline(splits[0], dateTime);
            tasks.add(newDeadline);

            printTaskAdding(newDeadline);
        }
    }

    /**
     * Add an Event into the internal list
     * @param params Parameters of Event (Description, Date/Time range) in String form that will be processed
     * @throws DukeCommandException if one or more parameters are missing or date time form was not followed
     */
    private static void addEvent(String params) throws DukeCommandException {
        String startEndPattern = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-" +
                "([1-9][0-9][0-9][0-9]) ([1-9]|1[0-2])(AM|PM)";

        if(params.length() == 0) {
            throw new DukeCommandException("event", params, "The details of a Event cannot be empty.");
        } else if(!params.contains("/start") || !params.contains("/end") || params.split(" /start | /end ").length != 3) {
            throw new DukeCommandException("event", params, "Description, start datetime, and end datetime " +
                    "must be given for an Event.");
        } else if(!params.split(" /start | /end ")[1].matches(startEndPattern)
                || !params.split(" /start | /end ")[2].matches(startEndPattern)) {
            throw new DukeCommandException("deadline", params, "Start or end date has incorrect format, try to " +
                    "follow the format of dd-mm-yyyy hAM/PM.");
        } else {
            String[] splits = params.split(" /start | /end ");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
            LocalDateTime start = LocalDateTime.parse(splits[1], formatter);
            LocalDateTime end = LocalDateTime.parse(splits[2], formatter);

            Event newEvent = new Event(splits[0], start, end);
            tasks.add(newEvent);

            printTaskAdding(newEvent);
        }
    }

    /**
     * Mark a task as completed
     * @param param Parameter in String form to complete a task
     * @throws DukeCommandException if there is no tasks, index <= 0, index >= number of tasks
     */
    private static void doneTask(String param) throws DukeCommandException {
        if(!param.matches("-?(0|[1-9]\\d*)")) {
            throw new DukeCommandException("done", param, "Please provide an actual number for the task you are done " +
                    "with.");
        }

        int index = Integer.parseInt(param) - 1;

        if(tasks.size() == 0){
            throw new DukeCommandException("done", param, "There are no task to be completed.");
        } else if(index < 0 || index >= tasks.size()) {
            throw new DukeCommandException("done", param, "Please enter a valid task index ranging " +
                    "from 1 to " + tasks.size() + " (inclusive).");
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

    /**
     * Delete a task from the internal list
     * @param param Parameter in String form to identify a task for deletion (Index)
     * @throws DukeCommandException if there is no tasks, index <= 0, index >= number of tasks
     */
    private static void deleteTask(String param) throws DukeCommandException {
        if(!param.matches("-?(0|[1-9]\\d*)")) {
            throw new DukeCommandException("delete", param, "Please provide an actual number for the task you are " +
                    "deleting.");
        }

        int index = Integer.parseInt(param) - 1;

        if(tasks.size() == 0){
            throw new DukeCommandException("delete", param, "There are no task to be deleted.");
        } else if(index < 0 || index >= tasks.size()) {
            throw new DukeCommandException("delete", param, "Please enter a valid task index ranging " +
                    "from 1 to " + tasks.size() + " (inclusive).");
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
     * Load tasks from previous session that was saved into hard disk
     * @throws DukeLoadException if there is an IO issue reading the save file or invalid task type was found
     */
    private static void loadTasks() throws DukeLoadException {
        // Create the 'data' folder if missing
        File dir = new File("data");
        if(!dir.exists()) {
            dir.mkdir();
        }

        // Load the save file or create one if missing
        File file = new File(TASKSLIST_PATH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");

        try {
            // If the save file already exists, load its tasks
            if(!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) {
                    String[] splits = scanner.nextLine().split(" \\| ");
                    boolean isDone = splits[1].equals("1");

                    switch (splits[0]) {
                    case "T":
                        ToDo toDo = new ToDo(splits[2], isDone);
                        tasks.add(toDo);
                        break;
                    case "D":
                        LocalDateTime dateTime = LocalDateTime.parse(splits[3], formatter);
                        Deadline deadline = new Deadline(splits[2], dateTime, isDone);
                        tasks.add(deadline);
                        break;
                    case "E":
                        LocalDateTime start = LocalDateTime.parse(splits[3], formatter);
                        LocalDateTime end = LocalDateTime.parse(splits[4], formatter);
                        Event event = new Event(splits[2], start, end, isDone);
                        tasks.add(event);
                        break;
                    default:
                        throw new DukeLoadException("Invalid task type found: " + splits[0]);
                    }
                }
            }
        } catch (IOException e) {
            throw new DukeLoadException("Issue with IO while loading tasks");
        }
    }

    /**
     * Save tasks from current session into the hard disk
     * @throws DukeSaveException if there is an IO issue writing the save file
     */
    private static void saveTasks() throws DukeSaveException {
        // Create the 'data' folder if missing
        File dir = new File("data");
        if(!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(TASKSLIST_PATH);
        try {
            // Erase any existing list in the file
            new PrintWriter(TASKSLIST_PATH).close();

            // Save each task as a row in the file
            FileWriter writer = new FileWriter(TASKSLIST_PATH);
            for (Task task : tasks) {
                writer.write(task.toSaveInfoString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeSaveException("Issue with IO while saving tasks");
        }
    }

    /**
     * Lifecycle of the chatbot
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            loadTasks();
        } catch (DukeLoadException e) {
            System.out.println("___________________________________________________________");
            System.out.printf("There is a problem while loading your past tasks meow! %s\n", e.getMessage());
            System.out.println("___________________________________________________________\n");
        }

        // Opening message
        ui.printWelcomeMsg(CHATBOT_NAME);

        // Scan user input as a command
        Scanner scanner = new Scanner(System.in);
        String command = "";

        // Respond to user's commands and exit when user enters bye
        while(isActive) {
            command = scanner.nextLine();

            try {
                if(command.matches("^todo($|.+$)")) {
                    addToDo(command.substring(4).stripLeading());
                    saveTasks();
                } else if(command.matches("^deadline($|.+$)")) {
                    addDeadline(command.substring(8).stripLeading());
                    saveTasks();
                } else if(command.matches("^event($|.+$)")) {
                    addEvent(command.substring(5).stripLeading());
                    saveTasks();
                } else if(command.matches("^done($|.+$)")) {
                    doneTask(command.substring(4).stripLeading());
                    saveTasks();
                } else if(command.matches("^delete($|.+$)")) {
                    deleteTask(command.substring(6).stripLeading());
                    saveTasks();
                } else if(command.equals("list")) {
                    listTasks();
                } else if(command.equals("bye")) {
                    isActive = false;
                } else {
                    System.out.println("___________________________________________________________");
                    System.out.println("No such command, Please try again with another command meow.");
                    System.out.println("___________________________________________________________\n");
                }
            } catch(DukeException e) {
                ui.printError(e.getMessage());
            }
        }

        // Closing message
        ui.printGoodbyeMsg();
    }
}
