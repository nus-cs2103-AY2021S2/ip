package duke.gui;

import duke.util.Task;
import javafx.scene.control.ListCell;

/**
 * Customized list cell for the listview.
 * Index for tasks and yellow color for high priority tasks.
 */
public class CustomListCell extends ListCell<Task> {

    @Override
    protected void updateItem(Task item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText("");
            setStyle("-fx-background-color:rgb(130, 130, 130);");
        } else {
            setText((getIndex() + 1) + ". " + item);
            setStyle(String.format("-fx-text-fill:%s;-fx-background-color:%s;",
                    item.isHighPriority() ? "yellow " : "white",
                    getIndex() % 2 == 0 ? "rgb(140, 140, 140)" : "rgb(130, 130, 130)"));
        }
    }
}
