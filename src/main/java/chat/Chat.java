package chat;

import chat.command.Command;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Class that executes Chat the Cat.
 */
public class Chat {
    
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialise Chat the Cat by loading data from file to create list of tasks.
     * 
     * @param filePath Path of the file that is read from.
     */
    public Chat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ui.greet();
            taskList = new TaskList(storage.load());
        } catch (ChatException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Returns storage attribute of Chat.
     * 
     * @return storage
     */
    public Storage getStorage() { 
        return this.storage;
    }

    /**
     * Returns taskList attribute of Chat.
     * 
     * @return taskList
     */
    public TaskList getTaskList() { 
        return this.taskList;
    }

    /**
     * Returns ui attribute of Chat.
     * 
     * @return ui
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Method that runs Chat to get a response to user's input.
     * It also sets ui object to have the latest response string and image based on the given command by user.
     * @param input
     */
    public void run(String input) {
        try {
            Command c = Parser.parse(input);

            //in execute method of command
            //ui will be updated with the latest response from chat
            c.execute(taskList, ui, storage);
            
            //stage will close as command is bye and user wants to exit
            if (c.isExit()) {
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }
            
        } catch (ChatException e) {
            
            //ui's respondString is updated with error message 
            ui.showError(e);
        }
    }

}
