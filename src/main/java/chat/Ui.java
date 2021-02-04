package chat;

import chat.task.Task;
import javafx.scene.image.Image;

/**
 * Ui is a class that deals with interactions with the user.
 */
public class Ui {
    
    private Image greetingChat = new Image(this.getClass().getResourceAsStream("/images/greetingCat.png"));
    private Image goodByeChat = new Image(this.getClass().getResourceAsStream("/images/goodByeCat.png"));
    private Image goChat = new Image(this.getClass().getResourceAsStream("/images/goCat.png"));
    private Image gdJobChat = new Image(this.getClass().getResourceAsStream("/images/gdJobCat.png"));
    private Image errorChat = new Image(this.getClass().getResourceAsStream("/images/errorCat.png"));
    
    private String respondString;
    private Image chatImage;

    public String getRespondString() { 
        return this.respondString;
    }
    
    public Image getChatImage() { 
        return this.chatImage;
    }
    
    /**
     * Sets all ui attributes for greeting message from Chat the Cat.
     */
    public void greet() {
        this.respondString = "Mew! I'm Chat the Cat\nWhat can I do for you?";
        this.chatImage = greetingChat;
    }

    /**
     * Sets all ui attributes for goodbye message from Chat the Cat.
     */
    public void exit() {
        this.respondString = "*** Goodbye *** ";
        this.chatImage = goodByeChat;
    }

    /**
     * Sets all ui attributes for add success message from Chat the Cat.
     * Message tells user that a task has been added successfully to list of tasks.
     * 
     * @param task Task that has been added to list of tasks.
     * @param size Total number of tasks in the list of tasks.
     */
    public void showAddSuccess(Task task, int size) {
        this.respondString = "Mew! I've added this task:\n" +
                task +
                String.format("\n** Now you have %d tasks in the list **", size);
        this.chatImage = goChat;
    }

    /**
     * Sets all ui attributes for delete success message from Chat the Cat.
     * Message tells user that a task has been deleted successfully from the list of tasks.
     * 
     * @param task Task that has been deleted from list of tasks.
     * @param size Total number of tasks in the list of tasks.
     */
    public void showDeleteSuccess(Task task, int size) {
        this.respondString = "Mew! I've removed this task:\n" + 
                task + 
                String.format("\n** Now you have %d tasks in the list **", size);
        this.chatImage = goChat;
    }

    /**
     * Sets all ui attributes for well done message from Chat the Cat.
     * Message tells user that a task has been marked successfully as done.
     * 
     * @param task Task Task that has been completed.
     */
    public void showWellDone(Task task) {
        this.respondString =  "Mew! I've marked this task as done:\n" + 
                task + 
                "\n* Good job, you deserve a kit-kat *";
        this.chatImage = gdJobChat;
    }

    /**
     * Sets all ui attributes for error message from Chat.
     * Message tells user that Chat has encountered an error when loading tasks from data on hard disk.
     */
    public void showLoadingError() { 
        this.respondString = new ChatException("Error loading file").getMessage();
        this.chatImage = errorChat;
    }

    /**
     * Sets all ui attributes for error message from Chat.
     * Message tells user that Chat has encountered an error.
     * 
     * @param e Error message from Chat to user.
     */
    public void showError(ChatException e) { 
        this.respondString = e.getMessage();
        this.chatImage = errorChat;
    }

    /**
     * Sets all all ui attributes for list output from Chat.
     * Output lists tasks from list of tasks from TaskList object.
     * 
     * @param taskList TaskList object that contains a list of tasks.
     */
    public void list(TaskList taskList) {
        int i = 0;
        String str = " * list *\n";
        while (i < taskList.getTasks().size()) {
            str += Integer.toString(i + 1) + ". " + taskList.getTasks().get(i) + "\n";
            i++;
        }
        
        //string without last /n at the back
        this.respondString = str.substring(0, str.length() - 1);
        this.chatImage = greetingChat;
    }
    
}
