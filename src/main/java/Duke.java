import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.time.LocalDate;

public class Duke {
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void printFileContents(String filePath, ArrayList<Task> lst) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String task = s.next();
            String line = s.nextLine();
            System.out.println(task + line);
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");

        java.nio.file.Path path = java.nio.file.Paths.get(home, "my", "app", "dir");
        boolean directoryExists = java.nio.file.Files.exists(path);

        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("____________________________");

        ArrayList<Task> lst = new ArrayList<>();

        System.out.println("Your tasks include:");
        String file = home + File.separator + "task.txt";
        try {
            printFileContents(file, lst);
        } catch (FileNotFoundException e) {
            File newFile = new File(file);
            System.out.println("File created: task.txt\n\tFolder: data");
        }

        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < lst.size(); i++) {
                    int count = i + 1;
                    Task a = lst.get(i);
                    System.out.println(count + ". " + a);
                }
                System.out.println();
            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (input.equals("done")) {
                int tag = sc.nextInt() - 1;
                Task d = lst.get(tag);
                d.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(d + "\n");
                try {
                    new FileWriter(file, false).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < lst.size(); i++) {
                    Task t = lst.get(i);
                    if (i != tag) {
                        try {
                            appendToFile(file, t.des + "\n");
                        } catch (IOException e){
                            System.out.println("OOPS!!! " + e.getMessage());
                        }
                    } else {
                        try {
                            t.markAsDone();
                            appendToFile(file, "(DONE)" + t.des + "\n");
                        } catch (IOException e){
                            System.out.println("OOPS!!! " + e.getMessage());
                        }
                    }
                }
            } else if (input.equals("delete")) {
                int tag = sc.nextInt() - 1;
                int len = lst.size() - 1;
                System.out.println("Noted. I've removed this task:");
                System.out.println("\t" + lst.get(tag));
                System.out.println("Now you have " + len + " tasks in the list.\n");
                //clear all contents
                try {
                    new FileWriter(file, false).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i <= len; i++) {
                    if (i != tag) {
                        try {
                            appendToFile(file, lst.get(i).des + "\n");
                        } catch (IOException e){
                            System.out.println("OOPS!!! " + e.getMessage());
                        }
                    }
                }
                lst.remove(tag);
            } else if (input.equals("todo")) {
                String task = sc.nextLine();
                try {
                    Todo td = Todo.makeTodo(task);
                    lst.add(td);
                    int len = lst.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + td);
                    System.out.println("Now you have " + len + " tasks in the list.\n");
                    try {
                        appendToFile(file, task + "\n");
                    } catch (IOException e) {
                        System.out.println("OOPS!!! " + e.getMessage());
                    }
                } catch (DukeException ex){
                    System.err.println("☹ OOPS!!! The description of a todo cannot be empty.\n");
                }
            } else if (input.equals("deadline")) {
                String line = sc.nextLine();
                try {
                    Deadline dl = Deadline.makeDeadline(line);
                    lst.add(dl);
                    int len = lst.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + dl);
                    System.out.println("Now you have " + len + " tasks in the list.\n");
                    try {
                        appendToFile(file,  line + "\n");
                    } catch (IOException e) {
                        System.out.println("OOPS!!! " + e.getMessage());
                    }
                } catch (DukeException ex){
                    System.err.println("☹ OOPS!!! The description of a deadline cannot be empty.\n");
                }
            } else if (input.equals("event")) {
                String line = sc.nextLine();
                try {
                    Event event = Event.makeEvent(line);
                    lst.add(event);
                    int len = lst.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + event);
                    System.out.println("Now you have " + len + " tasks in the list.");
                    try {
                        writeToFile(file, line + "\n");
                    } catch (IOException e) {
                        System.out.println("OOPS!!! " + e.getMessage());
                    }
                } catch (DukeException ex){
                    System.err.println("☹ OOPS!!! The description of an event cannot be empty.\n");
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        }
    }
}