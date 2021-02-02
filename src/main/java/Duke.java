import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Contains the main logic of the Duke class
     */
    public void run() {
        this.ui = new Ui();
        this.storage = new Storage();

        String filePath = this.ui.askFilePath();
        tasks = new TaskList(this.storage.load(filePath , ui));

        this.ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.excute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (DukeDeadlineException e) {
                ui.showError(e.getMessage());
            } catch (NullPointerException e) {
                ui.showError("OOPS!!! I`m sorry. but i don`t know what that means :-(");
            } finally {
                ui.showLine();
            }
        }
        this.storage.save(tasks.getTaskList());
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}