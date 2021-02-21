import java.io.IOException;

import duke.TaskList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DukePieChartDialogBox extends HBox {
    @FXML
    private PieChart dialog;
    @FXML
    private ImageView displayPicture;

    private DukePieChartDialogBox(TaskList taskList, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DukePieChartDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.loadProperties(taskList, image);
    }

    public static DukePieChartDialogBox getDialogBox(TaskList taskList, Image img) {
        return new DukePieChartDialogBox(taskList, img);
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
        this.dialog.setData(getBreakdownData(taskList));
    }

    private ObservableList<PieChart.Data> getBreakdownData(TaskList taskList) {
        int todoCount = taskList.getTodoCount();
        int deadlineCount = taskList.getDeadlineCount();
        int eventCount = taskList.getEventCount();
        int total = todoCount + deadlineCount + eventCount;

        String todoText = String.format("To-do %.2f%%", Double.valueOf(todoCount) / total);
        String deadlineText = String.format("Deadline %.2f%%", Double.valueOf(deadlineCount) / total);
        String eventText = String.format("Event %.2f%%", Double.valueOf(eventCount) / total);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(todoText, todoCount),
                        new PieChart.Data(deadlineText, deadlineCount),
                        new PieChart.Data(eventText, eventCount));

        return pieChartData;
    }
}