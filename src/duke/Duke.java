package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class Duke {
    public static void main(String[] args) {
        String path = "./data/duke.txt";
        String directory = "./data";

        Storage storage = new Storage(path, directory);
        TaskList taskList = new TaskList(storage);
        taskList.loadList();
        Parser parser = new Parser(taskList);
        Ui.printWelcome();
        parser.parseAll();

    }

}


