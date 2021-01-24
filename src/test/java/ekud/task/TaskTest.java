package ekud.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Abstract base class for all tasks")
public class TaskTest {
    public final String TASK_DESCRIPTION = "task description";

    private static class TaskImpl extends Task {
        public TaskImpl(String description) {
            super(description);
        }
    }

    @Nested
    @DisplayName("freshly created")
    class FreshTask {
        private Task task;

        @BeforeEach
        void createNewTask() {
            task = new TaskImpl(TASK_DESCRIPTION);
        }

        @Test
        @DisplayName("convert to string")
        void toStringTest() {
            assertEquals("[ ] " + TASK_DESCRIPTION, task.toString());
        }

        @Test
        @DisplayName("check if not done")
        void checkIfDone() {
            assertFalse(task.isDone());
        }

        @Test
        @DisplayName("export")
        void exportTest() {
            LinkedList<String> list = task.export();
            assertEquals("false", list.get(0));
            assertEquals(TASK_DESCRIPTION, list.get(1));
        }

        @Nested
        @DisplayName("after marking as done")
        class AfterMarkDone {
            @BeforeEach
            void markDone() {
                task.markAsDone();
            }

            @Test
            @DisplayName("convert to string")
            void toStringTest() {
                assertEquals("[X] " + TASK_DESCRIPTION, task.toString());
            }

            @Test
            @DisplayName("check if not done")
            void checkIfDone() {
                assertTrue(task.isDone());
            }

            @Test
            @DisplayName("export")
            void exportTest() {
                LinkedList<String> list = task.export();
                assertEquals("true", list.get(0));
                assertEquals(TASK_DESCRIPTION, list.get(1));
            }
        }
    }
}
