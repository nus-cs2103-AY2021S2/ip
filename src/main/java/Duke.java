import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> lst = new ArrayList<>();

    public static void initiate(Scanner s) {
        Duke.Greet();
        String input = s.nextLine();

        while(!input.equals("bye")){
            if (input.equals("list")) {
                Duke.list();
            } else {
                Duke.add(input);
            }
            input = s.nextLine();
        }
        Duke.exit();
    }

    public static void add(String msg) {
        lst.add(msg);
        System.out.println("---------------------------------------");
        System.out.println("added: " + msg);
        System.out.println("---------------------------------------");
    }

    public static void list() {
        System.out.println("---------------------------------------");
        for(int i = 0; i < lst.size(); i++) {
           System.out.println((i + 1) + ". " + lst.get(i));
        }
        System.out.println("---------------------------------------");
    }

    public static void Greet() {
        System.out.println("---------------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("---------------------------------------");
    }

    public static void echo(String msg) {
        System.out.println("---------------------------------------");
        System.out.println(msg);
        System.out.println("---------------------------------------");
    }

    public static void exit() {
        System.out.println("---------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------");
    }
}
