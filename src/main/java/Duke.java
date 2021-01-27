import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Collections;

/**
 *  Juke is a chatbot that helps users keep track of tasks.
 *
 *  The chatbot supports, todos, events and deadlines,
 *      todos: add to do task to a list
 *      events: adds task to a list with a specific time for the event with "/at"
 *      deadlines: adds task to a list with a specific deadline with "/by"
 *
 *      The done command followed by an integer x checks off  task x.
 *      The chatbot supports deletion of tasks with the "delete" command
 *
 *      The chatbot will throw exceptions for invalid inputs.
 * @author branzuelajohn
 * @version CS2103T AY20/21 Semester 2, Individual Project
 */
public class Duke {
    public static final String TASK_DONE = "1";
    public static final String TASK_NOT_DONE = "0";

    public static void main(String[] args) throws Exception {

        // Level 7
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator + "data" + File.separator + "duke.txt";
        File file = new File(filePath);
        // constants
        List<Task> list = Duke.loadFile(filePath);
        Scanner sc = new Scanner(System.in);

        int counter = 0;
        int total = 0;
        Duke.printHorizontalLine();
        System.out.println("Hello! I'm Juke");
        System.out.println("What can I do for you?");
        Duke.printHorizontalLine();

        while(true) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                Duke.byeCommand();
                saveFile(list, filePath);
                break;
            } else if (s.equals("list")) {
                Duke.printHorizontalLine();
                Duke.listCommand(list);
                Duke.printHorizontalLine();
            } else {
                try {
                    if (s.contains("done")) {
                        String[] array = s.split(" ");
                        if (array.length == 1) {
                            throw new DukeException("☹ OOPS!!! I don't know which task to mark as done.");
                        }
                        if(list.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! There are no tasks to be marked as done.");
                        }
                        if(Integer.parseInt(array[1]) > list.size()) {
                            throw new DukeException("☹ OOPS!!! There is no such task to be marked as done.");
                        } else {
                            doneCommand(list, array);
                        }
                        total--;
                    } else if (s.contains("todo")) {
                        if (s.length() <= 5) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        String st = s.substring(5);
                        toDoCommand(st, list);
                        total++;
                        continue;
                    } else if (s.contains("deadline")) {
                        if (s.length() <= 9 || !s.contains("/by")) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] strArr = s.split("/by ");
                        String description = strArr[0].substring(9).trim();
                        String by = strArr[1];
                        deadlineCommand(description, by, list);
                        total++;
                        continue;

                    } else if (s.contains("event")) {
                        if (s.length() <= 6 || !s.contains("/at")) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        String[] strArr = s.split("/at ");
                        String description = strArr[0].substring(6).trim();
                        String date = strArr[1];
                        eventCommand(description, date, list);
                        total++;
                        continue;
                    } else if (s.contains("delete")) {
                        String[] strArr = s.split(" ");
                        if(strArr.length == 1) {
                            throw new DukeException("☹ OOPS!!! I don't know which task to delete.");
                        }
                        int idx = Integer.parseInt(strArr[1]) - 1;
                        deleteCommand(idx, list);
                        total--;
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            }
        }
     }

    /**
     * prints horizontal line
     */
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     *  Prints bye message in between two horizontal lines.
     */
    public static void byeCommand() {
        Duke.printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        Duke.printHorizontalLine();

    }

    /**
     *
     * @param list the list of tasks
     * @param counter the number of tasks in the list
     */
    public static void listCommand(List<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            int j = i + 1;
            Task t = list.get(i);
            System.out.println((j) + "." + t);
        }
    }

    /**
     *
     * @param list the list of tasks
     * @param array String array 
     */
    public static void doneCommand(List<Task> list, String[] array) {
        int m = Integer.valueOf(array[1]) - 1;
        Task t  = list.get(m);
        t.markAsDone();
        Duke.printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[%s] %s%n", t.getStatusIcon(), t.getDescription());
        Duke.printHorizontalLine();
    }

    /*public static void addCommand(Task t) {
        Duke.printHorizontalLine();
        System.out.printf("added: %s%n", t);
        Duke.printHorizontalLine();
    }*/

    public static void toDoCommand(String s, List<Task> list) {
        Duke.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        ToDo td = new ToDo(s);
        list.add(td);
        System.out.println(" " + td.toString());
        if(list.size() == 1) {
            System.out.printf("Now you have %d task in the list.%n", list.size());
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", list.size());
        }
        Duke.printHorizontalLine();
    }

    public static void deadlineCommand(String description, String by, List<Task> list) {
        Duke.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        Deadline d = new Deadline(description, by);
        list.add(d);
        System.out.println(" " + d.toString());
        if(list.size() == 1) {
            System.out.printf("Now you have %d task in the list.%n", list.size());
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", list.size());
        }
        Duke.printHorizontalLine();
    }

    public static void eventCommand(String description, String date, List<Task> list) {
        System.out.println("Got it. I've added this task:");
        Event e = new Event(description, date);
        list.add(e);
        System.out.println(" " + e.toString());
        if(list.size() == 1) {
            System.out.printf("Now you have %d task in the list.%n", list.size());
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", list.size());
        }
        Duke.printHorizontalLine();
    }

    public static void deleteCommand(int index, List<Task> list) {
        Task t = list.get(index);
        list.remove(index);
        System.out.println("Noted. I've removed this task:\n" + t + "\nNow you have "
                + list.size() + " tasks in the list.");
    }

    public static void saveFile(List<Task> list, String filePath) throws DukeException {
        try {
            StringBuffer sb = new StringBuffer("");

            for (int i = 0; i < list.size(); i++) {
                Task t = list.get(i);
                String taskType = t.getType();
                String isDone = t.getIsDone() ? TASK_DONE : TASK_NOT_DONE;
                String line;

                switch (taskType) {
                    case "T":
                        line = "T | " + isDone + " | " + t.getDescription();
                        break;
                    case "D":
                        Deadline d = (Deadline) t;
                        line = "D | " + isDone + " | " + d.getDescription() + " | " + d.getBy();
                        break;
                    case "E":
                        Event e = (Event) t;
                        line = "E | " + isDone + " | " + e.getDescription() + " | " + e.getDate();
                        break;

                    default:
                        throw new DukeException("Task to be written is not recognised");

                }
                if (i == list.size() - 1) {
                    sb.append(line);
                } else {
                    sb.append(line + "\n");
                }
            }

            FileWriter myWriter = new FileWriter(new File(filePath));
            myWriter.write(sb.toString());
            myWriter.close();

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            throw new DukeException("Error saving file");
        }
    }

    public static ArrayList<Task> loadFile(String filePath) throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File f = new File(filePath);

        if(f.exists() && !f.isDirectory()) {
            try {
                for(String line :  Files.readAllLines(Paths.get(filePath))) {
                    String[] dataArr = line.split(" \\| ");
                    String typeTask = dataArr[0];

                    switch (typeTask) {
                        case "T":
                            ToDo td = new ToDo(dataArr[2]);
                            if (dataArr[1].equals("1")) {
                                td.markAsDone();
                            }

                            list.add(td);
                            break;

                        case "D":
                            Deadline d = new Deadline(dataArr[2], dataArr[3]);

                            if (dataArr[1].equals("1")) {
                                d.markAsDone();
                            }

                            list.add(d);
                            break;

                        case "E":
                            Event e = new Event(dataArr[2], dataArr[3]);

                            if (dataArr[1].equals("1")) {
                                e.markAsDone();
                            }
                            list.add(e);
                            break;
                        default:
                            throw new DukeException("Line cannot be read");
                        }
                    }
                    //Collections.sort(list);
                    System.out.println("loading old file");
                    return list;
            } catch (IOException e) {
                System.out.println("Error reading file" + e);
                throw new DukeException("Error reading file");
            }
        } else {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file" + e);
                throw new DukeException("Error reading file");
            }
        }
        return new ArrayList<>();
    }
}
