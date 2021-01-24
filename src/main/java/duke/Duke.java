package duke;

import duke.task.Task;

import static duke.Parser.parseAndProcessInput;
import static duke.data.Data.*;

import static duke.Ui.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> tasks;

    public static void main(String[] args) {
        try {
            displayWelcome();
            tasks = initialiseList();
            parseAndProcessInput();
            displayFarewell();
        } catch (Exception e) {
            displayError(e.getMessage());
        }
    }
}

