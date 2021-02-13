

import java.io.*;
import java.util.HashMap;

import exception.DukeException;
import exception.DukeInvalidInputException;
import gui.MainWindow;
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
     */
    public static void main(String[] args) {

        UI.greet(System.out);
        try {
            storage.loadHistory();
        } catch (IOException e) {
            e.printStackTrace();
        }

        chatLoop(System.in, System.out);

    }

    MainWindow window = new MainWindow();
    Thread t;

    @Override
    public void start(Stage primaryStage) {

        window.createChatWindow();
        primaryStage.setScene(window.getScene());

        UI.greet(window.getOutputStream());

        try {
            storage.loadHistory();
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.show();

        t = new Thread(() -> {
            chatLoop(window.getInputStream(), window.getOutputStream());
        });
        t.start();
    }

    @Override
    public void stop() {
        t.stop();
    }

    protected static void chatLoop(InputStream in, OutputStream out) {

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

    private static String readLine(InputStream stream) {
        char c = 0;
        String s = "";
        do {
            try {
                int temp = stream.read();
                if (temp == -1) {
                    if (s == "") {
                        return null;
                    }
                    return s;
                }
                c = (char) temp;
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            if (c == '\n')
                break;
            s += c + "";
        } while (c != -1);
        return s;
    }
}
