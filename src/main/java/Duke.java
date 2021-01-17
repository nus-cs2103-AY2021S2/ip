import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> texts = new ArrayList<String>(100);

    // Divider for Duke's Hello message.
    private static String BORDER = "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-"
            + "+-+-+-+-+-+";

    // Prints a string with a 4-space indent.
    public void indentedPrint(String s) {
        System.out.println("    " + s);
    }

    // Prints the divider.
    public void printDivider() {
        indentedPrint(BORDER);
    }

    // Greeting message from Duke.
    public void greet() {
        System.out.println(BORDER);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(BORDER);
        System.out.println("How may I help you?");
    }

    // Store input string in ArrayList.
    public void addText(String s) {
        texts.add(s);
    }

    // Iterates through ArrayList and prints each element.
    public void iterateList() {
        for (int i = 0; i < texts.size(); i++) {
            this.indentedPrint((i + 1) + ". " + texts.get(i));
        }
    }
}
