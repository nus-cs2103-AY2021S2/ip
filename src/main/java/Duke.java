
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import exception.DukeException;
import exception.DukeInvalidInputException;
import gui.MainWindow;
import gui.TextFieldInputStream;
import javafx.application.Application;
import javafx.stage.Stage;

public class Duke extends Application {

    protected static Storage storage = new Storage();
    protected static TaskList taskList = new TaskList(storage);

    /**
     * Entry point for program
     * 
     * @param args
     *                 command line arguments
     * @throws IOException
     *                         When file cannot be read.
     */
    public static void main(String[] args) throws IOException {

        UI.greet(System.out);

        storage.tasks = new ArrayList<>(100);
        storage.inputs = new ArrayList<>(100);
        storage.loadHistory();

        chatLoop(System.in, System.out);

    }

    MainWindow window = new MainWindow();
    Thread t;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window.createChatWindow();
        primaryStage.setScene(window.getScene());

        UI.greet(window.getOutputStream());

        storage.tasks = new ArrayList<>(100);
        storage.inputs = new ArrayList<>(100);
        storage.loadHistory();

        primaryStage.show();

        t = new Thread(() -> {
            try {
                chatLoop(window.getInputStream(), window.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

    @Override
    public void stop() {
        t.stop();
    }

    protected static void chatLoop(InputStream in, OutputStream out) throws IOException {

        PrintStream writer = getWriter(out);
        String line = readLine(in);
        while (line != null) {
            try {
                writer.println(parseInput(line));
                storage.inputs.add(line);
                storage.saveHistory();
            } catch (DukeException e) {
                handleException(e, writer);
            } catch (Exception e) {
                writer.println(e.toString());
            }
            if (line.equals("bye")) {
                break;
            }
            line = readLine(in);
        }
    }

    private static void handleException(DukeException e, PrintStream writer) {
        writer.println(e.getMessage());
    }

    protected static String parseInput(String input) throws DukeException {
        HashMap<String, String> tokenizedInput = Parser.parseInput(input);
        switch (tokenizedInput.get("command")) {
        case "bye":
            return "Bye. Hope to see you again soon!";
        case "list":
            return taskList.executeList();
        case "done":
            return taskList.executeDone(tokenizedInput);
        case "delete":
            return taskList.executeDelete(tokenizedInput);
        case "todo":
            return taskList.executeTodo(tokenizedInput);
        case "deadline":
            return taskList.executeDeadline(tokenizedInput);
        case "event":
            return taskList.executeEvent(tokenizedInput);
        case "find":
            return taskList.executeFind(tokenizedInput);
        default:
            throw new DukeInvalidInputException(input);
        }
    }

    private static PrintStream getWriter(OutputStream out) {
        return new PrintStream(out);
    }

    private static BufferedReader getReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }

    private static String readLine(InputStream stream) throws IOException {
        char c;
        String s = "";
        do {
            c = (char) stream.read();
            if (c == '\n')
                break;
            s += c + "";
        } while (c != -1);
        return s;
    }
}
