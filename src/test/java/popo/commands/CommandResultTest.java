package popo.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import popo.tasks.Task;
import popo.tasks.TaskList;

public class CommandResultTest {
    @Test
    public void getMessageForUser_emptyMessage_success() {
        assertEquals("", new CommandResult(false).getMessageForUser());
    }

    @Test
    public void getMessageForUser_nonEmptyMessage_success() {
        assertEquals("Good", new CommandResult(false, "Good").getMessageForUser());
    }

    @Test
    public void getUpdatedTaskList_nullTaskList_nullReturned() {
        assertNull(new CommandResult(false).getUpdatedTaskList());
    }

    @Test
    public void getUpdatedTaskList_emptyTaskList_success() {
        TaskListStub taskListStub = new TaskListStub();
        assertEquals(taskListStub, new CommandResult(taskListStub, false).getUpdatedTaskList());
    }

    @Test
    public void isExitingProgram_exiting_true() {
        assertTrue(new CommandResult(true).isExitingProgram());
    }

    @Test
    public void isExitingProgram_notExiting_false() {
        assertFalse(new CommandResult(false).isExitingProgram());
    }

    private static class TaskListStub extends TaskList {
        private final List<Task> taskList;

        public TaskListStub() {
            taskList = new ArrayList<>();
        }
    }
}
