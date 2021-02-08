package ekud.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TaskWithDateTimeTest {
    static final String DATE_TIME_TASK_DESCRIPTION = "task with datetime description";
    static final LocalDateTime DATE_TIME = LocalDateTime.of(2020, 2, 2, 2, 2);

    private static class TaskWithDateTimeImpl extends TaskWithDateTime {
        public TaskWithDateTimeImpl(String description, LocalDateTime dateTime) {
            super(description, dateTime);
        }
    }

    @Nested
    @DisplayName("freshly created")
    class FreshTask {
        private TaskWithDateTime taskWithDateTime;

        @BeforeEach
        void createNewTask() {
            taskWithDateTime = new TaskWithDateTimeImpl(DATE_TIME_TASK_DESCRIPTION, DATE_TIME);
        }

        @Test
        @DisplayName("return correct datetime")
        void checkIfDone() {
            assertEquals(DATE_TIME, taskWithDateTime.getDateTime());
        }

        @Test
        @DisplayName("export")
        void exportTest() {
            LinkedList<String> list = taskWithDateTime.export();
            assertEquals(DATE_TIME.toString(), list.getLast());
        }
    }
}
