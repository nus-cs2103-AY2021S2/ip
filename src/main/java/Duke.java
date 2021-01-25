import utils.Formatter;
import utils.Parser;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Apollo apollo = new Apollo();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            try {
                Parser.handleInput(input, apollo.getTaskList());
            } catch (Exception e) {
                Formatter.printBetweenLines("An error occurred:", e.getMessage());
            }
        }
    }
}
