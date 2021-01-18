import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Skeleton class for the Duke chatbox.
 */
public class Skeleton {

    static Scanner sc = new Scanner(System.in);
    static String cmd;
    static ArrayList<Task> storage = new ArrayList<>();
    static int current  = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = "";
        String rest = "";
        greet();
        while (true) {
            try {
                cmd = sc.nextLine();
                int i = cmd.indexOf(' ');
                if (i >= 0) {
                    word = cmd.substring(0, i);
                    rest = cmd.substring(i);
                }
                if (cmd.equals("bye")) {
                    terminate();
                } else if (cmd.equals("list")) {
                    listItems();
                } else if (word.equals("done")) {
                    int value = Integer.parseInt(rest.replaceAll("[^0-9]", ""));
                    done(value);
                } else if (word.equals("delete")) {
                    int value = Integer.parseInt(rest.replaceAll("[^0-9]", ""));
                    delete(value);
                } else {
                    add(word, rest);
                }
            }
            catch (DukeException E) {
                //System.out.println("Caught DukeException.");
                System.out.println(E);
            }
        }
    }

    /** function that adds to a storage list the type of task the user has input. */
    static void add(String type, String rest) throws DukeException {
        if (type.equals("todo") && rest.equals("")) {
            throw new DukeException("Description cannot be empty");
        }
        if (type.equals("todo")) {
            storage.add(new Todo(rest));
        } else if (type.equals("deadline")) {
            int i = rest.indexOf("/") + 3;
            String by = rest.substring(i);
            rest = rest.substring(0, i - 3);
            storage.add(new Deadline(rest, by));
        } else if (type.equals("event")) {
            int i = rest.indexOf("/") + 3;
            String by = rest.substring(i);
            rest = rest.substring(0, i - 3);
            storage.add(new Event(rest, by));
        } else {
            throw new DukeException("Command UNKNOWN");
        }
        System.out.println("Wagata. Mou added shi mashita.");
        System.out.println(storage.get(current));
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
        System.out.println();
        current++;
    }

    static void done(int value) throws DukeException {
        if (value <= 0 || value > storage.size()) {
            throw new DukeException("No such list item.");
        }
        storage.get(value-1).markAsDone();
        System.out.println("YOSHA! KONNO TASK GA OWARIMASHITA! GANBARE");
        System.out.println(storage.get(value - 1));
        System.out.println();
    }

    /** function that deletes en entry in the list*/
    static void delete(int value) {
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
            cmd = sc.nextLine();
        } else {
            System.out.println("HERE ARE THE TASKS");
            for (int j = 0; j < storage.size(); j++) {
                System.out.println((j+1) + ". " + storage.get(j));
            }
            System.out.println();
        }
    }
    /** function that exits the program when the user types bye*/
    static void terminate(){
        System.out.println("Sayonara, mata ne!");
        System.out.println();
        sc.close();
        exit(0);
    }

    /** function that greets the user*/
    static void greet(){
        System.out.println("Konnichiwa! watashi wa Duke desu");
        System.out.println("Dou shi mashi taka?");
        System.out.println("***********");
    }
}