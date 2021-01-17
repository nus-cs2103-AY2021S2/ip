import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Skeleton class for the Duke chatbox.
 */
public class Skeleton {
    static Scanner sc = new Scanner(System.in);
    static String cmd;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            echo();
        }
        terminate();
    }

    /** function that echos whatever the user types*/
    static void echo(){
        System.out.println(cmd);
        cmd = sc.nextLine();
    }

    /** function that exits the program when the user types bye*/
    static void terminate(){
        System.out.println("Sayonara, mata ne!");
        exit(0);
    }

    /** function that greets the user*/
    static void greet(){
        System.out.println("Konnichiwa! watashi wa Duke desu");
        System.out.println("Dou shi mashi taka?");
    }
}
