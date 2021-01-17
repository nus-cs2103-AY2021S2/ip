import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " _____      _               _   _             \n" +
                "/  ___|    | |             | | (_)            \n" +
                "\\ `--.  ___| |__   __ _ ___| |_ _  __ _ _ __  \n" +
                " `--. \\/ _ \\ '_ \\ / _` / __| __| |/ _` | '_ \\ \n" +
                "/\\__/ /  __/ |_) | (_| \\__ \\ |_| | (_| | | | |\n" +
                "\\____/ \\___|_.__/ \\__,_|___/\\__|_|\\__,_|_| |_|\n" +
                "                                              \n" +
                "                                              ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("Enter a command below for me to assist you");

        boolean shouldRun = true;
        while (shouldRun) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                echo("Bye. Hope to see you again soon!");
                shouldRun = false;
            } else {
                echo(input);
            }
        }
    }

    public static void echo(String input) {
        System.out.println("------------------------------");
        System.out.println(input);
        System.out.println("------------------------------");
    }
}
