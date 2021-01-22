import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Scanner sc = new Scanner (System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! What can I do for you:>");

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + "." + list.get(i));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. See you again!");
    }
}
