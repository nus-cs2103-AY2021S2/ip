import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " _____  _    _ _____ _   _ \n" +
            "/  ___|| |  | |  ___| | | | \n" +
            "\\ `--. | |  | | |__ | |_| | \n" +
            " `--. \\| |/\\| |  __||  _  | \n" +
            "/\\__/ /\\  /\\  / |___| | | | \n" +
            "\\____/  \\/  \\/\\____/\\_| |_/ \n";

        String greeting = "Hello from\n" 
            + logo 
            + "\nYour Simple Word-Executed Helper!"
            + "\nHow can I help?\n";
                                  
        System.out.println(greeting);
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            System.out.println(Formatter.formatOut(respond(cmd)));
            if (cmd.equals("bye")) {
                break;
            }
        }

    }

    public static String respond(String command) {
        switch (command) {
            case "bye":
                return "Bye. See ya again soon!";
            default:
                return echo(command);
        }
    }

    public static String echo(String input) {
        return input;
    }
}
