package duke;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import static javafx.scene.paint.Paint.valueOf;

public class Duke extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane dialogWindow = new BorderPane();
        dialogWindow.setBackground(new Background(new BackgroundFill(valueOf("black"), null, null)));
        //Color.BLACK

        Group root = new Group();

        Scene scene = new Scene(dialogWindow ,500, 500);

        //setting color to the scene
        scene.setFill(Color.BLACK);

        //Setting the title to Stage.
        stage.setTitle("DUKE");

        final TextField userText = new TextField();
        userText.setBackground(new Background(new BackgroundFill(valueOf("white"), null, null)));
        dialogWindow.setBottom(userText); //attach the text field to the scene.

        Label display = new Label(Ui.welcome());
        dialogWindow.setCenter(display);
        dialogWindow.setAlignment(display, Pos.BOTTOM_CENTER);

        Parser parser = new Parser();

        // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                String command = userText.getText();
                display.setText(parser.parser(command));
                userText.clear();
                if (command.equals("bye")) {
                    Platform.exit();
                }
            }
        };

        userText.setOnAction(event);

        //Adding the scene to Stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();


/*
        DialogPane dialog = new DialogPane();
        dialog.setContentText("testing\ntest\n123");

        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(dialog, 500, 500); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

 */
    }

/*
    public static void main(String[] args) {
        Parser p = new Parser();

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while(!input.equals("bye")) {
        	p.parser(input);
        	input = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________");
		System.out.println();
		System.out.println("      Bye. Hope to see you again soon!");
		System.out.println("    ____________________________________________________________");
		System.out.println();
    }
 */
}
