import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Skeleton class for the Duke chatbox.
 */

public class Skeleton {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Task> storage = new ArrayList<>();

    public static void main(String[] args) {
        FileManager fm = new FileManager();
        fm.loadFile();
        greet();
        Scanner sc = new Scanner(System.in);
        String cmd = sc.next();
        while (true) {
            try {
                switch (cmd) {
                    case "bye":
                        fm.saveFile();
                        terminate();
                        break;
                    case "list":
                        listItems();
                        break;
                    case "todo":
                        add(new Todo(sc.nextLine()));
                        break;
                    case "deadline":
                        String rest = sc.nextLine();
                        String[] parts = rest.split("/by");
                        add(new Deadline(parts[0], parts[1]));
                        break;
                    case "event":
                        String rest1 = sc.nextLine();
                        String[] parts1 = rest1.split("/at");
                        add(new Event(parts1[0], parts1[1]));
                        break;
                    case "done":
                        int number = sc.nextInt();
                        done(number);
                        break;
                    case "delete":
                        int no = sc.nextInt();
                        delete(no);
                        break;
                    default:
                        break;
                }
            } catch (DukeException E) {
                //System.out.println("Caught DukeException.");
                System.out.println(E);
            }
            cmd = sc.next();
        }
    }

    /** function that adds to a storage list the type of task the user has input. */
    static void add(Task task) throws DukeException {
        storage.add(task);
        System.out.println("ALRIGHT. I HAVE ALREADY ADDED THE TASK");
        System.out.println(task);
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
        System.out.println();
    }

    static void done(int value) throws DukeException {
        if (value <= 0 || value > storage.size()) {
            throw new DukeException("No such list item.");
        }
        storage.get(value-1).markAsDone();
        System.out.println("ALRIGHT. THIS TASK HAS BEEN MARKED AS COMPLETE");
        System.out.println(storage.get(value - 1));
        System.out.println();
    }

    /** function that deletes en entry in the list*/
    static void delete(int value) throws DukeException{
        if (value <= 0 || value > storage.size()) {
            throw new DukeException("No such list item.");
        }
        System.out.println("OK. TASK REMOVED.");
        System.out.println(storage.get(value - 1));
        storage.remove(value - 1);
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
        System.out.println();
    }
    /** function that lists whatever the user has stored.*/
    static void listItems() {
        if (storage.isEmpty()) {
            System.out.println("Empty list");
            System.out.println();
        } else {
            System.out.println("HERE ARE THE TASKS");
            for (int j = 0; j < storage.size(); j++) {
                System.out.println((j+1) + ". " + storage.get(j));
            }
            System.out.println();
        }
    }
    /** function that exits the program when the user types bye*/
    static void terminate() {
        System.out.println("GOOD BYE SEE YOU AGAIN!");
        System.out.println();
        sc.close();
        exit(0);
    }

    /** function that greets the user*/
    static void greet() {
        System.out.println("HELLO! I AM YOUR ROBOT");
        System.out.println("WHAT DO YOU WANT?");
        System.out.println("***********");
    }
}