
/** Read in user inputs and perform actions accordingly. */
public class Main {

    /**
     * Reads input containing keyword and content of task from user.
     * Add, delete, view, mark as done or exit accordingly.
     * @param args Texts entered by user.
     */ 
    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.chat();
    }
}
