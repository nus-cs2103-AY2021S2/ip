import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DukeList list = new DukeList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\n"
                + logo + "\nHello! I'm Duke\n" + "What can I do for you?\n"
                + "____________________________________________________________\n");
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________\n"
                        + "Good bye! Stay calm and keep coding o7"
                        + "\n____________________________________________________________\n");
                sc.close();
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                list.printAll();
                System.out.println("____________________________________________________________\n");
            } else if (input.contains("done")) {
                String[] arr = input.split(" ");
                int x = Integer.parseInt(arr[1]) - 1;
                list.done(x);
                System.out.println("____________________________________________________________\n"
                        + "Good job! I've marked this task as done:\n" + "  " + list.get(x)
                        + "\n____________________________________________________________\n");
            } else {
                Task curr = new Task(input);
                list.add(curr);
                System.out.println("____________________________________________________________\n"
                        + "added: "
                        + input
                        + "\n____________________________________________________________\n");
            }
        }
    }
}
