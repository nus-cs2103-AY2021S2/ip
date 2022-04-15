package kobe;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.lang.Thread;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class KobeN{
    private Storage storage;
    private TaskList tasks; //ArrayList<Tasks> tasks
    private Ui ui;
    private static final String HOME = System.getProperty("user.home");

    public KobeN() {}

    /**
     * Initialises Kobe.
     */
    public KobeN(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath, tasks, ui);
    }

    /**
     * Runs Kobe, ready to accept commands typed into the command line, in sync with the GUI.
     */
    public void run() {
        //Scanner things
        Scanner sc = new Scanner(System.in);

        try {
            while (sc.hasNext()) {
                //Read the whole line, dissect each command word, including the condition after "/"
                String command = sc.nextLine();

                //---Parser---
                Parser.readInput(command, tasks, storage, ui);
            }
        } catch (CustomExceptions.IncompleteDecriptionException e) {
            System.out.println(e);
        } catch (CustomExceptions.IncorrectDecriptionException e) {
            System.out.println(e);
        }
        sc.close();
    }

    /**
     * The user input is a command that is processed, and the corresponding line that
     * Kobe is suppose to respond with is obtained in this method.
     *
     * @param input
     * @return  Kobe's response
     */
    public String getResponse(String input) {
        String kobesResponse = "";
        boolean isGoodbye = false;
        Parser.readInput(input, tasks, storage, ui);
        kobesResponse = Ui.getMostRecentResponse();
        return kobesResponse;
    }

    public String getGreeting() {
        String kobesResponse = "";
        kobesResponse = Ui.getMostRecentResponse();
        return kobesResponse;
    }

}

