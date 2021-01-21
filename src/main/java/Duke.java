import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        ArrayList<Task> lines = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello from\n" + Duke.logo);
        System.out.println("What can I do for you?");

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.compareTo("bye") == 0) {
                System.out.println("\tDuke:");
                System.out.println("\tBye!");
                return;
            } else if (line.compareTo("list") == 0) {
                System.out.println("\tDuke:");
                for(int i = 0;i < lines.size();i++) {
                    System.out.printf("\t%d. %s\n", i + 1, lines.get(i));
                }
            } else {
                lines.add(new Task(line));
                System.out.println("\tDuke:");
                System.out.println("\tadded: " + line);
            }
        }
    }
}
