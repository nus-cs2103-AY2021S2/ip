package jeff;

import java.util.Scanner;

public class Ui {
    private static final String INDENT = "        ";
    private static final String BORDER = INDENT + "__________________________________________________";
    private static final String LOGO = "      _       __  __  \n"
            + "     | |     / _|/ _| \n"
            + "     | | ___| |_| |_  \n"
            + " _   | |/ _ \\  _|  _|\n"
            + "| |__| |  __/ | | |   \n"
            + " \\____/ \\___|_| |_| \n";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void print(String s) {
        String[] sSplit = s.split("\n");
        System.out.println(BORDER);
        for (String line: sSplit) {
            System.out.println(INDENT + line);
        }
        System.out.println(BORDER + "\n");
    }

    public void printError(String s) {
        print("OOPS!!! Error: " + s);
    }

    public void showWelcome() {
        print("Hello I'm\n" + LOGO + "\nWhat can I do for you?");
    }

    public String readMessage() {
        return sc.nextLine();
    }
}
