import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "How can I assist you?\n" +
                "____________________________________________________________");
        while (true) {
            String text = sc.nextLine();
            if (text.equals("bye")) {
                System.out.println("Bye. Till next time!");
                break;
            }
            else if (text.equals("list")) {
                int num = 1;
                for (String task: list) {
                    System.out.println(String.format("%d. %s", num, task));
                    num++;
                }
            }
            else {
                list.add(text);
                System.out.println(String.format("added: %s", text));
            }
        }
        sc.close();
    }
}
