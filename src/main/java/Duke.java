import java.util.Scanner;
import java.util.Vector;

public class Duke {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";

    /**
     * Wrap line(s) with horizontal lines and indent using tab
     * @param lines: array of strings
     */
    private static void wrappedPrint(String[] lines) {
        System.out.println(HORIZONTAL_LINE);
        for (String l: lines) {
            System.out.println("\t " + l);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Wrap single line with horizontal lines and indent using tab
     */
    private static void wrappedPrint(String line) {
        wrappedPrint(new String[]{line});
    }

    private static void printHistory(Vector<String> commandHistory) {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < commandHistory.size(); i++) {
            System.out.printf("\t %d: %s%n", i + 1, commandHistory.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        // Initial greeting
        wrappedPrint(new String[]{"Yo! I'm Ekud!", "What you want?"});

        // store whatever is given until bye is detected
        Scanner input = new Scanner(System.in);
        Vector<String> commandHistory = new Vector<>();
        String command;
        boolean active = true;

        do {
            command = input.nextLine();
            switch (command) {
                case "list":
                    printHistory(commandHistory);
                    break;
                case "bye":
                    wrappedPrint("Bye bye. Anything call me ah!");
                    active = false;
                    break;
                default:
                    commandHistory.add(command);
                    wrappedPrint("added: " + command);
            }
        } while (active);
    }
}
