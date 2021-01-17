import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    static void display(String str) {
        String[] strings = str.split("\n");
        ArrayList<String> displayedStrings = new ArrayList<>();
        int longest = 0;

        for (String s : strings) {
            if (s.length() > longest) longest = s.length();
            displayedStrings.add("    | " + s);
        }

        String border = "     " + "-".repeat(longest + 2);
        System.out.println(border);

        for (String s : displayedStrings) {
            String rightSpace = " ".repeat(longest - s.length() + 7);
            System.out.println(s + rightSpace + "|");
        }
        System.out.println(border);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        display("Hello! I'm Duke\nWhat can I do for you?");
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                display("Bye. Hope to see you again soon!");
                return;
            } else {
                display(command);
            }
        }
    }
}
