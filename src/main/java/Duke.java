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
        List<String> list = new ArrayList<>();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
                break;
            } else if(input.equals("list")) {
                System.out.print(divider);
                for(int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i-1));
                }
                System.out.println(divider);
            } else {
                list.add(input);
                System.out.println(divider + "Added: " + input + "\n" + divider);
            }
        }
    }
}
