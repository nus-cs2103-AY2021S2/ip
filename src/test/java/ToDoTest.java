import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.TaskParseException;
import duke.task.ToDo;

public class ToDoTest {

    @Test
    public void toDo_deserializeBadlySerializedToDo_throwsTaskParseException() {
        String description = "a random description";
        boolean isDone = true;
        String badlySerializedToDo = new ToDo(description, isDone).serialize().substring(1);

        assertThrows(TaskParseException.class, () -> {
            ToDo.deserialize(badlySerializedToDo);
        });
    }

    @Test
    public void toDo_deserializeSerializedToDo_correctlyDeserialized() {
        String description = "something";
        boolean isDone = true;
        ToDo toDo = new ToDo(description, isDone);

        assertDoesNotThrow(() -> {
            ToDo deserialized = ToDo.deserialize(toDo.serialize());
            assertEquals(deserialized.getDescription(), toDo.getDescription());
            assertEquals(deserialized.getType(), toDo.getType());
            assertEquals(deserialized.getStatusIcon(), toDo.getStatusIcon());
        });
    }

    @Test
    public void toDo_serializedToDo_recognizedAsToDo() {
        String description = "something else";
        boolean isDone = false;
        String toDo = new ToDo(description, isDone).serialize();

        assertTrue(ToDo.isToDo(toDo));
    }
}
