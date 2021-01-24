import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) throws DukeException, FileNotFoundException {
        DukeController simulator = new DukeController();
        simulator.run();
    }
}
