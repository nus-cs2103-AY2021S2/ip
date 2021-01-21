import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String h_rule = "────────────────────────────────────────────────────────────────────";
        System.out.println("Greetings! I am Duke! How may I assist you?\n" + h_rule);

        Scanner sc = new Scanner(System.in);

        String raw_in;
        String[] input;
        List<Task> itemList = new ArrayList<>();

        while (true) {
            raw_in = sc.nextLine().trim();
            input = raw_in.split(" ");
            System.out.println(h_rule);
            if (raw_in.equals("bye")) {
                System.out.println("Goodbye! Have a nice day!\n");
                break;
            } else {
                switch (input[0]) {
                    case "list":
                        int i = 1;
                        for (Task s : itemList) {
                            System.out.println(i + s.toString());
                            i++;
                        }
                        break;
                    case "done":
                        Task task = itemList.get(Integer.parseInt(input[1]) - 1);
                        task.markDone();
                        System.out.println("Alright, I will mark this as done. \n" + input[1] + task.toString());
                        break;
                    default:
                        itemList.add(new Task(raw_in));
                        System.out.println("Added " + raw_in);
                }
            }
            System.out.println(h_rule);
        }
        sc.close();
    }
}
