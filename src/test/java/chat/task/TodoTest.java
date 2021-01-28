package chat.task;

import chat.ChatException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    
    private static String formatStr = "Please input with this format:\n" + "todo [name]";
    
    @Test
    public void createTodo_wrongInstruction_chatException() {
        String errorMessage = "wrong instruction for todo\n" + formatStr;
        try {
            Todo.createTodo("deadline");
            fail(); // the test should not reach this line
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createTodo_onlyTodo_chatException() {
        String errorMessage = "todo name missing\n" + formatStr;
        try {
            Todo.createTodo("todo");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createTodo_todoOneSpace_chatException() {
        String errorMessage = "todo name missing\n" + formatStr;
        try {
            Todo.createTodo("todo");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createTodo_todoManySpaces_chatException() {
        String errorMessage = "todo name missing\n" + formatStr;
        try {
            Todo.createTodo("todo     ");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createTodo_noSpaceBetweenTodoAndDescription_chatException() {
        String errorMessage = "no spacing after todo\n" + formatStr;
        try {
            Todo.createTodo("todoread book");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createTodo_correctFormat_chatException() {
        try {
            Todo.createTodo("todo read book");
        } catch (ChatException e) {
            fail();
        }
    }
    
    @Test
    public void allParameterStr_todoObjNotDone_commaSeparatedString() {
        assertEquals("T,false,read book", 
                new Todo(false, "read book").allParameterStr());
    }

    @Test
    public void allParameterStr_todoObjDone_commaSeparatedString() {
        assertEquals("T,true,read book",
                new Todo(true, "read book").allParameterStr());
    }

    @Test
    public void toString_todoObjNotDone_string() {
        assertEquals("[T][ ] read book",
                new Todo(false, "read book").toString());
    }

    @Test
    public void toString_todoObjDone_string() {
        assertEquals("[T][X] read book",
                new Todo(true, "read book").toString());
    }
}
