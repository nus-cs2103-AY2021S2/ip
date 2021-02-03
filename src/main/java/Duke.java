import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/20191231_194653.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/20191231_194232.jpg"));

    /**
     * provides a platform for users to key in tasks to add to the list
     * @param args
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("____________________________");

        Storage st = new Storage();
        st.initialise(st.getDefaultFilePath());
        ArrayList<Task> lst = new ArrayList<>();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            TaskList tl = new TaskList();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else {
                tl.createTask(input, sc, lst);
            }
        }
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    ArrayList<Task> lst = new ArrayList<>();
    public String getResponse(String input) {
        Scanner sc = new Scanner(System.in);
        String[] splited = input.split("\\s+");
        return TaskList.guiTask(splited, lst);
    }

}