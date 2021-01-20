import java.util.Scanner;

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
        while(sc.hasNext()) {
            String input = sc.next();
            if(input.equals("bye")) {
                System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
                break;
            } else {
                System.out.println(divider + input + "\n" + divider);
            }
        }
    }
}
