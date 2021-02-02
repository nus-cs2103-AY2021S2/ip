package duke;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class Duke extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/User.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/Duwuke.jpg"));

    static class FastIO extends PrintWriter
    {
        private BufferedReader br;
        private StringTokenizer st;

        public FastIO() {
            super(new BufferedOutputStream(System.out));
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static class Task {
        /** The task that needs to be done, as a String */
        protected String todo;
        /** Whether or not the task is complete */
        protected boolean done;

        /**
         * Instantiates a task.
         *
         * @param s
         */
        public Task (String s) {
            this.todo = s;
            this.done = false;
        }

        public void setDone() {
            this.done = true;
        }

        public String getTodo() {
            return this.todo;
        }

        /**
         * Returns a String form of the current Task to be saved onto the hard disk.
         *
         * @return the Task as a String to be saved.
         */
        public String saveToData() {
            if (this.done) {
                return ("T | 1 | " + todo);
            } else {
                return ("T | 0 | " + todo);
            }
        }

        /**
         * Returns a nicely formatted String from the given LocalDateTime.
         *
         * @param date Date to be formatted.
         * @return inputted date as a String.
         */
        public String dateFormat(LocalDateTime date) {
            return (date.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")));
        }

        @Override
        public String toString() {
            if (!this.done) {
                return ("[T][ ] " + todo);
            } else {
                return ("[T][X] " + todo);
            }
        }

    }

    public static class Deadline extends Task {
        /** The date/time that the Deadline should be done by */
        private LocalDateTime doneBy;

        /**
         * Initiates a deadline, time is set to 0000 if no time is provided.
         *
         * @param s The task.
         * @param doneBy The date and time to finish the task by.
         */
        public Deadline(String s, String doneBy) {
            super(s);
            String[] split = doneBy.split("\\s+");
            if (split.length == 1) {
                this.doneBy = LocalDate.parse(doneBy).atTime(0, 0);
            } else if (split.length == 2) {
                this.doneBy = LocalDate.parse(split[0]).atTime(LocalTime.parse(split[1]));
            } else {
                throw new DateTimeParseException("", "", 1);
            }

        }

        /**
         * Returns a String form of the current Deadline to be saved onto the hard disk.
         *
         * @return the Deadline as a String to be saved.
         */
        public String saveToData() {
            if (this.done) {
                return ("D | 1 | " + todo + " | " + doneBy.toString().replace("T", " "));
            } else {
                return ("D | 0 | " + todo + " | " + doneBy.toString().replace("T", " "));
            }
        }

        @Override
        public String toString() {
            if (!this.done) {
                return ("[D][ ] " + this.todo + " (by:" + dateFormat(this.doneBy) + ")");
            } else {
                return ("[D][X] " + this.todo + " (by:" + dateFormat(this.doneBy) + ")");
            }
        }
    }

    public static class Event extends Task {
        /** The time at which the Event happens */
        private LocalDateTime time;

        /**
         * Initiates a Event, time is set to 0000 if none is provided.
         *
         * @param s The task.
         * @param time The time when the task occurs.
         * @throws DateTimeParseException
         */
        public Event(String s, String time) throws DateTimeParseException {
            super(s);
            String[] split = time.split("\\s+");
            if (split.length == 1) {
                this.time = LocalDate.parse(time).atTime(0, 0);
            } else if (split.length == 2) {
                this.time = LocalDate.parse(split[0]).atTime(LocalTime.parse(split[1]));
            } else {
                throw new DateTimeParseException("", "", 1);
            }
        }

        /**
         * Returns a String form of the current Event to be saved onto the hard disk.
         *
         * @return the Event as a String to be saved.
         */
        public String saveToData() {
            if (this.done) {
                return ("E | 1 | " + todo + " | " + time.toString().replace("T", " "));
            } else {
                return ("E | 0 | " + todo + " | " + time.toString().replace("T", " "));
            }
        }

        @Override
        public String toString() {
            if (!this.done) {
                return ("[E][ ] " + this.todo + " (at:" + dateFormat(this.time) + ")");
            } else {
                return ("[E][X] " + this.todo + " (at:" + dateFormat(this.time) + ")");
            }
        }
    }

    public class Storage {

        /** The path of the storage file where the TaskList is stored. */
        private String filePath;

        public Storage(String filePath) {
            this.filePath = filePath;
        }

        /**
         * Loads a TaskList from storage, if it exists.
         * Else creates a new storage file.
         *
         * @return the TaskList stored on the hard disk.
         */
        public TaskList load() {
            File taskData = new File(filePath);

            TaskList leest = new TaskList();

            //opens the duke.txt file to read from, creates file if not found
            try {
                Scanner s = new Scanner(taskData);
                while (s.hasNext()) {
                    String[] line = s.nextLine().split(" \\| ");
                    switch (line[0]) {
                    case "T":
                        Task task = new Task(line[2]);
                        if (line[1].equals("1")) {
                            task.setDone();
                        }
                        leest.add(task);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(line[2], line[3]);
                        if (line[1].equals("1")) {
                            deadline.setDone();
                        }
                        leest.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(line[2], line[3]);
                        if (line[1].equals("1")) {
                            event.setDone();
                        }
                        leest.add(event);
                        break;
                    default:
                    }
                }
            } catch (FileNotFoundException e) {
                try {
                    taskData.createNewFile();
                } catch (IOException f) {
                    try {
                        Path path = Paths.get("test/");
                        Files.createDirectories(path);
                        taskData.createNewFile();
                    } catch (IOException g) {
                        System.err.println("Failed to create directory uwu");
                    }
                }
            }
            return leest;
        }

        /**
         * Writes the task to a file, erasing any previous data.
         *
         * @param filePath The storage file to be written to.
         * @param textToAdd The data to be written.
         * @throws IOException If the file does not exist.
         */
        private void writeToFile(String filePath, String textToAdd) throws IOException {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd + "\n");
            fw.close();
        }

        /**
         * Appends the task to a file.
         *
         * @param filePath The storage file to be written to.
         * @param textToAdd The data to be written.
         * @throws IOException If the file does not exist.
         */
        private void appendToFile(String filePath, String textToAdd) throws IOException {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAdd + "\n");
            fw.close();
        }

        /**
         * Saves the given TaskList to a file.
         *
         * @param l The TaskList to be saved.
         */
        public void saveAsFile(TaskList l) {
            boolean isFirst = true;
            if (l.isEmpty()) {
                try {
                    writeToFile(filePath, " ");
                } catch (IOException e) {
                    System.out.println("Something went wrong uwu: " + e.getMessage());
                }
            }
            for (Task t : l.getList()) {
                try {
                    if (isFirst) {
                        writeToFile(filePath, t.saveToData());
                        isFirst = false;
                    } else {
                        appendToFile(filePath, t.saveToData());
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong uwu: " + e.getMessage());
                }
            }
        }

    }

    public static class TaskList {

        private List<Task> leest;

        public TaskList() {
            this.leest = new ArrayList<>();
        }

        public void add(Task t) {
            this.leest.add(t);
        }

        public boolean isEmpty() {
            return this.leest.isEmpty();
        }

        public int size() {
            return this.leest.size();
        }

        public Task get(int i) {
            return this.leest.get(i);
        }

        public void remove(Task t) {
            this.leest.remove(t);
        }

        public List<Task> getList() {
            return this.leest;
        }

    }

    public class Parser {

        /**
         * Parses a given input and returns Strings as necessary based on the input.
         * The main driver of the Duke class.
         *
         * @param leest The current TaskList.
         * @param input Input that is being read in.
         */
        public String parse(TaskList leest, String input) {

            String[] split = input.split("\\s+");

            switch (split[0]) {
            case "list":
                if (leest.size() == 0) {
                    return ui.emptyListMessage();
                } else {
                    return ui.showList(leest);
                }
            case "done":
                try {
                    int done = Integer.parseInt(split[1]) - 1;
                    leest.get(done).setDone();
                    storage.saveAsFile(leest);
                    return ui.setDone(leest.get(done));
                } catch (Exception e) {
                    return ui.errorMessage("invalidDone");
                }
            case "bye":
                return ui.byeBye();
            case "todo":
                try {
                    leest.add(new Task(input.substring(5)));
                    storage.saveAsFile(leest);
                    return (ui.taskAdded(leest.get(leest.size() - 1)) + "\n"
                            + ui.showTaskListSize(leest.size()));
                } catch (Exception e) {
                    return ui.errorMessage("invalidTodo");
                }
            case "deadline":
                try {
                    String[] splitagain = input.substring(9).split("/by");
                    leest.add(new Deadline(splitagain[0], splitagain[1].substring(1)));
                    storage.saveAsFile(leest);
                    return (ui.taskAdded(leest.get(leest.size() - 1)) + "\n"
                        + ui.showTaskListSize(leest.size()));
                } catch (DateTimeParseException de) {
                    return ui.errorMessage("dateTimeError");
                } catch (Exception e) {
                    return ui.errorMessage("invalidDeadline");
                }
            case "event":
                try {
                    String[] splitagain2 = input.substring(6).split("/at");
                    leest.add(new Event(splitagain2[0], splitagain2[1].substring(1)));
                    storage.saveAsFile(leest);
                    return (ui.taskAdded(leest.get(leest.size() - 1)) + "\n"
                            + ui.showTaskListSize(leest.size()));
                } catch (DateTimeParseException de) {
                    return ui.errorMessage("dateTimeError");
                } catch (Exception e) {
                    //System.out.println(e);
                    return ui.errorMessage("invalidEvent");
                }
            case "delete":
                try {
                    Task toDelete = leest.get(Integer.parseInt(split[1]) - 1);
                    leest.remove(toDelete);
                    storage.saveAsFile(leest);
                    return (ui.deleteTask(toDelete) + "\n"
                            + ui.showTaskListSize(leest.size()));
                } catch (Exception e) {
                    return ui.errorMessage("invalidDelete");
                }
            case "find":
                String toFind = input.substring(5);
                TaskList toReturn = new TaskList();
                for (Task t : leest.getList()) {
                    if (t.getTodo().contains(toFind)) {
                        toReturn.add(t);
                    }
                }
                return ui.showSearchList(toReturn);
            default:
                return ui.errorMessage("unknownInput");
            }
        }
    }

    public static class Ui {

        public Ui() {

        }

        /**
         * Greets the user with a fancy image.
         */
        public String greet() {
            return ( "Hello from\n"
                    + " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___| uwu\n"
                    + "Nyahello! I'm Duwuke, your neighbourhood disgusting weeb bot!\n"
                    + "What can I do for you? uwu");
        }

        public String showLoadingError() {
            return ("Something went wrong with the storage loading uwu");
        }

        public String emptyListMessage() {
            return ("☹ OOPS!!! Your list is currently empty uwu.");
        }

        public String showTaskListSize(int listSize) {
            return ("Now you have " + listSize + " task(s) in the list uwu");
        }

        /**
         * Prints an error message as required.
         *
         * @param s The error message to be printed.
         */
        public String errorMessage(String s) {
            switch (s) {
            case "unknownInput":
                return ("☹ OOPS!!! Sumimasen, but I don't know what that means T^T");
            case "invalidDelete":
                return ("☹ OOPS!!! Please indicate a valid task to delete uwu");
            case "invalidEvent":
                return ("☹ OOPS!!! Please define your event properly uwu.");
            case "dateTimeError":
                return ("☹ OOPS!!! Please define your todo date/time in the " +
                        "YYYY-MM-DD HH:MM format uwu.");
            case "invalidDeadline":
                return ("☹ OOPS!!! Please define your deadline properly uwu.");
            case "invalidTodo":
                return ("☹ OOPS!!! Please define your todo properly uwu.");
            case "invalidDone":
                return ("☹ OOPS!!! Please indicate a valid task to complete uwu");
            default:
                return "Something went really really wrong uwu.";
            }
        }

        /**
         * Returns String for when a task is deleted.
         *
         * @param t
         */
        public String deleteTask(Task t) {
            return ("Noted. I've removed this task uwu:\n" + t);
        }

        /**
         * Returns String for when a task is added.
         *
         * @param t
         */
        public String taskAdded(Task t) {
            return ("Hai. I've added this task:\n" + t);
        }

        public String byeBye() {
            return ("Bye, hope to see you again! uwu");
        }

        public String setDone(Task t) {
            return ("Sugoi! I've marked this task as done uwu:\n" + t);
        }

        /**
         * Prints the given TaskList in a user-friendly format.
         *
         * @param tl The TaskList to be printed.
         */
        public String showList(TaskList tl) {
            String toReturn = ("Here are the tasks in your list uwu:\n");
            int counter = 1;
            for (Task t : tl.getList()) {
                toReturn += (counter + ". " + t + "\n");
                counter++;
            }
            return toReturn;
        }

        /**
         * Prints a 'no matching tasks' message if none are found.
         * Else prints the taks out.
         *
         * @param tl The tasklist to be printed.
         */
        public String showSearchList(TaskList tl) {
            if (tl.isEmpty()) {
                return ("There are no matching tasks uwu. "
                        + "(just like how whoever wrote this has no friends.)");
            } else {
                String toReturn = ("Here are the matching tasks in your list uwu: ");
                int counter = 1;
                for (Task t : tl.getList()) {
                    toReturn += (counter + ". " + t + "\n");
                    counter++;
                }
                return toReturn;
            }
        }

    }

    /**
     * Instantiates a Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("test/duke.txt");

        try {
            taskList = storage.load();
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * Runs Duke.
     */
    public void run() {
        FastIO fio = new FastIO();
        Parser parser = new Parser();

        //ui.greet();

        // Continuously read input and performs commands until the user enters "bye"
        //while (true) {
        //    String input = fio.nextLine();
        //    parser.parse(taskList, input);
        //    if (input.equals("bye")) {
        //
        //        break;
        //    }
        //}
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    @Override
    public void start(Stage stage) {
        Parser parser = new Parser();

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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.greet(), duke));

        sendButton.setOnMouseClicked((event) -> {
            String userText = userInput.getText();
            String dukeText = parser.parse(taskList, userInput.getText());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, user),
                    DialogBox.getDukeDialog(dukeText, duke)
            );
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            //handleUserInput();
            String userText = userInput.getText();
            String dukeText = parser.parse(taskList, userInput.getText());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, user),
                    DialogBox.getDukeDialog(dukeText, duke)
            );
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

    }

    //public static void main(String[] args) {
    //   new Duke("test/duke.txt").run();
    //}
}
