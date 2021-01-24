package ekud.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    public static final String DEADLINE_DESCRIPTION = "deadline description";
    static final LocalDateTime DATE_TIME = LocalDateTime.of(2020, 2, 2, 2, 2);
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy h.mma");

    @Nested
    @DisplayName("freshly created")
    class FreshTask {
        private Deadline deadline;

        @BeforeEach
        void createNewTask() {
            deadline = new Deadline(DEADLINE_DESCRIPTION, DATE_TIME);
        }

        @Test
        @DisplayName("convert to string")
        void toStringTest() {
            assertEquals(
                    "[D][ ] " + DEADLINE_DESCRIPTION + " (by: " + DATE_TIME.format(FORMATTER) + ")",
                    deadline.toString());
        }

        @Test
        @DisplayName("export")
        void exportTest() {
            LinkedList<String> list = deadline.export();
            assertEquals("D", list.get(0));
        }

        @Nested
        @DisplayName("after marking as done")
        class AfterMarkDone {
            @BeforeEach
            void markDone() {
                deadline.markAsDone();
            }

            @Test
            @DisplayName("convert to string")
            void toStringTest() {
                assertEquals(
                        "[D][X] " + DEADLINE_DESCRIPTION + " (by: " + DATE_TIME.format(FORMATTER) + ")",
                        deadline.toString());
            }

            @Test
            @DisplayName("export")
            void exportTest() {
                LinkedList<String> list = deadline.export();
                assertEquals("D", list.getFirst());
            }
        }
    }
}
