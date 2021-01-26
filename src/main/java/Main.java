import duke.Duke;
import exception.DukeDateFormatException;
import exception.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();

        while (sc.hasNextLine()) {
            try {
                if (duke.parse(sc.nextLine()) == 0) break;
            } catch (DukeException | IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}