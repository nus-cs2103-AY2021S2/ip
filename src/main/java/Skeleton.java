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
    static String[] storage = new String[100];
    static int itemcount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                listItems();
            } else {
                add();
            }
        }
        terminate();
    }

    /** function that echos whatever the user types*/
    static void add(){
        System.out.println("added: " + cmd);
        storage[itemcount] = cmd;
        itemcount++;
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
