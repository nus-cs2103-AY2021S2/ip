package task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.ChecklstException;

public class TodoTest {

    @Test
    public void createTodoTest() {
        assertThrows(ChecklstException.class, () -> Todo.makeTodo(""));
    }

}