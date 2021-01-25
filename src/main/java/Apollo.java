import handlers.StorageHandler;
import tasks.Task;

import utils.Formatter;

import java.util.ArrayList;

public class Apollo {
    private static final String BOT_NAME = "Apollo the Robot";
    private static final String STORAGE_PATH = "data/ApolloTaskData.txt";
    private ArrayList<Task> taskList;
    private final StorageHandler storageHandler;

    public Apollo() {
        this.taskList = new ArrayList<>();
        this.storageHandler = new StorageHandler(STORAGE_PATH);
        displayWelcomeText();
    }

    private void displayWelcomeText() {
        Formatter.printBetweenLines("Hello! I'm " + BOT_NAME + "!", "What would you like to do today?");
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
