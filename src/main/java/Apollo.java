import tasks.Task;

import utils.Formatter;

import java.util.ArrayList;

public class Apollo {
    private static final String BOT_NAME = "Apollo the Robot";
    private ArrayList<Task> taskList;

    public Apollo() {
        this.taskList = new ArrayList<>();
        displayWelcomeText();
    }

    private void displayWelcomeText() {
        Formatter.printBetweenLines("Hello! I'm " + BOT_NAME + "!", "What would you like to do today?");
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
