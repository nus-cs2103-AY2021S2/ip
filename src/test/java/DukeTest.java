import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    @Test
    public void parseTodoWorks(){
        String todo = "study ";
        String[] test = new String[]{"todo", "study"};
        Todo task = new Todo(todo,false);
        Todo task2 = Parser.parseTodo(test);
        assertEquals(task.toString(),task2.toString(),"Wrong format");
    }
    @Test
    public void taskListSizeIsCorrect(){
        ArrayList<Task> test = new ArrayList<>();
        test.add(new Todo("task 1",false));
        test.add(new Todo("task 2",false));
        test.add(new Todo("task 3",true));
        TaskList list = new TaskList(test);
        assertEquals(3,list.size(),"Wrong Answer");
    }
    @Test
    public void taskListGetIsCorrect(){
        ArrayList<Task> test = new ArrayList<>();
        test.add(new Todo("task 1",false));
        test.add(new Todo("task 2",false));
        test.add(new Todo("task 3",true));
        TaskList list = new TaskList(test);
        assertEquals("[T][X] task 3",list.get(2).toString(),"Wrong Answer");
    }
}
