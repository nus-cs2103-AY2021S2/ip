package duke;

import java.util.Scanner;

import duke.common.DukeBot;

/**
 * A CLI interface for Duke. Deprecated.
 */
public class DukeCli {
    /**
     * The entry point for the Duke chatbot.
     *
     * @param args the command line args passed to Duke.
     */
    public static void main(String[] args) {
        new DukeBot("data/duke.txt").run(new Scanner(System.in));
    }
}
