import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String s = sc.nextLine();
            if (s.equals("list")) {
                int index = 1;
                for (String text : list) {
                    System.out.println(index + ". " + text);
                    index++;
                }
            } else if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                list.add(s);
                System.out.println("added: " + s);
            }
        }
    }
}
