import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./src/main/java/tasks.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[d/M/yyyy HHmm][d MMM yy HHmm][dd-MM-yy HHmm]");
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;

    private static boolean isByeCommand(String command) {
        return command.equals(Commands.BYE.getCommand());
    }

    private static boolean isListCommand(String command) {
        return command.equals(Commands.LIST.getCommand());
    }

    private static boolean isDoneCommand(String command) {
        return command.equals(Commands.DONE.getCommand());
    }

    private static boolean isDeleteCommand(String command) {
        return command.equals(Commands.DELETE.getCommand());
    }

    private static boolean checkValidCommand(String command) {
        return command.equals(TaskTypes.TODO.getType()) || command.equals(TaskTypes.DEADLINE.getType())
                || command.equals(TaskTypes.EVENT.getType());
    }

    private static Task defineTask(String command, String[] taskInputAndDate) throws InvalidCommandException,
            EmptyDescriptionException, DateTimeParseException {
        if (checkValidCommand(command)) {
            if (taskInputAndDate[0].length() == 0) {
                throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
            } else if (command.equals(TaskTypes.TODO.getType())) {
                return new ToDo(taskInputAndDate[0]);
            } else if (command.equals(TaskTypes.DEADLINE.getType())) {
                taskInputAndDate[1] = taskInputAndDate[1].trim().substring(3);
                return new Deadline(taskInputAndDate[0], LocalDateTime.parse(taskInputAndDate[1], formatter));
            } else {
                taskInputAndDate[1] = taskInputAndDate[1].trim().substring(3);
                return new Event(taskInputAndDate[0], LocalDateTime.parse(taskInputAndDate[1], formatter));
            }
        } else {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(.\n" +
                    "Please enter a valid command that starts with 'todo', 'deadline', 'event', 'list' or 'done'!");
        }
    }

    private static void endProgram() throws IOException {
        List<String> converted = TaskStringConverter.allTaskToAllString(taskList.getList());
        storage.writeToFile(converted);
        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(endMessage);
    }

    private static int calcListPos(String taskIndex) throws NumberFormatException {
        return Integer.parseInt(taskIndex) - 1;
    }

    private static void doneTask(String taskIndex) throws IndexOutOfBoundsException {
        taskList.setTaskDone(calcListPos(taskIndex));
        System.out.println("Nice! I've marked this task as done:");
        taskList.printList();
    }

    private static void deleteTask(String taskIndex) throws IndexOutOfBoundsException {
        int pos = calcListPos(taskIndex);
        System.out.println("Noted. I've removed this task:");
        taskList.printTask(pos);
        taskList.deleteTask(pos);
        printNumTasksInList();
    }

    private static void addTask(String command, String[] taskInputAndDate) throws InvalidCommandException,
            EmptyDescriptionException, DateTimeParseException {
        Task task = defineTask(command, taskInputAndDate);
        taskList.addTask(task);
        System.out.println("Got it. I've added this task:\n" + task);
        printNumTasksInList();
    }

    private static void printNumTasksInList() {
        System.out.println("Now you have " + taskList.getList().size() + " tasks in the list.");
    }

    private static void run() {
        ui = new Ui();
        ui.introduction();
        storage = new Storage(FILE_PATH);

        try {
            List<String> input = storage.loadFromFile();
            if (input.size() == 0) {
                ui.showMsg("You have no existing tasks!");
            } else {
                try {
                    ui.showMsg("You have existing tasks!");
                    List<Task> converted = TaskStringConverter.allStringToAllTask(input);
                    taskList = new TaskList(converted);
                    taskList.printList();
                } catch (InvalidTaskTypeException e) {
                    ui.showError("Erroneous task type in file. Please check your file again!");
                }
            }

            Scanner scannerInput = new Scanner(System.in);
            ui.showMsg("What can I do for you?");
            while (scannerInput.hasNextLine()) {
                String command = scannerInput.next();
                if (isByeCommand(command)) {
                    try {
                        endProgram();
                    } catch (IOException e) {
                        ui.showError("There was an error writing to the file.\n" + e.getMessage());
                    }
                    break;
                } else if (isListCommand(command)) {
                    taskList.printList();
                } else {
                    String taskInput = scannerInput.nextLine();
                    String[] taskInputAndDate = taskInput.split("/", 2);
                    taskInputAndDate[0] = taskInputAndDate[0].trim();
                    String taskDescription = taskInputAndDate[0];
                    if (isDoneCommand(command) || isDeleteCommand(command)) {
                        try {
                            if (isDoneCommand(command)) {
                                doneTask(taskDescription);
                            } else {
                                deleteTask(taskDescription);
                            }
                        } catch (NumberFormatException numEx) {
                            ui.showError("'" + command + "' is command word; please pass a numerical index or "
                                    + "start your task with another word!");
                        } catch (IndexOutOfBoundsException arrEx) {
                            ui.showError("Please pass a valid index!");
                        }
                    } else {
                        try {
                            addTask(command, taskInputAndDate);
                        } catch (InvalidCommandException | EmptyDescriptionException e) {
                            System.err.println(e.getMessage());
                        } catch (DateTimeParseException e) {
                            ui.showError("Please enter your date in one of the following formats:\n" +
                                    "d/M/yyyy HHmm OR d MMM yy HHmm OR dd-MM-yy HHmm");
                        }
                    }
                }
            }
            scannerInput.close();
        } catch (FileNotFoundException e) {
            ui.showError("Please create a 'tasks.txt' file in the current directory to store your tasks before "
                    + "running the program again!");
        }
    }

    public static void main(String[] args) {
        run();
    }
}