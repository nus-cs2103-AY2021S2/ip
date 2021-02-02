package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class CommandResultTest {
    @Test
    public void getMessageForUser_emptyMessage_success() {
        assertEquals("", new CommandResult("", true).getMessageForUser());
    }

    @Test
    public void getMessageForUser_nonEmptyMessage_success() {
        assertEquals("Working", new CommandResult("Working", true).getMessageForUser());
    }

    @Test
    public void getUpdatedTaskList_nullInput_nullReturned() {
        assertNull(new CommandResult("", true).getUpdatedTaskList());
    }

    @Test
    public void getUpdatedTaskList_nonEmptyTaskList_success() {
        TaskListStub taskListStub = new TaskListStub();
        assertEquals(taskListStub, new CommandResult("", taskListStub, true).getUpdatedTaskList());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("Message: completed", true);

        assertTrue(commandResult.equals(new CommandResult("Message: completed", true)));
        assertTrue(commandResult.equals(new CommandResult("Message: completed", true)));

        assertFalse(commandResult.equals(new CommandResult("Message: not completed", true)));
        assertFalse(commandResult.equals(new CommandResult("Message: completed",
                new TaskListStub(), true)));
        assertFalse(commandResult.equals(1));
    }

    class TaskListStub extends TaskList {
        private List<Task> taskList;

        public TaskListStub() {
            this.taskList = new ArrayList<>();
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj;
        }
    }
}
