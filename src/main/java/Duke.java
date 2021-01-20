import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static void level1() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }


    static void level2() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        List<String> lst = new ArrayList<>();

        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                lst.add(input);
                System.out.println("added: " + input);
            } else {
                for (int i = 1; i <= lst.size(); i++) {
                    System.out.println(i + ". " + lst.get(i-1));
                }
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }


    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */

        level2();

    }
}
