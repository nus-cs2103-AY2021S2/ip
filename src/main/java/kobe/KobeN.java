package kobe;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class KobeN extends Application {
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
     * Main method. Allows Kobe to run.
     */
    public static void main(String[] args) {
        Path path = Paths.get(HOME + "/ip/src/main/data/kobe.txt");
        String pathName = HOME + "/ip/src/main/data/kobe.txt";
        new KobeN(pathName).run();
    }

    /**
     * Runs Kobe, ready to accept commands typed into the command line.
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

    @Override
    public void start (Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();

    }

}