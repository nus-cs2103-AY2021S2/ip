import main.java.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class Duke {


    private static final String INDENT = "         ";
    private static final String HORIZ_SEP = INDENT + "________________________________________________";
    private static final String FILE_PATH = "./data/duke.txt";

    private static void logMessage(DukeCommand command, String message, Integer numTasks, Task relevantTask) {
        if (command == DukeCommand.BYE || command == DukeCommand.WELCOME) {
            System.out.println(HORIZ_SEP + "\n" + message + HORIZ_SEP + "\n");
        } else if (command == DukeCommand.ADD_TASK) {
            System.out.println(HORIZ_SEP + "\n" +  INDENT + " Got it. I've added this task: ");
            System.out.println(INDENT + "   " + relevantTask);
            System.out.println(INDENT + " Now you have " + numTasks + " tasks in the list.");
            System.out.println(HORIZ_SEP + "\n");
        } else if (command == DukeCommand.DELETE_TASK) {
            System.out.println(HORIZ_SEP + "\n" +  INDENT + " Noted. I've removed this task ");
            System.out.println(INDENT + "   " + relevantTask);
            System.out.println(INDENT + " Now you have " + numTasks + " tasks in the list.");
            System.out.println(HORIZ_SEP + "\n");
        } else if (command == DukeCommand.DONE) {
            System.out.println(HORIZ_SEP + "\n" + INDENT + " Nice! I've marked this task as done:");
            System.out.println(INDENT + "   " + relevantTask);
            System.out.println(HORIZ_SEP + "\n");
        }
    }

    private static void writeToFile(List<Task> taskList) throws IOException {
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
                bfWriter.write(task.fileFormat() + "\n");
            }

            bfWriter.close();

        } catch(Exception err) {
            err.printStackTrace();
        }
    }

    private static List<Task> readFromFile() throws IOException {

        ArrayList<Task> tasksList = new ArrayList<>();
        final String DELIMITER = " \\| ";

        try {
            Task task = null;
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            String input = br.readLine();
            while (input != null) {
                String[] inputArr = input.split(DELIMITER);

                try {
                    switch (inputArr[0]) {
                        case "T":
                            task = new Todo(inputArr[2]);
                            break;
                        case "D":
                            task = new Deadline(inputArr[2], LocalDate.parse(inputArr[3]));
                            break;
                        case "E":
                            String[] timeParams = inputArr[3].split(" ", 2);
                            task = new Event(inputArr[2], LocalDate.parse(timeParams[0]), timeParams[1]);
                            break;
                    }

                    if (Integer.parseInt(inputArr[1]) == 1) {
                        tasksList.add(task.markAsDone());
                    } else {
                        tasksList.add(task);
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                input = br.readLine();
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasksList;
    }


    public static void main(String[] args) {

        String greeting = INDENT + " Hello! I'm Duke\n" + INDENT + " What can I do for you?\n";
        String farewell = INDENT + " Bye. Hope to see you again soon!\n";
        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);


        try {
            Path dirPath = Paths.get("data");
            Path filePath = Paths.get("data", "duke.txt");

            boolean dirExists = Files.exists(dirPath);
            boolean fileExists = Files.exists(filePath);

            if (!dirExists) {
                Files.createDirectory(dirPath);
            }
            if (!fileExists) {
                Files.createFile(filePath);
            }
            taskList = readFromFile();

        } catch (Exception err) {
            err.printStackTrace();
        }

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
                    Integer index = Integer.parseInt(params[1]) - 1;

                    if (index >= taskList.size()) {
                        throw new DukeException("No such task in the list");
                    }

                    Task removedTask = taskList.remove(Integer.parseInt(params[1]) - 1);
                    writeToFile(taskList);
                    logMessage(DukeCommand.DELETE_TASK, "", taskList.size(), removedTask);

                } else if (next.equals("list")) {
                    ListIterator<Task> taskIter = taskList.listIterator();

                    System.out.println(HORIZ_SEP);
                    System.out.println(INDENT + " Here are the tasks in your list:");
                    while (taskIter.hasNext()) {

                        Task curr = taskIter.next();

                        System.out.println(INDENT + " " + String.valueOf(taskIter.nextIndex()) + "." + curr);
                    }
                    System.out.println(HORIZ_SEP + "\n");

                } else if (params[0].equals("done")) {
                    Integer index = Integer.parseInt(params[1]) - 1;

                    if (index >= taskList.size()) {
                        throw new DukeException("No such task in the list");
                    }

                    taskList.set(index, taskList.get(index).markAsDone());
                    writeToFile(taskList);
                    Task curr = taskList.get(index);
                    logMessage(DukeCommand.DONE, "", 0, curr);


                } else if (params[0].equals("todo")) {
                    if (params.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty");
                    }

                    Todo newTask = new Todo(params[1]);
                    taskList.add(newTask);
                    writeToFile(taskList);
                    logMessage(DukeCommand.ADD_TASK, "", taskList.size(), newTask);


                } else if (params[0].equals("deadline")) {

                    if (params.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty");
                    }

                    String[] deadlineParams = params[1].split(" /by ");

                    if (deadlineParams.length == 1) {
                        throw new DukeException("deadline not given for this Deadline!");
                    }

                    Pattern pt = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                    Matcher mt = pt.matcher(deadlineParams[1]);

                    if (!mt.find()) {
                        throw new DukeException("OOPS!!! Please enter '/by YYYY-MM-DD' after description");
                    }


                    Deadline newTask = new Deadline(deadlineParams[0], LocalDate.parse(deadlineParams[1]));
                    taskList.add(newTask);
                    writeToFile(taskList);
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

                    Pattern datePt = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                    Matcher dateMt = datePt.matcher(timeParams[0]); // timeParams[0] refers to the date

                    if (!dateMt.find()) {
                        throw new DukeException("OOPS!!! Please enter '/by YYYY-MM-DD {time range}' after description");
                    }


                    Pattern timePt = Pattern.compile("\\d{1,2}-\\d{1,2}p?a?m");
                    Matcher timeMt = timePt.matcher(timeParams[1]); // timeParams[1] refers to the time

                    if (!timeMt.find()) {
                        throw new DukeException("OOPS!!! Please enter a valid time range in this format \"{start}-{end}\"" +
                                " and include am/pm after");
                    }

                    Event newTask = new Event(eventParams[0], LocalDate.parse(timeParams[0]), timeParams[1]);
                    taskList.add(newTask);
                    writeToFile(taskList);
                    logMessage(DukeCommand.ADD_TASK, "", taskList.size(), newTask);

                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
                }


            } catch(DukeException exp) {
                System.out.println(HORIZ_SEP);
                System.out.println(INDENT + " " + exp.getMessage());
                System.out.println(HORIZ_SEP + "\n");

            } catch(Exception err) {
                err.printStackTrace();
            }

        }

    }
}
