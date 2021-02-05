import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that runs the entire Duke application
 */
public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    //@Override
    /*public void start(Stage stage) {
        /*
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        helloWorld.setFont(new Font("Arial", 50));
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
        */
        /*
        Stage s = new Stage();
        Label l = new Label("sdf");
        s.setScene(new Scene((l)));
        s.show();
        */
        /*
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(400,585);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        //puts scrollbar at the bottom
        scrollPane.setFitToWidth(true);

        dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener(
                observable -> scrollPane.setVvalue(1.0));
        //scroll down to the end every time dialogcontainer's height changes
        //listener basically reacts whenever theres a change

        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        userInput.setPrefWidth(343.0);
        //handler to handle when press enter
        userInput.setOnAction((event) -> {
            //dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            //userInput.clear();
            handleUserInput();
        });


        sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);
        sendButton.setOnMouseClicked((event) -> {
            //dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            //userInput.clear();
            handleUserInput();
        });


        AnchorPane mainLayout = new AnchorPane();
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        //affects the positioning of the nodes. use static method and not object method?? coz this is setting for the
        //node which is sendbutton
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.show();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    //not needed anymore
    /*
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    /*
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                //DialogBox.getDialog(userText, new ImageView(user), true),
                //DialogBox.getDialog(dukeText, new ImageView(duke), false)
                DialogBox.getDialog(userText, user, true),
                DialogBox.getDialog(dukeText, duke, false)
        );
        userInput.clear();
    }*/


    private static final String FOLDER_PATH = "./src/main/java/data/";
    private static final String FILE_NAME = "All Tasks.txt";

    /**
     * Runs the Duke application
     */
    /*public static void run() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        //only take in lines and not by whitespace, coz have one case where " " keeps the sc running to the next
        //String relPath = "./src/main/java/data/All Tasks.txt";//for runtest.sh put .. coz the path for that is diff
        // compared to this

        try {
            //File f = new File("./");
            //System.out.println(f.getAbsolutePath());//to get the path to see which path java is looking
            ArrayList<Task> prevTasks = FileAccessor.readFromTasks(FOLDER_PATH + FILE_NAME, new ArrayList<Task>());
            TaskList.setList(prevTasks);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            //System.out.println("EXCEPTION");
            try {
                Files.createDirectory(Paths.get(FOLDER_PATH));
                TaskList.setList(new ArrayList<Task>());
            } catch (IOException e1) {
                System.out.println(Ui.lineGetter() + " Cannot create new directory\n" + Ui.lineGetter());
            }
            //shld just be ioexception, shldnt come to this catch block
            //File f = new File(relPath); //no need to create file here will get auto created when writing
        }

        System.out.println(Ui.introduce());

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            input = Parser.trimWhiteSpaces(input);
            if (input.equals("bye")) {
                break;
            } else if (input.length() == 0) {
                //if just enter spaces
                System.out.println(Ui.informOnlySpaces());
            } else if (input.equals("list")) {
                try {
                    System.out.println(TaskList.printList());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    String[] split = Parser.splitFirstAndRest(input);
                    if (split[0].equals("done")) {
                        try {
                            int num = Parser.makeToInt(split[1]);
                            Task done = TaskList.doneTask(num - 1);
                            System.out.println(Ui.doneTask(done));
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (split[0].equals("delete")) {
                        try {
                            int num = Parser.makeToInt(split[1]);
                            Task del = TaskList.deleteTask(num - 1);
                            System.out.println(Ui.deleteTask(del, TaskList.getListSize()));
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (split[0].equals("find")) {
                        try {
                            System.out.println(TaskList.findTask(split[1]));
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (split[0].equals("todo") || split[0].equals("deadline")
                            || split[0].equals("event")) {
                        Task task;
                        if (split[0].equals("todo")) {
                            task = new Todo(split[1]);
                        } else if (split[0].equals("deadline")) {
                            task = new Deadline(split[1]);
                        } else {
                            task = new Event(split[1]);
                        }
                        TaskList.addTask(task);
                        System.out.println(Ui.addTask(task, TaskList.getListSize()));
                    } else {
                        throw new IllegalArgumentException();
                    }

                    try {
                        FileAccessor.writeToTasks(FOLDER_PATH + FILE_NAME, TaskList.getList());
                    } catch (IOException e) {
                        System.out.println(Ui.informUnableSave());
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(Ui.informIllegalArgExc());
                } catch (DateTimeParseException e) {
                    System.out.println(Ui.informDateTimeParseExc());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                    //print error specific messages
                }
            }
        }
        System.out.println(Ui.exit());
    }*/

    public String getResponse(String input) {
        //Scanner scanner = new Scanner(System.in);
        //scanner.useDelimiter("\n");
        //only take in lines and not by whitespace, coz have one case where " " keeps the sc running to the next
        //String relPath = "./src/main/java/data/All Tasks.txt";//for runtest.sh put .. coz the path for that is diff
        // compared to this

        try {
            //File f = new File("./");
            //System.out.println(f.getAbsolutePath());//to get the path to see which path java is looking
            ArrayList<Task> prevTasks = FileAccessor.readFromTasks(FOLDER_PATH + FILE_NAME, new ArrayList<Task>());
            TaskList.setList(prevTasks);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            //System.out.println("EXCEPTION");
            try {
                Files.createDirectory(Paths.get(FOLDER_PATH));
                TaskList.setList(new ArrayList<Task>());
            } catch (IOException e1) {
                System.out.println(Ui.lineGetter() + " Cannot create new directory\n" + Ui.lineGetter());
            }
            //shld just be ioexception, shldnt come to this catch block
            //File f = new File(relPath); //no need to create file here will get auto created when writing
        }

        String response = "";
        //response += Ui.introduce(); dont want to respond each time!

        input = Parser.trimWhiteSpaces(input);
        if (input.equals("bye")) {
            response += Ui.exit();
        } else if (input.length() == 0) {
            //if just enter spaces
            response += Ui.informOnlySpaces();
        } else if (input.equals("list")) {
            try {
                response += TaskList.printList();
            } catch (IllegalArgumentException e) {
                response += e.getMessage();
            }
        } else {
            try {
                String[] split = Parser.splitFirstAndRest(input);
                if (split[0].equals("done")) {
                    try {
                        int num = Parser.makeToInt(split[1]);
                        Task done = TaskList.doneTask(num - 1);
                        response += Ui.doneTask(done);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        response += e.getMessage();
                    }
                } else if (split[0].equals("delete")) {
                    try {
                        int num = Parser.makeToInt(split[1]);
                        Task del = TaskList.deleteTask(num - 1);
                        response += Ui.deleteTask(del, TaskList.getListSize());
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        response += e.getMessage();
                    }
                } else if (split[0].equals("find")) {
                    try {
                        response += TaskList.findTask(split[1]);
                    } catch (IllegalArgumentException e) {
                        response += e.getMessage();
                    }
                } else if (split[0].equals("todo") || split[0].equals("deadline")
                        || split[0].equals("event")) {
                    Task task;
                    if (split[0].equals("todo")) {
                        task = new Todo(split[1]);
                    } else if (split[0].equals("deadline")) {
                        task = new Deadline(split[1]);
                    } else {
                        task = new Event(split[1]);
                    }
                    TaskList.addTask(task);
                    response += Ui.addTask(task, TaskList.getListSize());
                } else {
                    throw new IllegalArgumentException();
                }

                try {
                    FileAccessor.writeToTasks(FOLDER_PATH + FILE_NAME, TaskList.getList());
                } catch (IOException e) {
                    System.out.println(Ui.informUnableSave());
                }
            } catch (IllegalArgumentException e) {
                response += Ui.informIllegalArgExc();
            } catch (DateTimeParseException e) {
                response += Ui.informDateTimeParseExc();
            } catch (ArrayIndexOutOfBoundsException e) {
                response += e.getMessage();
            }
        }
        return response;
    }

    /*public static void main(String[] args) {
        Duke.run();
    }*/
}
