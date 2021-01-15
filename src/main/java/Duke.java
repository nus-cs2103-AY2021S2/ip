import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String LINES = "____________________________________________________________";
    public static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINES);
        System.out.println(GREETING);
        System.out.println(LINES);
        System.out.println();
        while (true){
            String command = sc.nextLine();
            if (command.equals("bye")){
                System.out.println(LINES);
                System.out.println(BYE);
                System.out.println(LINES);
                System.out.println();
                break;
            } else if (command.equals("list")){
                System.out.println(LINES);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
                System.out.println(LINES);
                System.out.println();
            } else {
                System.out.println(LINES);
                list.add(command);
                System.out.println("added: " + command);
                System.out.println(LINES);
                System.out.println();
            }
        }
        sc.close();
    }
}
