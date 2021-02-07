package duke;

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
}
