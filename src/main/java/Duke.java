import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private File file;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("   Hello there! I'm Duke, always here for you!");
        System.out.println("   How can I help you today?");



        ArrayList<Task> arrL = new ArrayList<>();
        SaveToFile fio = new SaveToFile(arrL);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Duke obj = new Duke();

        while (!input.equals("bye")) {
            int first = input.indexOf(" ");
            String command;
            if (first == -1) {
                command = input;
            } else {
                command = input.substring(0, first);
            }
            String date;
            System.out.println("___");
            try {
                obj.checkCommand(input, command, first, arrL.size());
                switch (command) {
                case "list":
                    for (int i = 0; i < arrL.size(); i++) {
                        System.out.println("   " + (i + 1) + ". " + arrL.get(i));
                    }
                    break;

                case "done":
                    int task_No = Integer.parseInt(input.substring(first + 1));
                    arrL.get(task_No - 1).doTask();
                    System.out.println("   Nice! I've marked this task as done:");
                    System.out.println("      " + arrL.get(task_No - 1));
                    break;

                case "todo":
                    ToDo todo = new ToDo(input.substring(first).strip());
                    arrL.add(todo);
                    System.out.println("   Got it! I've added this task:\n      " + todo);
                    System.out.println("   Now you have " + arrL.size() + " tasks in the list");
                    break;

                case "deadline":
                    int byDate = input.lastIndexOf("/by ");
                    date = input.substring(byDate + 4);
                    Deadline deadline = new Deadline(input.substring(first, byDate).strip(), date);
                    arrL.add(deadline);
                    System.out.println("   Got it! I've added this task:\n      " + deadline);
                    System.out.println("   Now you have " + arrL.size() + " tasks in the list");
                    break;

                case "event":
                    int atDate = input.lastIndexOf("/at ");
                    date = input.substring(atDate + 4);
                    Event event = new Event(input.substring(first, atDate).strip(), date);
                    arrL.add(event);
                    System.out.println("   Got it! I've added this task:\n      " + event);
                    System.out.println("   Now you have " + arrL.size() + " tasks in the list");
                    break;

                case "remove":
                    int remove_No = Integer.parseInt(input.substring(first + 1));
                    Task task = arrL.remove(remove_No - 1);
                    System.out.println("   Okay! I've removed this task:");
                    System.out.println("      " + task);
                    System.out.println("   Now you have " + arrL.size() + " tasks in the list");
                    break;
                }
                System.out.println("___");
                input = sc.nextLine();
            } catch (DukeException ex) {
                System.out.println("   " + ex.toString());
                System.out.println("___");
                input = sc.nextLine();
            }
        }
        System.out.println("___\n   Bye! Hope to see you again! :)\n___\n");
        for (int i = 0; i < arrL.size(); i++) {
            fio.writeToFile(arrL.get(i));
        }
        fio.closeFile();
    }

    void checkCommand(String input, String command, int index, int counter) throws DukeException {
        boolean test = index == -1 || input.substring(index).isBlank();
        switch (command) {
        case "todo":
            if (test) {
                throw new DukeException(" ToDo cannot be empty! :(");
            }
            break;
        case "deadline":
            if (test) {
                throw new DukeException(" Deadline cannot be empty! :(");
            } else if (!input.contains("/by")) {
                throw new DukeException(" Deadline must have a date! :(");
            } else {
                int by = input.indexOf("/by");
                if (input.substring(index, by).isBlank()) {
                    throw new DukeException(" Deadline must have a task! :(");
                } else if (input.substring(by + 3).isBlank()) {
                    throw new DukeException(" Deadline date cannot be empty! :(");
                }
            }
            break;
        case "event":
            if (test) {
                throw new DukeException(" Event cannot be empty! :(");
            } else if (!input.contains("/at")) {
                throw new DukeException(" Event must have a date! :(");
            } else {
                int at = input.indexOf("/at");
                if (input.substring(index, at).isBlank()) {
                    throw new DukeException(" Event must have a task! :(");
                } else if (input.substring(at + 3).isBlank()) {
                    throw new DukeException(" Event date cannot be empty! :(");
                }
            }
            break;
        case "list":
            break;
        case "done":
        case "remove":
            if (test) {
                throw new DukeException(" Which task? :(");
            } else {
                int no = Integer.parseInt(input.substring(index + 1));
                if (no > counter || no < 1) {
                    throw new DukeException(" No such task! :(");
                }
            }
            break;
        default:
            throw new DukeException(" I don't understand! :(");
        }
    }
}
