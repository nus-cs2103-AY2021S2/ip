import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";

    /**
     * Wrap single line with horizontal lines and indent using tab
     */
    private static void wrappedPrint(String line) {
        wrappedPrint(new String[]{line});
    }

    /**
     * Wrap line(s) with horizontal lines and indent using tab
     * @param lines
     */
    private static void wrappedPrint(String[] lines) {
        System.out.println(HORIZONTAL_LINE);
        for (String l: lines) {
            System.out.println("\t " + l);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        // Initial greeting
        wrappedPrint(new String[]{"Yo! I'm Ekud!", "What you want?"});

        // echo whatever is given until bye is detected
        Scanner input = new Scanner(System.in);
        String command = input.next();
        while (!command.equals("bye")) {
            wrappedPrint(command);
            command = input.next();
        }
        wrappedPrint("Bye bye. Anything call me ah!");
    }
}
