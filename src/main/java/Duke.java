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
        ArrayList<Task> list = new ArrayList<Task>();
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
                for (Task task: list) {
                    System.out.println(String.format("%d. %s", num, task));
                    num++;
                }
            }
            else if (text.split(" ")[0].equals("done")) {
                int num = Integer.parseInt(text.split(" ")[1]);
                list.get(num-1).done();
            }
            else {
                list.add(new Task(text));
                System.out.println(String.format("added: %s", text));
            }
        }
        sc.close();
    }
}
