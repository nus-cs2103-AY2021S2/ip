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
        cmd = sc.nextLine();
        int i = cmd.indexOf(' ');
        if (i >= 0) {
            word = cmd.substring(0, i);
            rest = cmd.substring(i);
        }
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                listItems();
            } else if (word.equals("done")) {
                int value = Integer.parseInt(rest.replaceAll("[^0-9]", ""));
                done(value);
            } else {
                add(word, rest);
            }
            i = cmd.indexOf(' ');
            if (i >= 0) {
                word = cmd.substring(0, i);
                rest = cmd.substring(i);
            }
        }
        terminate();
    }

    /** function that adds to a storage list the type of task the user has input. */
    static void add(String type, String rest) {
        System.out.println("Wagata. Mou added shi mashita.");
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
        }
        System.out.println(storage.get(current));
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
        System.out.println();
        cmd = sc.nextLine();
        current++;
    }

    static void done(int value) {
        storage.get(value-1).markAsDone();
        System.out.println("YOSHA! KONNO TASK GA OWARIMASHITA! GANBARE");
        System.out.println(storage.get(value-1));
        System.out.println();
        cmd = sc.nextLine();
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
            cmd = sc.nextLine();
        }
    }
    /** function that exits the program when the user types bye*/
    static void terminate(){
        System.out.println("Sayonara, mata ne!");
        System.out.println();
        exit(0);
    }

    /** function that greets the user*/
    static void greet(){
        System.out.println("Konnichiwa! watashi wa Duke desu");
        System.out.println("Dou shi mashi taka?");
        System.out.println();
    }
}