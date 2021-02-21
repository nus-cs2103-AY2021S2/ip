import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a
 * pie chart containing the statistics of the user's task list.
 */
public class StatsBox extends HBox {
    @FXML
    private PieChart dialog;
    @FXML
    private ImageView displayPicture;

    private StatsBox(ArrayList<Task> tasks, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/StatsBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up pie chart
        int t = 0;
        int d = 0;
        int e = 0;
        for (Task task : tasks) {
            if (task instanceof Todo) {
                t += 1;
            } else if (task instanceof Deadline) {
                d += 1;
            } else if (task instanceof Event) {
                e += 1;
            }
        }

        // Calculate percentages
        int total = t + d + e;
        String todoText = String.format("To-do %.2f%%", Double.valueOf(t) / total);
        String deadlineText = String.format("Deadline %.2f%%", Double.valueOf(d) / total);
        String eventText = String.format("Event %.2f%%", Double.valueOf(e) / total);

        // Populate pie chart
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(todoText, t),
                        new PieChart.Data(deadlineText, d),
                        new PieChart.Data(eventText, e));

        // Update StatsBox
        dialog.setTitle("Task List Breakdown");
        dialog.setData(pieChartData);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static StatsBox getUserDialog(ArrayList<Task> tasks, Image img) {
        return new StatsBox(tasks, img);
    }

    public static StatsBox getDukeDialog(ArrayList<Task> tasks, Image img) {
        var db = new StatsBox(tasks, img);
        db.flip();
        return db;
    }
}