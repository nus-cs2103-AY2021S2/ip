import javafx.scene.image.Image;

/**
 * Represents the chatbot Duke.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Image userimg = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeimg = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks = new TaskList(this.storage.loadTasks());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Allows user to start chatting with Duke.
     * @param input Command from user.
     * @param tasks List of tasks user has saved.
     */
    public String chat(String input, TaskList tasks) {
        String s = "";
        try {
            Command command = Parser.parse(input);
            s = command.execute(tasks, this.ui, this.storage);
        } catch (Exception e) {
            s = this.ui.showError(e.getMessage());
        }
        return s;
    }

    /**
     * Gets the appropriate response from duke.
     * @param input Command given by user.
     * @param duke Duke chatbot.
     * @return Appropriate response given command.
     */
    public String getResponse(String input, Duke duke) {
        return duke.chat(input, duke.tasks);
    }

    /**
     * Allows other classes to access the Ui of duke.
     * @return Ui of duke.
     */
    public Ui getUi() {
        return this.ui;
    }
}
