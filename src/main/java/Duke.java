import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        printResponse(getWelcomeMsg());
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String[] words = input.trim().split(" ");
            String cmd = words[0];

            // Recombine cmdArgs for further parsing in individual cmd classes
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < words.length; i++) {
                sb.append(words[i]).append(" ");
            }
            String cmdArgs = sb.toString();

            switch (cmd) {
                case "bye":
                    printResponse(getByeMsg());
                    System.exit(0);
                default:
                    printResponse(processEcho(input));
            }
        }
    }

    public static String processEcho(String input) {
        return input;
    }

    public static String getWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo +
                "Hello! I am duke\n" +
                "What can I do for you?\n";
    }

    public static String getByeMsg() {
        return "Bye. Hope to see you again soon!\n";
    }

    public static void printResponse(String resp) {
        System.out.println("\t____________________________________________________________");
        resp.lines().forEach(line -> System.out.printf("\t%s\n", line));
        System.out.println("\t____________________________________________________________");
    }
}
