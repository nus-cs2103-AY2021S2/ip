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
    static Task[] storage = new Task[100];
    static int itemcount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                listItems();
            } else if (cmd.contains("done")) {
                int value = Integer.parseInt(cmd.replaceAll("[^0-9]", ""));
                done(value);
            }
            else {
                add();
            }
        }
        terminate();
    }

    /** function that echos whatever the user types*/
    static void add(){
        System.out.println("added: " + cmd);
        storage[itemcount] = new Task(cmd);
        itemcount++;
        System.out.println();
        cmd = sc.nextLine();
    }

    static void done(int value) {
        storage[value-1].markAsDone();
        System.out.println("YOSHA! KONNO TASK WA OWARIMASHITA! GANBARE");
        System.out.println(storage[value-1]);
        System.out.println();
        cmd = sc.nextLine();
    }

    /** function that lists whatever the user has stored.*/
    static void listItems() {
        if (storage[0] == null) {
            System.out.println("Empty list");
            System.out.println();
            cmd = sc.nextLine();
        } else {
            for (int j = 0; j < itemcount; j++) {
                System.out.println((j+1) + ". " + storage[j]);
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