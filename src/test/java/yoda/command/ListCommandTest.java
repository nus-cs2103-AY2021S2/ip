package yoda.command;

import org.junit.jupiter.api.Test;
import yoda.task.TaskList;
import yoda.task.ToDo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCommandTest {
    TaskList tasks = new TaskList(new ArrayList<>());

    @Test
    void testFindByKeyword() throws InvalidKeywordException {
        ToDo rest = new ToDo("have a break");
        ToDo coffee = new ToDo("drink coffee");
        ToDo corrections = new ToDo("make corrections");
        tasks.addTask(rest);
        tasks.addTask(coffee);
        tasks.addTask(corrections);
        String[] listCommandArgs = {"FIND", "coffee"};
        ListCommand listCommand = new ListCommand(listCommandArgs);
        TaskList filteredList = listCommand.findByKeyword("coffee", tasks);
        assertEquals(1, filteredList.getTaskListSize());
        assertThrows(InvalidKeywordException.class, () -> listCommand.findByKeyword("a", tasks));
    }
}