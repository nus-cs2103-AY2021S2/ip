package apollo;

import exceptions.DukeException;
import handlers.StorageHandler;
import tasks.Task;

import utils.Formatter;

import java.io.IOException;
import java.util.ArrayList;

public class Apollo {
    private static final String BOT_NAME = "Apollo the Robot";
    private static final String STORAGE_PATH = "data/ApolloTaskData.txt";
    private ArrayList<Task> taskList;
    private final StorageHandler storageHandler;

    public Apollo() {
        this.storageHandler = new StorageHandler(STORAGE_PATH);
        displayWelcomeText();

        try {
            this.taskList = this.storageHandler.readFile();
        } catch (DukeException e) {
            this.taskList = new ArrayList<>();
        }
    }

    private void displayWelcomeText() {
        Formatter.printBetweenLines("Hello! I'm " + BOT_NAME + "!", "What would you like to do today?");
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void saveBeforeExit() {
        try {
            storageHandler.writeFile(taskList);
        } catch (IOException | DukeException e) {
            Formatter.printBetweenLines(e.getMessage());
        }
    }
}
