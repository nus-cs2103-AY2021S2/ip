import java.util.*;

public class Duke {
    public static final String line = String.format("%" + 5 + "s", " ") + "____________________________________________________________________";
    public static final Scanner sc = new Scanner(System.in);
    public static List<Task> list = new ArrayList<>();

    public static void start() {
        System.out.println(line);
        String logo =
                "      _ __ ___   ___  _   _ _ __ ___   ___  _   _ \n" +
                "     | '_ ` _ \\ / _ \\| | | | '_ ` _ \\ / _ \\| | | |\n" +
                "     | | | | | | (_) | |_| | | | | | | (_) | |_| |\n" +
                "     |_| |_| |_|\\___/ \\__,_|_| |_| |_|\\___/ \\__,_|";
        System.out.println(logo + " is back!\n     What have you awoken MouMou for?");
        System.out.println(line);
    }

    public static void handleCommand(String command) {
        try {
            System.out.println(line);
            Command com = new Command(list);
            list = com.handleCommand(command);
        } catch (DukeException ex) {
            System.out.println(ex);
        } finally {
            System.out.println(line);
        }
    }

    public static void end() {
        System.out.println(line);
        System.out.println(Aligner.align("Goodnight! MouMou will go back to sleep now."));
        System.out.println(line);
    }

    public static void main(String[] args) {
        start();
        String input = sc.nextLine();
        while (!input.equals("bye")) { //user input is not bye
            handleCommand(input);
            input = sc.nextLine();
        }
        end();
    }
}
