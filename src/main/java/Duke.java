import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "\n What can I do for you?");
        start();
    }

    public static void start() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String curr = sc.nextLine();
            if (curr.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (curr.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else {
                list.add(curr);
                System.out.println("added " + curr + " to the list!");
            }
        }
    }
}
