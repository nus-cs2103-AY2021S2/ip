import java.io.IOException;
import java.util.*;
import java.io.File;

public class Duke {
    public static final String line = String.format("%" + 5 + "s", " ") + "____________________________________________________________________";
    public static final Scanner sc = new Scanner(System.in);
    public static List<Task> list;
    public static FileHandler fh = new FileHandler();

    public static void start() {
        System.out.println(line);
        String logo =
                "      _ __ ___   ___  _   _ _ __ ___   ___  _   _ \n" +
                "     | '_ ` _ \\ / _ \\| | | | '_ ` _ \\ / _ \\| | | |\n" +
                "     | | | | | | (_) | |_| | | | | | | (_) | |_| |\n" +
                "     |_| |_| |_|\\___/ \\__,_|_| |_| |_|\\___/ \\__,_|";
        System.out.println(logo + " is back!\n     What have you awoken MouMou for?");
        System.out.println(line);
        readData();
    }

    public static void handleCommand(String command) {
        try {
            System.out.println(line);
            Command com = new Command(list);
            list = com.handleUserCommand(command);
        } catch (DukeException ex) {
            System.out.println(ex);
        } finally {
            System.out.println(line);
        }
    }

    public static void end() {
        writeData();
        System.out.println(line);
        System.out.println(Aligner.align("Goodnight! MouMou will go back to sleep now."));
        System.out.println(line);
    }

    public static void readData() {
        try {
            list = fh.readFromFile(); //reading input and putting into list
        } catch (DukeException ex) {
            System.out.println(line);
            System.out.println(ex);
            System.out.println(line);
        } catch (IOException ex) {
            System.out.println("Oops! Something went wrong: " + ex.getMessage());
        }
    }

    public static void writeData() {
        try {
            fh.writeToFile(list);
        } catch (IOException ex) {
            System.out.println("Oops! Something went wrong: " + ex.getMessage());
        }
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
