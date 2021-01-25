import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Duke {
    private static final List<Task> taskList = new ArrayList<>();
    private static final String FILE_PATH = "./src/main/java/tasks.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[d/M/yyyy HHmm][d MMM yy HHmm][dd-MM-yy HHmm]");

    private static void introduction() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        String welcomeMessage = "Hello! I'm Duke.";
        System.out.println(welcomeMessage);
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task t : taskList) {
            System.out.println(counter + ". " + t);
            counter++;
        }
    }

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
            EmptyDescriptionException {
        if (checkValidCommand(command)) {
            if (taskInputAndDate[0].length() == 0) {
                throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
            } else if (command.equals(TaskTypes.TODO.getType())) {
                return new ToDo(taskInputAndDate[0]);
            } else if (command.equals(TaskTypes.DEADLINE.getType())) {
                return new Deadline(taskInputAndDate[0], LocalDateTime.parse(taskInputAndDate[1], formatter));
            } else {
                return new Event(taskInputAndDate[0], LocalDateTime.parse(taskInputAndDate[1], formatter));
            }
        } else {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(.\n" +
                    "Please enter a valid command that starts with 'todo', 'deadline', 'event', 'list' or 'done'!");
        }
    }

    private static void saveToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        String text = "";
        for (Task task : taskList) {
            text += taskToTxt(task) + "\n";
        }
        fw.write(text);
        fw.close();
    }

    private static String taskToTxt(Task task) {
        String done = task.isDone() ? "1" : "0";
        if (task instanceof ToDo) {
            return "T | " + done + " | " + task.getDescription();
        } else if (task instanceof Event) {
            return "E | " + done + " | " + task.getDescription() + " | " + ((Event) task).getDateToStore();
        } else {
            return "D | " + done + " | " + task.getDescription() + " | " + ((Deadline) task).getDateToStore();
        }
    }

    private static void endProgram() throws IOException {
        saveToFile();
        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(endMessage);
    }

    private static void setTaskDone(int pos) {
        taskList.get(pos).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + taskList.get(pos));
    }

    private static int calcListPos(String taskIndex) throws NumberFormatException {
        return Integer.parseInt(taskIndex) - 1;
    }

    private static void doneTask(String taskIndex) throws IndexOutOfBoundsException {
        setTaskDone(calcListPos(taskIndex));
    }

    private static void deleteTask(String taskIndex) throws IndexOutOfBoundsException {
        int pos = calcListPos(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(pos));
        taskList.remove(pos);
        printNumTasksInList();
    }

    private static void addTask(String command, String[] taskInputAndDate) throws InvalidCommandException,
            EmptyDescriptionException {
        Task task = defineTask(command, taskInputAndDate);
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        printNumTasksInList();
    }

    private static void printNumTasksInList() {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void storeToProgram(String task) {
        String[] separated = task.split(" \\| ");
        char taskType = separated[0].charAt(0);
        if (taskType == 'T') {
            ToDo t = new ToDo(separated[2]);
            if (separated[1].equals("1")) {
                t.markAsDone();
            }
            taskList.add(t);
        } else if (taskType == 'D') {
            Deadline d = new Deadline(separated[2], LocalDateTime.parse(separated[3], formatter));
            if (separated[1].equals("1")) {
                d.markAsDone();
            }
            taskList.add(d);
        } else if (taskType == 'E') {
            Event e = new Event(separated[2], LocalDateTime.parse(separated[3], formatter));
            if (separated[1].equals("1")) {
                e.markAsDone();
            }
            taskList.add(e);
        } else {
            System.err.println("Invalid task type!");
        }
    }

    private static void run() {
        introduction();
        File file = new File(FILE_PATH);
        try {
            Scanner scannerFile = new Scanner(file);
            if (scannerFile.hasNextLine()) {
                System.out.println("You have existing tasks!");
                while (scannerFile.hasNextLine()) {
                    String task = scannerFile.nextLine();
                    storeToProgram(task);
                }
                printList();
            } else {
                System.out.println("You have no existing tasks!");
            }
            scannerFile.close();

            Scanner scannerInput = new Scanner(System.in);
            System.out.println("What can I do for you?");
            while (scannerInput.hasNextLine()) {
                String command = scannerInput.next();
                if (isByeCommand(command)) {
                    try {
                        endProgram();
                    } catch (IOException e) {
                        System.err.println("There was an error writing to the file.");
                        System.err.println(e.getMessage());
                    }
                    break;
                } else if (isListCommand(command)) {
                    printList();
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
                            System.err.println("'" + command + "' is command word; please pass a numerical index or "
                                    + "start your task with another word!");
                        } catch (IndexOutOfBoundsException arrEx) {
                            System.err.println("Please pass a valid index!");
                        }
                    } else {
                        taskInputAndDate[1] = taskInputAndDate[1].trim().substring(3);
                        try {
                            addTask(command, taskInputAndDate);
                        } catch (InvalidCommandException | EmptyDescriptionException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
            }
            scannerInput.close();
        } catch (FileNotFoundException e) {
            System.err.println("Please create a 'tasks.txt' file in the current directory to store your tasks before "
                    + "running the program again!");
        }
    }

    public static void main(String[] args) {
        run();
    }
}