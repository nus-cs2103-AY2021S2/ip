import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Main class for Duke.
 *
 */

public class Duke  extends Application{
    /**
     * Driver code.
     */
    public static final String REGEX_SPACE = "\\s+";


    private static String FILE_PATH = "./data/";
    private static String FILE_NAME = "data.txt";
    private static String hello = "What can I do for you?\n";
    private static String goodbye = "Bye. Hope to see you again soon!\n";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Storage storage;
    public TaskList tasklist;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke(String filePath, String fileName) throws FileNotFoundException {
        storage = new Storage(filePath, fileName);
        assert(user != null); // check user image exists
        assert(duke != null); // check duke image exists
        try {
            tasklist = new TaskList(readInput(storage.readFile()));
        }
        catch (Exception e){
            throw new FileNotFoundException("No File Found!");
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        assert(FILE_NAME != null); // check user image exists
        assert(FILE_PATH != null); // check duke image exists
        Duke duke = new Duke(FILE_NAME,FILE_NAME);
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        while(sc.hasNext()) {
            duke.doCommand(word);
            word = sc.nextLine();
        }
        duke.doCommand("bye");
    }

    /**
     * Find a task.
     * @param tasks list of tasks.
     * @param s the key string.
     */

    private static String findTasks(TaskList tasks, String s) {
        TaskList query = tasks.find(s);
        return  Duke.printTasks(query);
    }

    /**
     * Delete a task.
     * @param tasks list of tasks.
     * @param n the target index.
     */

    private static String deleteTask(TaskList tasks, int n) {
        String response = "";
        try {
            Task t = tasks.get(n);
            response = "Noted. I've removed this task: ";
            tasks.remove(n);
            response = response + "\n" +t.toString();
            int size = tasks.size();
            response = response + "\n" + printTotalTasks(size);
            return response;
        }
        catch(IndexOutOfBoundsException e){
            response = "Wrong task ID";
            return response;
        }
    }

    /**
     * Print the number of tasks
     * @param size the total of tasks.
     */

    private static String printTotalTasks(int size) {
        String msg;
        if(size != 1) {
             msg = "Now you have " + size + " tasks in the list";
        }
        else{
             msg = "Now you have " + size + " task in the list";
        }
        return msg;
    }

    /**
     * Mark a task as done.
     * @param tasks list of tasks.
     * @param n target index.
     */

    private static String doneTask(TaskList tasks, int n) {
        String response;
        try {
            tasks.set(n, tasks.get(n).finish());
            response = "Nice! I've marked this task as done: \n";
            response = response + tasks.get(n).toString();
            return response;
        }
        catch(IndexOutOfBoundsException e){
            response = "Wrong task ID";
            return response;
        }

    }

    /**
     * Print an error.
     */

    private static String addError() {
        return "Command not understood";
    }

    /**
     * Add a todo task
     * @param tasks list of tasks.
     * @param pre input from user.
     */

    private static String addToDo(TaskList tasks, String[] pre) {
        String response;
        if (pre.length > 0) {
            tasks.add(Parser.parseTodo(pre));
            response = "Got it. I've added this task:";
            response = response + "\n" + tasks.get(tasks.size() - 1).toString() + "\n" +
                printTotalTasks(tasks.size());
            return response;
        }
        else {
            return "Please add a description for todo.";
        }
    }

    /**
     * Add a event task
     * @param tasks list of tasks.
     * @param cmd input from user.
     */

    private static String addEvent(TaskList tasks, String cmd) {
        String[] pre2 = cmd.split("/at");
        String response;
        try {
            tasks.add(Parser.parseEvent(pre2));
            response = "Got it. I've added this task:";
            response = response + "\n" + tasks.get(tasks.size() - 1).toString() + "\n" +
                    printTotalTasks(tasks.size());
            return response;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return "Please enter a description for event";
        }
    }

    /**
     * Add a deadline task
     * @param tasks list of tasks.
     * @param cmd input from user.
     */

    private static String addDeadline(TaskList tasks, String cmd) {
        String[] pre2 = cmd.split("/by");
        String response;
        try {
            tasks.add(Parser.parseDeadlinne(pre2));
            response = "Got it. I've added this task:";
            response = response + "\n" + tasks.get(tasks.size() - 1).toString() + "\n" +
                    printTotalTasks(tasks.size());
            return response;
        }
        catch (ArrayIndexOutOfBoundsException  e) {
            return "Please enter a description for deadline";
        }
    }

    /**
     * Read input from an array of strings
     * @param strings array to convert.
     * @return the arrayList of tasks.
     */

    private static ArrayList<Task> readInput(ArrayList<String> strings) {
        ArrayList<Task> tasks = new ArrayList<>();
        for(String i: strings) {
            String[] texts = i.split(REGEX_SPACE);
            switch(texts[0].charAt(1)) {
                case 'D':
                    tasks.add(Parser.stringToDeadline(i));
                    break;
                case 'T':
                    tasks.add(Parser.stringToTodo(i));
                    break;
                case 'E':
                    tasks.add(Parser.stringToEvent(i));
                    break;
            }
        }
    return tasks;
    }

    /**
     * Sort list of tasks alphabetically
     * @param taskList list of tasks to convert.
     */

    public String sort(TaskList taskList){
        String response = "List Sorted";
        taskList = taskList.sort();
        return response;

    }
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


    }
    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public String doCommand(String word){
        String[] pre = word.split(REGEX_SPACE);
        String response = "";
        switch (pre[0]) {
            case "bye":
                return "Good Bye~~";
            case "list" :
                response = Duke.printTasks(tasklist);
                storage.writeTasks(tasklist);
                break;
            case "done" :
                int n = parseInt(pre[1]) - 1;
                response = doneTask(tasklist, n);
                storage.writeTasks(tasklist);
                break;
            case "delete" :
                n = parseInt(pre[1]) - 1;
                response = deleteTask(tasklist, n);
                storage.writeTasks(tasklist);
                break;
            case "deadline" :
                response = addDeadline(tasklist, word);
                storage.writeTasks(tasklist);
                break;
            case "event" :
                response = addEvent(tasklist, word);
                storage.writeTasks(tasklist);
                break;
            case "todo" :
                response = addToDo(tasklist, pre);
                storage.writeTasks(tasklist);
                break;
            case "find" :
                response = findTasks(tasklist,pre[1]);
                break;
            case "sort":
                response = sort(tasklist);
                storage.writeTasks(tasklist);
                break;
            default :
                 response = addError();
            }
            return response;
    }
    public static String printTasks(TaskList tasks) {
        String response = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            response = response + i + ". " + tasks.get(i-1) + "\n";
        }
        return response;
    }


}