import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static boolean isRunning;
    public static List<Task> taskList;
    public static final String STORAGE_PATH = "./data/duke.txt";

    public static void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    public static void printGreeting() {
        String greeting = "Hello! I'm Marvin and I will assist you with your task management.\n"
                + "What can I do for you today?\n";
        System.out.println(greeting);
    }

    public static List<Task> loadTasksFromFile(String pathname) throws DukeException {
        List<Task> taskList = new ArrayList<>();
        File file = new File(pathname);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
                return taskList;
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskData = line.split("\\|");
                String taskType = taskData[0];
                String taskStatus = taskData[1];
                String taskDescription = taskData[2];
                Task task = null;

                switch(taskType) {
                case("T"):
                    task = new Todo(taskDescription);
                    break;
                case("D"):
                    task = new Deadline(taskDescription, taskData[3]);
                    break;
                case("E"):
                    task = new Event(taskDescription, taskData[3]);
                    break;
                }

                if (task != null && taskStatus.equals("1")) {
                    task.setDone(true);
                }

                taskList.add(task);
            }

        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return taskList;
    }

    public static void saveTaskToFile(List<Task> taskList, String pathname) throws DukeException {
        StringBuilder taskString = new StringBuilder();
        for (Task task : taskList) {
            taskString.append(task.toSaveFormat());
        }
        File file = new File(pathname);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
            writer.write(taskString.toString());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }


    public static void startDuke() {
        printDivider();
        printGreeting();
        isRunning = true;
        try {
            taskList = loadTasksFromFile(STORAGE_PATH);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        printDivider();
    }


    public static void waitForInput(Scanner in) {
        while (isRunning) {
            try {
                String input = in.nextLine();
                handleInput(input);
            } catch (DukeException e) {
                printDivider();
                System.out.println(e.getMessage());
                printDivider();
            }
        }
    }

    public static void handleInput(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);

        if (inputArr.length == 0) {
            throw new DukeException("Please enter a command!");
        } else if (inputArr.length == 1) {
            Command command = Command.valueOfLabel(inputArr[0]);
            handleCommand(command, null);
        } else {
            Command command = Command.valueOfLabel(inputArr[0]);
            String rest = inputArr[1];
            handleCommand(command, rest);
        }
    }

    public static void handleCommand(Command command, String rest) throws DukeException {
        switch (command) {
        case TODO:
            handleTodo(rest);
            break;
        case DEADLINE:
            handleDeadline(rest);
            break;
        case EVENT:
            handleEvent(rest);
            break;
        case BYE:
            handleBye();
            break;
        case LIST:
            handleList();
            break;
        case DONE:
            handleDone(rest);
            break;
        case DELETE:
            handleDelete(rest);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void handleTodo(String input) throws DukeException {
        if (input != null) {
            Todo todo = new Todo(input);
            taskList.add(new Todo(input));
            printAddedTask(todo);
            saveTaskToFile(taskList, STORAGE_PATH);
        } else {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void handleDeadline(String input) throws DukeException {
        if (input != null) {
            String[] inputArr = input.split(" /by ", 2);
            if (inputArr.length == 2) {
                Deadline deadline = new Deadline(inputArr[0], inputArr[1]);
                taskList.add(deadline);
                printAddedTask(deadline);
                saveTaskToFile(taskList, STORAGE_PATH);
            } else {
                throw new DukeException("☹ OOPS!!! The due date of a deadline cannot be empty.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public static void handleEvent(String input) throws DukeException {
        if (input != null) {
            String[] inputArr = input.split(" /at ", 2);
            if (inputArr.length == 2) {
                Event event = new Event(inputArr[0], inputArr[1]);
                taskList.add(event);
                printAddedTask(event);
                saveTaskToFile(taskList, STORAGE_PATH);
            } else {
                throw new DukeException("☹ OOPS!!! The occurrence time of an event cannot be empty.");
            }

        } else {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }

    public static void printAddedTask(Task task) {
        printDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list%n", taskList.size());
        printDivider();
    }

    public static void handleBye() {
        isRunning = false;
    }

    public static void handleList() {
        printTaskList();
    }

    public static void printTaskList() {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        if (taskList.size() > 0) {
            for (Task task : taskList) {
                System.out.printf("%d.%s%n", count++, task);
            }
        }
        printDivider();
    }

    public static void handleDone(String strIdx) throws DukeException {
        if (strIdx != null) {
            int idx = Integer.parseInt(strIdx) - 1;
            Task task = taskList.get(idx);
            task.setDone(true);
            printDoneTask(task);
            saveTaskToFile(taskList, STORAGE_PATH);
        } else {
            throw new DukeException("☹ OOPS!!! The id of a done command cannot be empty.");
        }
    }

    public static void printDoneTask(Task task) {
        printDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printDivider();
    }

    public static void handleDelete(String strIdx) throws DukeException {
        if (strIdx != null) {
            int idx = Integer.parseInt(strIdx) - 1;
            Task task = taskList.get(idx);
            taskList.remove(idx);
            printDeletedTask(task);
            saveTaskToFile(taskList, STORAGE_PATH);
        } else {
            throw new DukeException("☹t OOPS!!! The id of a delete command cannot be empty.");
        }
    }

    public static void printDeletedTask(Task task) {
        printDivider();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list%n", taskList.size());
        printDivider();
    }

    public static void endDuke() {
        printByeMessage();
    }

    public static void printByeMessage() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon!\n");
        printDivider();
    }

    public static void runDuke(Scanner in) {
        startDuke();
        waitForInput(in);
        endDuke();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        runDuke(in);
        in.close();
    }
}
