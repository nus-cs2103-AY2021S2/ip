package duke;

import duke.exceptions.DukeException;

public class Main {
    private static Duke alfred = new Duke();

    public static void main(String[] args) throws DukeException {
        alfred.run();
    }
}
