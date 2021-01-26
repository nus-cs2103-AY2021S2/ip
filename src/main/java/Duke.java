import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {

    private static final String FILE_DIR = "duke.txt";

    protected enum Command {
        LIST, DONE, TODO, DEADLINE, EVENT, DELETE, BYE
    }

    private static ArrayList<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueDuke = true;

        greet();

        try {
            load();
        } catch (DukeException e) {
            echo(e.getMessage());
        }

        while (continueDuke) {
            String input = scanner.nextLine().trim();
            continueDuke = processInput(input);
        }
    }

    public static boolean processInput(String input) {
        String[] tokenizedInput = input.split(" ", 2);

        try {
            switch (Command.valueOf(tokenizedInput[0].toUpperCase())) {
            case LIST:
                listTask();
                break;
            case DONE:
                doneTask(tokenizedInput);
                break;
            case BYE:
                exit();
                return false;
            case TODO:
                addTodo(tokenizedInput);
                break;
            case DEADLINE:
                addDeadline(tokenizedInput);
                break;
            case EVENT:
                addEvent(tokenizedInput);
                break;
            case DELETE:
                deleteTask(tokenizedInput);
                break;
            }

            return true;
        } catch (IllegalArgumentException e) {
            echo("☹ Sorry, please enter a valid command.\n\tCommands available:\n\t\t- list\n\t\t" +
                    "- done [task number]\n\t\t- todo [description]\n\t\t- deadline [description] /by [deadline]\n\t\t" +
                    "- event [description] /at [datetime]\n\t\t- delete [task number]\n\t\t- bye");
        } catch (DukeException e) {
            echo(e.getMessage());
        }

        return true;
    }

    public static void addTodo(String[] tokenizedInput) throws DukeException {
        if (tokenizedInput.length != 2) {
            throw new DukeException("☹ Sorry, please enter a description for the todo\n\tCommand: todo [description]");
        }

        Task newTask = new ToDo(tokenizedInput[1].trim());
        addTask(newTask);
    }

    public static void addDeadline(String[] tokenizedInput) throws DukeException {
        if (tokenizedInput.length != 2) {
            throw new DukeException("☹ Sorry, please enter a description for the deadline\n\tCommand: deadline [description] /by [deadline]");
        }

        String[] deadlineDetails = tokenizedInput[1].split("/by");
        if (deadlineDetails.length != 2) {
            throw new DukeException("☹ Sorry, please enter a valid deadline format.\n\tCommand: deadline [description] /by [deadline]");
        }

        Task newTask = new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
        addTask(newTask);
    }

    public static void addEvent(String[] tokenizedInput) throws DukeException {
        if (tokenizedInput.length != 2) {
            throw new DukeException("☹ Sorry, please enter a description for the event.\n\tCommand: event [description] /at [datetime]");
        }

        String[] eventDetails = tokenizedInput[1].split("/at");
        if (eventDetails.length != 2) {
            throw new DukeException("☹ Sorry, please enter a valid event format.\n\t Command: event [description] /at [datetime]");
        }

        Task newTask = new Event(eventDetails[0].trim(), eventDetails[1].trim());
        addTask(newTask);
    }

    public static void addTask(Task newTask) {
        tasks.add(newTask);

        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task:\n\t");
        builder.append(newTask);
        builder.append("\nNow you have ");
        builder.append(tasks.size());

        if (tasks.size() == 1) {
            builder.append(" task");
        } else {
            builder.append(" tasks");
        }
        builder.append(" in the list.");

        try {
            save();
        } catch (DukeException e) {
            echo(e.getMessage());
        }

        echo(builder.toString());
    }

    public static void doneTask(String[] input) throws DukeException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(input[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ Sorry, please enter a task number.\n\tCommand: done [task number]");
        }

        try {
            tasks.get(taskIndex).markAsDone();
            echo("Nice! I've marked this task as done:\n\t" + tasks.get(taskIndex));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("☹ Sorry, please enter a valid task number.\n\tCommand: done [task number]");
        }

        try {
            save();
        } catch (DukeException e) {
            echo(e.getMessage());
        }
    }

    public static void listTask() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int numbering = i + 1;
            builder.append(String.format("\t%d. %s", numbering, tasks.get(i)));

            if (numbering != tasks.size()) {
                builder.append("\n");
            }
        }
        echo(builder.toString());
    }

    public static void deleteTask(String[] input) throws DukeException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(input[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ Sorry, please enter a task number.\n\tCommand: delete [task number]");
        }

        try {
            Task deletedTask = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            echo("Noted! I've removed this task:\n\t" + deletedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("☹ Sorry, please enter a valid task number.\n\tCommand: delete [task number]");
        }

        try {
            save();
        } catch (DukeException e) {
            echo(e.getMessage());
        }
    }

    public static void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _ ___\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        echo("Hello! I'm Duke.\nWhat can I do for you today?");
    }
    
    public static void load() throws DukeException {
        try {
            FileInputStream fileIn = new FileInputStream(FILE_DIR);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tasks = (ArrayList<Task>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            // file not found, no tasks loaded
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException("☹ Sorry, something went wrong while I was loading saved data from file.");
        }
    }

    public static void save() throws DukeException {
        try {
            FileOutputStream fileOut = new FileOutputStream(FILE_DIR);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tasks);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            throw new DukeException("☹ Sorry, something went wrong while I was saving data to file.");
        }
    }

    public static void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(String input) {
        System.out.println("========================================");
        System.out.println(input);
        System.out.println("========================================\n");
    }
}
