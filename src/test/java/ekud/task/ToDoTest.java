package ekud.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ToDoTest {

    public static final String TODO_DESCRIPTION = "todo description";

    @Nested
    @DisplayName("freshly created")
    class FreshTask {
        private ToDo toDo;

        @BeforeEach
        void createNewTask() {
            toDo = new ToDo(TODO_DESCRIPTION);
        }

        @Test
        @DisplayName("convert to string")
        void toStringTest() {
            assertEquals("[T][\u2718] " + TODO_DESCRIPTION, toDo.toString());
        }

        @Test
        @DisplayName("export")
        void exportTest() {
            LinkedList<String> list = toDo.export();
            assertEquals("T", list.get(0));
        }

        @Nested
        @DisplayName("after marking as done")
        class AfterMarkDone {
            @BeforeEach
            void markDone() {
                toDo.markAsDone();
            }

            @Test
            @DisplayName("convert to string")
            void toStringTest() {
                assertEquals("[T][\u2713] " + TODO_DESCRIPTION, toDo.toString());
            }

            @Test
            @DisplayName("export")
            void exportTest() {
                LinkedList<String> list = toDo.export();
                assertEquals("T", list.get(0));
            }
        }
    }
}
