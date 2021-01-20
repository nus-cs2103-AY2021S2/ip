import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        List<String> contentList = new ArrayList<String>();

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                textWarper("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int num = 1;
                StringBuilder output = new StringBuilder();
                for (String i : contentList) {
                    output.append(String.format("%d. %s%n", num, i));
                    num++;
                }
                textWarper(output.toString());
            } else {
                contentList.add(input);
                textWarper("added: " + input);
            }
        }

    }

    public static void textWarper(String a) {
        System.out.println("____________________________________________________________");
        System.out.println(a);
        System.out.println("____________________________________________________________");
    }
}
