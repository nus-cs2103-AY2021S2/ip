package DukeTools;

import java.util.Scanner;

public class Parser {
    public String read;

    Scanner scan = new Scanner(System.in);

    public void readLine() {
        this.read = scan.nextLine().trim();
    }

    public String getCommand(String input) {
        String command = input.split(" ")[0];
        return command;
    }

    public String getArguments(String input) {
        String arguments = input.split(" ", 2)[1];
        return arguments;
    }

    public int getDeleteIndex(String input) {
        int i = Integer.parseInt(input.split(" ")[1]) - 1;
        return i;
    }
}
