import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * The DukeLevel class is an interactive chat bot that can:
 * - add items to a list
 * - display a list of items which were previously added
 * - mark items in the list as done
 * - remove items from the list
 * - throw exceptions if the input is incorrect
 *
 * @author Shaelyn
 * @version CS2103T 20/21 Semester 2, Individual Project
 */

public class DukeLevel {
    public static void main(String[] args) {
        ArrayList<Task> tasksArray = new ArrayList<>();

        File dukeFile = new File("src\\main\\data\\duke.txt");
        try {
            if (!dukeFile.exists()) {
                dukeFile.createNewFile();
            }
            loadData(tasksArray, dukeFile);
        } catch (Exception e) {
            e.getStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        String line = "    _________________________________________________";

        System.out.println(line);
        System.out.println("     Hello! I'm Duke :)");
        System.out.println("     What can I do for you?");
        System.out.println(line);

        while (sc.hasNext()) {
            try {
                String input = sc.nextLine().trim();
                String[] strArray = input.split(" ", 2);
                String cmd = strArray[0];

                if (input.equalsIgnoreCase("todo") || input.equalsIgnoreCase("deadline") || input.equalsIgnoreCase("event")) {
                    throw new DukeException("Uh oh! There is nothing to do.");
                }

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(line);
                    System.out.println("     Bye. Hope to see you again soon! :)");
                    System.out.println(line);
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println(line);
                    if (tasksArray.isEmpty()) {
                        System.out.println("     Your list is empty, there is nothing to do. Yay!");
                    } else {
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < tasksArray.size(); i++) {
                            System.out.println("       " + (i+1) + ". " + tasksArray.get(i).toString());
                        }
                    }
                    System.out.println(line);
                } else if (cmd.equalsIgnoreCase("done")) {
                    int cmdNum = Integer.parseInt(strArray[1]);
                    tasksArray.get(cmdNum - 1).markAsDone();
                    System.out.println(line);
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + tasksArray.get(cmdNum - 1).toString());
                    System.out.println(line);
                } else if (cmd.equalsIgnoreCase("delete")) {
                    System.out.println(line);
                    if (tasksArray.isEmpty()) {
                        System.out.println("     Oops! You have no tasks to delete.");
                    } else {
                        int cmdNum = Integer.parseInt(strArray[1]);
                        System.out.println("     Noted. I've removed this task: ");
                        System.out.println("       " + tasksArray.get(cmdNum - 1).toString());
                        tasksArray.remove(cmdNum - 1);
                    }
                    if (tasksArray.size() == 1) {
                        System.out.println("      Now you have " + tasksArray.size() + " task in the list.");
                    } else {
                        System.out.println("     Now you have " + tasksArray.size() + " tasks in the list.");
                    }
                    System.out.println(line);
                } else {
                    if (cmd.equalsIgnoreCase("todo")) {
                        System.out.println(line);
                        String cmdTask = strArray[1];
                        ToDo tempTask = new ToDo(cmdTask);
                        tasksArray.add(tempTask);
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("       " + tempTask.toString());
                    } else if (cmd.equalsIgnoreCase("deadline")) {
                        String cmdTask = strArray[1];
                        if (!strArray[1].contains("/by")) {
                            throw new DukeException("Uh oh! Please specify a timing using /by.");
                        }
                        String[] tempStrArray = cmdTask.split("/by", 2);
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                        try {
                            Date date = formatter.parse(tempStrArray[1]);
                        } catch (Exception e) {
                            throw new DukeException("Uh oh! Please enter a timing in the format dd-mm-yyyy");
                        }
                        System.out.println(line);
                        Deadline tempTask = new Deadline(tempStrArray[0], tempStrArray[1]);
                        tasksArray.add(tempTask);
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("       " + tempTask.toString());
                    } else if (cmd.equalsIgnoreCase("event")) {
                        String cmdTask = strArray[1];
                        if (!strArray[1].contains("/at")) {
                            throw new DukeException("Uh oh! Please specify a timing using /at.");
                        }
                        System.out.println(line);
                        String[] tempStrArray = cmdTask.split("/at", 2);
                        Event tempTask = new Event(tempStrArray[0], tempStrArray[1]);
                        tasksArray.add(tempTask);
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("       " + tempTask.toString());
                    } else {
                        throw new DukeException("I'm sorry but I don't know what this means :(");
                    }
                    if (tasksArray.size() == 1) {
                        System.out.println("     Now you have " + tasksArray.size() + " task in the list.");
                    } else {
                        System.out.println("     Now you have " + tasksArray.size() + " tasks in the list.");
                    }
                    System.out.println(line);
                }
            } catch (DukeException exception) {
                System.out.println(line);
                System.out.println("     " + exception);
                System.out.println(line);
            }
        }

        try {
            saveData(tasksArray, dukeFile);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private static void loadData(ArrayList<Task> tasksArrayList, File fname) throws IOException {
        Scanner sc = new Scanner(fname);
        while (sc.hasNextLine()) {
            String tempStr = sc.nextLine();
            String checkForTick = tempStr.substring(0, 5);
            String cmd = tempStr.substring(7);
            if (tempStr.contains("[T]")) {
                ToDo toDoTask = new ToDo(cmd);
                if (!checkForTick.contains(" ")) {
                    toDoTask.markAsDone();
                }
                tasksArrayList.add(toDoTask);
            } else if (tempStr.contains("[D]")) {
                String[] strArray = cmd.split("by:", 2);
                String inst = strArray[0].substring(0, strArray[0].length() - 2);
                String date = strArray[1].substring(0, strArray[1].length() - 1);
                Deadline deadlineTask = new Deadline(inst + " ", date);
                if (!checkForTick.contains(" ")) {
                    deadlineTask.markAsDone();
                }
                tasksArrayList.add(deadlineTask);
            } else if (tempStr.contains("[E]")) {
                String[] strArray = cmd.split("at:", 2);
                String inst = strArray[0].substring(0, strArray[0].length() - 2);
                String date = strArray[1].substring(0, strArray[1].length() - 1);
                Event eventTask = new Event(inst + " ", date);
                if (!checkForTick.contains(" ")) {
                    eventTask.markAsDone();
                }
                tasksArrayList.add(eventTask);
            }
        }
        sc.close();
    }

    private static void saveData(ArrayList<Task> taskArrayList, File fname) throws IOException {
        FileWriter fwriter = new FileWriter(fname);
        for (int i = 0; i < taskArrayList.size(); i++) {
            if (taskArrayList.get(i) instanceof ToDo) {
                fwriter.write(taskArrayList.get(i).toString() + "\n");
            } else if (taskArrayList.get(i) instanceof Deadline) {
                fwriter.write(taskArrayList.get(i).toString() + "\n");
            } else {
                fwriter.write(taskArrayList.get(i).toString() + "\n");
            }
        }
        fwriter.close();
    }

}


/**
 * The Task class is a parent class for ToDo, Deadline and Event
 * whereby each Task has a boolean to indicate whether it has been done
 */
class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The Task applies to inputs with sufficient information to classify as a todo, deadline
     * or event task.
     * @param description describes the details of a task supplied to the chat bot
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * For a given task, indicate whether it has been done using a space or a tick
     * @return if the task has been done, return a tick, else return a space
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick symbol
    }

    /**
     * For a given task, if it is done, mark it as done by changing the boolean isDone to true
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() { return "[" + getStatusIcon() + "] " + description; }
}

/**
 * The ToDo class is a child class of the Task Class,
 * it specifies the task as a ToDo using [T]
 */
class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * The Deadline class is a child class of the Task Class,
 * it specifies the task as a Deadline using [D]
 */
class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}

/**
 * The Event class is a child class of the Task Class,
 * it specifies the task as an Event using [E]
 */
class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}

/**
 * The DukeException is a child class of the Java Exception class
 * which throws an exception message when an invalid input is given to the chat bot
 * either a wrong message (unidentified input) or an unclear message (not specific enough)
 */
class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}