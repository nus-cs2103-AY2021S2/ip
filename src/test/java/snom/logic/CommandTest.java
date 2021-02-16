package snom.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import snom.common.exceptions.SnomException;
import snom.logic.commands.AddCommand;
import snom.logic.commands.CommandEnum;
import snom.logic.commands.DeleteCommand;
import snom.logic.commands.FinishCommand;
import snom.model.task.TaskList;
import snom.storage.StorageManager;
import snom.ui.Snomio;

public class CommandTest {
    private TaskList taskList = new TaskList();
    private Snomio snomio = new Snomio();
    private StorageManager storage = new StorageManager(Paths.get("data", "snom_test.txt"));

    @Test
    public void addFinishDeleteCommand() {
        try {
            // Add command
            String content = "read book";
            AddCommand addCommand = new AddCommand(CommandEnum.TODO, content);
            addCommand.execute(taskList, snomio, storage);
            assertEquals(taskList.get(0).getDescription(), content);

            // Finish Command
            content = "1";
            FinishCommand finishCommand = new FinishCommand(CommandEnum.FINISH, content);
            finishCommand.execute(taskList, snomio, storage);
            assertEquals(taskList.get(0).hasFinished(), true);

            // Delete Command
            DeleteCommand deleteCommand = new DeleteCommand(CommandEnum.DELETE, content);
            deleteCommand.execute(taskList, snomio, storage);
            assertEquals(taskList.size(), 0);

        } catch (SnomException e) {
            assert false;
        }
    }
}
