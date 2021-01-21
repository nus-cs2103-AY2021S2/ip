import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        ArrayList<String> lst = new ArrayList<>();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                for (int i = 0; i < lst.size(); i++) {
                    int count = i + 1;
                    System.out.println(count + ". " + lst.get(i));
                }
            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else {
                lst.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
