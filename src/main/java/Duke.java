import java.util.Scanner;

public class Duke {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello from\n" + Duke.logo);
        System.out.println("What can I do for you?");

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.compareTo("bye") == 0) {
                System.out.println("\tDuke:");
                System.out.println("\tBye!");
                return;
            }
            System.out.println("\tDuke:");
            System.out.println("\t" + line);
        }
    }
}
