package duke.ui;

import duke.commands.CommandResult;

public interface Ui {
    String readCommand();

    void showCommandResult(CommandResult result);

    void showError(String errMsg);

    void showFarewell();

    void showGreeting();

    void showMessage(String msg);
}
