import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<String> list = new ArrayList<>();

    static void display(String str) {
        String[] strings = str.split("\n");

        System.out.println("    " + "___________________________________________________________________");
        for (String s : strings) {
            System.out.println("    " + s);
        }
        System.out.println("    " + "___________________________________________________________________");
    }

    static void displayList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= list.size(); i++) {
            sb.append(i + ". " + list.get(i - 1) + "\n");
        }
        display(sb.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        display("Hello! I'm Duke\nWhat can I do for you?");
        while (sc.hasNextLine()) {
            String[] command = sc.nextLine().split(" ");
            if (command[0].equals("bye")) {
                display("Bye. Hope to see you again soon!");
                return;
            } else if (command[0].equals("list")) {
                displayList();
            } else {
                list.add(command[0]);
                display("added: " + command[0]);
            }
        }
    }
}
