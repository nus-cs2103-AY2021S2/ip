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
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm a bot called Duke. Beep boop. \nWhat do you want?\n");
        

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            switch (command.split(" ")[0]) {
                case "list":
                    print(listToString(list));
                    break;
                default:
                    list.add(command);
                    print("Added: " + command);
            }
            command = sc.nextLine();
        }
        print("BYE AND HAVE A GOOD DAY. Beep boop.");
        sc.close();
        return;
    }

    public static void print(String text) {
        String line = "    _______________________________________\n    ";
        System.out.println(line + text + "\n" + line);
    }

    public static String listToString(List<String> list) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += String.valueOf(i+1) + ": " + list.get(i) + "\n    ";
        }

        return str.substring(0, str.length() - 5);
    }
}
