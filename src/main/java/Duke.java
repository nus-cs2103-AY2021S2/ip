import java.io.File;
import java.io.IOException;
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
     * Lifecycle of the chatbot
     * @param args Command line arguments
     */
    public static void main(String[] args) {
//        try {
//            loadTasks();
//        } catch (DukeLoadException e) {
//            System.out.println("___________________________________________________________");
//            System.out.printf("There is a problem while loading your past tasks meow! %s\n", e.getMessage());
//            System.out.println("___________________________________________________________\n");
//        }

        Ui ui = new Ui();
        TaskManager taskManager = new TaskManager();
        Command.setup(ui, taskManager);

        // Opening message
        ui.printWelcomeMsg(CHATBOT_NAME);

        // Scan user input as a command
        Scanner scanner = new Scanner(System.in);
        String line = "";

        // Respond to user's commands and exit when user enters bye
        while(isActive) {
            line = scanner.nextLine();

            try {
                Command command = Parser.parse(line);
                command.execute();
                isActive = !command.isToExit();
            } catch(DukeException e) {
                ui.printError(e.getMessage());
            }
        }

        // Closing message
        ui.printGoodbyeMsg();
    }
}
