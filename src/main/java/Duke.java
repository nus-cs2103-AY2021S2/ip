import main.java.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class Duke {


    private static final String indent = "         ";
    private static final String horizSep = indent + "________________________________________________";
    private static final List<Task> taskList = new ArrayList<>();

    private static void logMessage(DukeCommand command, String message, Integer numTasks, Task relevantTask) {
        if (command == DukeCommand.BYE || command == DukeCommand.WELCOME) {
            System.out.println(horizSep + "\n" + message + horizSep + "\n");
        } else if (command == DukeCommand.ADD_TASK) {
            System.out.println(horizSep + "\n" +  indent + " Got it. I've added this task: ");
            System.out.println(indent + "   " + relevantTask);
            System.out.println(indent + " Now you have " + numTasks + " tasks in the list.");
            System.out.println(horizSep + "\n");
        } else if (command == DukeCommand.DELETE_TASK) {
            System.out.println(horizSep + "\n" +  indent + " Noted. I've removed this task ");
            System.out.println(indent + "   " + relevantTask);
            System.out.println(indent + " Now you have " + numTasks + " tasks in the list.");
            System.out.println(horizSep + "\n");
        } else if (command == DukeCommand.DONE) {
            System.out.println(horizSep + "\n" + indent + " Nice! I've marked this task as done:");
            System.out.println(indent + "   " + relevantTask);
            System.out.println(horizSep + "\n");
        }
    }

    private static void writeToFile() {
//        String home = System.getProperty("user.home");

//        Path dirPath = Paths.get(home, "data");
//        Path filePath = Paths.get(home,"data", "duke.txt");

        Path dirPath = Paths.get("data");
        Path filePath = Paths.get("data", "duke.txt");

//        System.out.println(dirPath);
//        System.out.println(filePath);

        try {
            boolean dirExists = Files.exists(dirPath);
            boolean fileExists = Files.exists(filePath);

            if (!dirExists) {
                Files.createDirectory(dirPath);
            }
            if (!fileExists) {
                Files.createFile(filePath);
            }

            BufferedWriter bfWriter = Files.newBufferedWriter(filePath);

            for (Task task : taskList) {
                bfWriter.write(task.toString() + "\n");
            }
            bfWriter.flush();
            bfWriter.close();

        } catch(IOException err) {
            err.printStackTrace();
        }
    }

//    private static void readFromFile() {
//
//    }


    public static void main(String[] args) {

        String greeting = indent + " Hello! I'm Duke\n" + indent + " What can I do for you?\n";
        String farewell = indent + " Bye. Hope to see you again soon!\n";
        Scanner sc = new Scanner(System.in);


        logMessage(DukeCommand.WELCOME, greeting, 0, null);

        while (sc.hasNextLine()) {

            String next = sc.nextLine();
            String[] params = next.split(" ", 2);

            try {

                if (next.equals("bye")) {

                    logMessage(DukeCommand.BYE, farewell, 0, null);
                    sc.close();
                    return;

                } else if (params[0].equals("delete")) {

                    try {
                        Integer index = Integer.parseInt(params[1]) - 1;

                        if (index >= taskList.size()) {
                            throw new DukeException("No such task in the list");
                        }

                        Task removedTask = taskList.remove(Integer.parseInt(params[1]) - 1);
                        writeToFile();
                        logMessage(DukeCommand.DELETE_TASK, "", taskList.size(), removedTask);

                    } catch (Exception err) {
                        throw new DukeException(err.getMessage());
                    }



                } else if (next.equals("list")) {
                    ListIterator<Task> taskIter = taskList.listIterator();

                    System.out.println(horizSep);
                    System.out.println(indent + " Here are the tasks in your list:");
                    while (taskIter.hasNext()) {

                        Task curr = taskIter.next();

                        System.out.println(indent + " " + String.valueOf(taskIter.nextIndex()) + "." + curr);
                    }
                    System.out.println(horizSep + "\n");

                } else if (params[0].equals("done")) {

                    Integer index = Integer.parseInt(params[1]) - 1;

                    if (index >= taskList.size()) {
                        throw new DukeException("No such task in the list");
                    }

                    taskList.set(index, taskList.get(index).markAsDone());

                    Task curr = taskList.get(index);
                    logMessage(DukeCommand.DONE, "", 0, curr);


                } else if (params[0].equals("todo")) {
                    if (params.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty");
                    }

                    Todo newTask = new Todo(params[1]);
                    taskList.add(newTask);
                    writeToFile();
                    logMessage(DukeCommand.ADD_TASK, "", taskList.size(), newTask);


                } else if (params[0].equals("deadline")) {

                    if (params.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty");
                    }

                    String[] deadlineParams = params[1].split(" /by ");

                    if (deadlineParams.length == 1) {
                        throw new DukeException("deadline not given for this Deadline!");
                    }



                    Deadline newTask = new Deadline(deadlineParams[0], deadlineParams[1]);
                    taskList.add(newTask);
                    writeToFile();
                    logMessage(DukeCommand.ADD_TASK, "", taskList.size(), newTask);

                } else if (params[0].equals("event")) {

                    if (params.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty");
                    }

                    String[] eventParams = params[1].split(" /at ");

                    if (eventParams.length == 1) {
                        throw new DukeException("no date and time given for this Event!");
                    }

                    String[] timeParams = eventParams[1].split(" ");

                    if (timeParams.length == 1) {
                        throw new DukeException("time of Event was not specified!");
                    }

                    Pattern pt = Pattern.compile("\\d-\\dp?a?m");
                    Matcher mt = pt.matcher(timeParams[1]);

                    if (!mt.find()) {
                        throw new DukeException("Invalid time range for the event!");
                    }

                    Event newTask = new Event(eventParams[0], eventParams[1]);
                    taskList.add(newTask);
                    writeToFile();
                    logMessage(DukeCommand.ADD_TASK, "", taskList.size(), newTask);

                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
                }


            } catch(DukeException exp) {
                System.out.println(horizSep);
                System.out.println(indent + " " + exp);
                System.out.println(horizSep + "\n");
            }


        }

    }
}
