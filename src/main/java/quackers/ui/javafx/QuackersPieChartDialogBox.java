package quackers.ui.javafx;

import java.io.IOException;

import quackers.task.Deadline;
import quackers.task.Event;
import quackers.task.Todo;
import quackers.tasklist.TaskList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class QuackersPieChartDialogBox extends HBox {
    @FXML
    private PieChart dialog;
    @FXML
    private ImageView displayPicture;

    private QuackersPieChartDialogBox(TaskList taskList, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/QuackersPieChartDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.loadProperties(taskList, image);
    }

    public static QuackersPieChartDialogBox getDialogBox(TaskList taskList, Image img) {
        return new QuackersPieChartDialogBox(taskList, img);
    }

    private void loadProperties(TaskList taskList, Image image) {
        this.setDisplayPicture(image);
        this.setTaskListBreakdown(taskList);
    }

    private void setDisplayPicture(Image image) {
        this.displayPicture.setImage(image);
    }

    private void setTaskListBreakdown(TaskList taskList) {
        this.dialog.setTitle("Task List Breakdown");
        this.dialog.setData(this.getBreakdownData(taskList));
    }

    private ObservableList<PieChart.Data> getBreakdownData(TaskList taskList) {
        int todoCount = taskList.getTaskCount(Todo.class);
        int deadlineCount = taskList.getTaskCount(Deadline.class);
        int eventCount = taskList.getTaskCount(Event.class);
        int total = todoCount + deadlineCount + eventCount;

        String todoText = String.format("To-do %.2f%%",
                Double.valueOf(todoCount) / total);
        String deadlineText = String.format("Deadline %.2f%%",
                Double.valueOf(deadlineCount) / total);
        String eventText = String.format("Event %.2f%%",
                Double.valueOf(eventCount) / total);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(todoText, todoCount),
                        new PieChart.Data(deadlineText, deadlineCount),
                        new PieChart.Data(eventText, eventCount));
        return pieChartData;
    }
}
