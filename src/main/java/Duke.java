import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//to handle date and time
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Duke {
    // output strings
    public static String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    public static String line = "    ____________________________________________________________";
    public static String indentation = "    ";
    public static String terminate_input = "bye";
    public static String show_list = "list";
    public static String done = "done";
    public static String delete = "delete";

    // task list
    public static List<Task> task = new ArrayList<>();

    // scanner
    public static Scanner sc = new Scanner(System.in);

    // file saving object from FileSaver class
    public static FileSaver fs = new FileSaver();

    public static void greeting() {
        System.out.println(logo);
        System.out.println(line);
        System.out.println(indentation + "Hello! I'm Duke");
        System.out.println(indentation + "What I can do for you?");
        System.out.println(line);
    }

    public static void bye() {
        System.out.println(line);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(line);
        sc.close();
    }

    // public static String getUserInput() {
    // try {
    // String userInput = sc.nextLine();
    // String[] input = userInput.split(" ", 2);
    // return input[0];
    // } catch (ArrayIndexOutOfBoundsException e) {
    // // TODO: handle exception
    // System.out.println(line);
    // System.out.println(indentation + "Invalid input, please enter another one");
    // System.out.println(line);
    // }
    // }

    public static boolean isDateFormat(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException e) {
            //TODO: handle exception
            return false;
        }
        return true;
    }

    public static void add(String[] userInput) throws DukeException {
        switch (userInput[0]) {
            case "todo":
                Todo t = new Todo(userInput[1]);
                task.add(t);
                reportTask(t);
                break;

            case "deadline":
                String[] deadlineArr = userInput[1].split(" /by ", 2);
                if (deadlineArr.length != 2) {
                    throw new DukeException("Missing component: due date");
                }
                String time = deadlineArr[1];
                if (isDateFormat(time)) {
                    LocalDate date = LocalDate.parse(time);
                    time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                }
                Deadline d = new Deadline(deadlineArr[0], time);
                task.add(d);
                reportTask(d);
                break;

            case "event":
                String eventArr[] = userInput[1].split(" /at ", 2);
                if (eventArr.length != 2) {
                    throw new DukeException("Missing component: event date and time");
                }
                Event e = new Event(eventArr);
                task.add(e);
                reportTask(e);
                break;

            default:
                break;
        }
    }

    public static void reportTask(Task t) {
        int count = task.size();
        System.out.println(line);
        System.out.println(indentation + "Got it, I've added this task to the list:");
        System.out.println(indentation + t.toString());
        System.out.printf(indentation + "You now have %d task in the list.%n", count);
        System.out.println(line);
    }

    public static void doneTask(String inputIndex) {
        try {
            System.out.println(line);
            int index = Integer.parseInt(inputIndex.trim());
            Task t = task.get(index - 1);
            if (t.isDone()) {
                System.out.println(indentation + "This task was marked as done before");
            } else {
                t.markAsDone();
                System.out.println(indentation + "You have done the following task:");
                System.out.println(indentation + t.toString());
            }
            System.out.println(line);
        } catch (IndexOutOfBoundsException e) {
            // TODO: handle exception
            printErrorMessage(indentation + "Sorry, I cannot find this task, please check your list again");
        } catch (NumberFormatException e) {
            printErrorMessage(indentation + "Sorry, number not recognized");
        }
    }

    public static void deleteTask(String inputIndex) {
        try {
            System.out.println(line);
            int index = Integer.parseInt(inputIndex.trim());
            Task t = task.get(index - 1);
            task.remove(t);
            System.out.println(indentation + "The following task has been deleted:");
            System.out.println(indentation + t.toString());
            System.out.printf(indentation + "You now have %d task in your list %n", task.size());
        } catch (IndexOutOfBoundsException e) {
            // TODO: handle exception
            printErrorMessage(indentation + "Sorry, I cannot find this task, please check your list again");
        } catch (NumberFormatException e) {
            printErrorMessage(indentation + "Sorry, number not recognized");
        }
    }

    public static void printTask() {
        System.out.println(line);
        System.out.println(indentation + "Here is your current tasks");
        for (int i = 1; i <= task.size(); ++i) {
            System.out.println(indentation + i + "." + task.get(i - 1).toString());
        }
        System.out.println(line);
    }

    private static Command getUserInputType(String userInput) throws DukeException {
        try {
            return Command.valueOf(userInput.toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new DukeException("Sorry, I dont understand what that means :-(");
        }
    }

    public static void run() {
        boolean run = true;
        while (run) {
            try {
                String temp = sc.nextLine();
                String[] input = temp.split(" ", 2);
                Command command = getUserInputType(input[0]);
                switch (command) {
                    case DEADLINE:
                    case TODO:
                    case EVENT:
                        add(input);
                        break;
                    case DELETE:
                        deleteTask(input[1]);
                        break;
                    case LIST:
                        printTask();
                        break;
                    case DONE:
                        doneTask(input[1]);
                        break;
                    case BYE:
                        bye();
                        run = false;
                        break;
                    default:
                        throw new DukeException("Sorry, I dont understand that");
                }
                fs.save(task);
            } catch (DukeException e) {
                // TODO: handle exception
                printErrorMessage(e.getMessage());
            }
        }
    }

    public static void printErrorMessage(String message) {
        System.out.println(line);
        System.out.println(indentation + "☹ OOPS!!! " + message);
        System.out.println(line);
    }

    public static void main(String[] args) {
        greeting();
        try {
            fs.load(task);
        } catch (DukeException e) {
            //TODO: handle exception
            printErrorMessage(e.getMessage());
        }
        run();
        sc.close();
    }
}
