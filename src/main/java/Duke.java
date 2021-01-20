import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String divider = "____________________________________________________________\n";
        System.out.println(divider + logo + "\nHello! I'm Duke\nWhat can I do for you?\n" + divider);

        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
                break;
            } else if(input.equals("list")) {
                System.out.print(divider);
                for(int i = 1; i <= list.size(); i++) {
                    Task curr = list.get(i-1);
                    System.out.println(i + ".[" + curr.getStatusIcon() + "] " + curr.description);
                }
                System.out.println(divider);
            } else {
                String[] check = input.split(" ");
                if(check[0].equals("done")) {
                    Task curr = list.get(Integer.parseInt(check[1])-1);
                    curr.markAsDone();
                    System.out.println(divider + "Nice! I've marked this task as done:\n[\u2713] " + curr.description + "\n" + divider);

                } else {
                    list.add(new Task(input));
                    System.out.println(divider + "Added: " + input + "\n" + divider);
                }
            }
        }
    }
}
