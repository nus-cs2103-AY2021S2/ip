import java.util.Scanner;

import duke.common.DukeBot;

public class Duke {
    /**
     * The entry point for the Duke chatbot.
     * @param args the command line args passed to Duke
     */
    public static void main(String[] args) {
        new DukeBot(new Scanner(System.in), "data/duke.txt").run();
    }
}
