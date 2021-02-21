package yoda.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import yoda.task.InvalidTaskListIndexException;
import yoda.task.TaskList;
import yoda.task.ToDo;


class EditCommandTest {
    private TaskList tasks = new TaskList(new ArrayList<>());
    @Test
    void testAccessTasksToBeEdited() throws InvalidTaskListIndexException {
        ToDo rest = new ToDo("have a break");
        ToDo coffee = new ToDo("drink coffee");
        ToDo corrections = new ToDo("make corrections");
        tasks.addTask(rest);
        tasks.addTask(coffee);
        tasks.addTask(corrections);
        String[] editCommandArgs = {"DELETE", "1", "2"};
        EditCommand editCommand = new EditCommand(editCommandArgs);
        TaskList editedTasks = editCommand.accessTasksToBeEdited(tasks);
        assertEquals(2, editedTasks.getTaskListSize());
        assertEquals(3, tasks.getTaskListSize());
    }

    @Test
    void testDeleteTasks() {
        ToDo rest = new ToDo("have a break");
        ToDo coffee = new ToDo("drink coffee");
        ToDo corrections = new ToDo("make corrections");
        tasks.addTask(rest);
        tasks.addTask(coffee);
        tasks.addTask(corrections);
        String[] editCommandArgs = {"DELETE", "1", "2"};
        EditCommand editCommand = new EditCommand(editCommandArgs);
        editCommand.deleteTasks(tasks);
        assertEquals(1, tasks.getTaskListSize());
    }

    @Test
    void testMarkTasksAsDone() {
        ToDo rest = new ToDo("have a break");
        ToDo coffee = new ToDo("drink coffee");
        ToDo corrections = new ToDo("make corrections");
        tasks.addTask(rest);
        tasks.addTask(coffee);
        tasks.addTask(corrections);
        String[] editCommandArgs = {"DONE", "1", "2"};
        EditCommand editCommand = new EditCommand(editCommandArgs);
        editCommand.markTasksAsDone(tasks);
        assertEquals(1, tasks.getNumberOfUnfinishedTasks());
    }
}
