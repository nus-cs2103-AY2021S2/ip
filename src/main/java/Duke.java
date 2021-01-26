
/** Read in user inputs and perform actions accordingly. */
/*public class Duke {

    /**
     * Reads input containing keyword and content of task from user.
     * Add, delete, view, mark as done or exit accordingly.
     * @param args Texts entered by user.
     */ 
    /*public static void main(String[] args) {
        Parser chatbot = new Parser();
        chatbot.chat();
    }
}*/

public class Duke {

    private Storage storage;
    //private TaskManager tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        //tasks = new TaskManager;
        this.parser = new Parser();
    }

    public void run() {
        this.ui.greet();
        this.parser.chat();
        //this.ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
