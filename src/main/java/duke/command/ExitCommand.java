package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class ExitCommand extends Command{
    public ExitCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
        return ui.showBye();
    }
}
