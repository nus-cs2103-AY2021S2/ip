package ekud.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class EventTaskTest {
    public static final String EVENT_DESCRIPTION = "event description";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy h.mma");
    static final LocalDateTime DATE_TIME = LocalDateTime.of(2020, 2, 2, 2, 2);

    @Nested
    @DisplayName("freshly created")
    class FreshTask {
        private EventTask eventTask;

        @BeforeEach
        void createNewTask() {
            eventTask = new EventTask(EVENT_DESCRIPTION, DATE_TIME);
        }

        @Test
        @DisplayName("convert to string")
        void toStringTest() {
            assertEquals(
                    "[E][\u2718] " + EVENT_DESCRIPTION + " (at: " + DATE_TIME.format(FORMATTER) + ")",
                    eventTask.toString());
        }

        @Test
        @DisplayName("export")
        void exportTest() {
            LinkedList<String> list = eventTask.export();
            assertEquals("E", list.get(0));
        }

        @Nested
        @DisplayName("after marking as done")
        class AfterMarkDone {
            @BeforeEach
            void markDone() {
                eventTask.markAsDone();
            }

            @Test
            @DisplayName("convert to string")
            void toStringTest() {
                assertEquals(
                        "[E][\u2713] " + EVENT_DESCRIPTION + " (at: " + DATE_TIME.format(FORMATTER) + ")",
                        eventTask.toString());
            }

            @Test
            @DisplayName("export")
            void exportTest() {
                LinkedList<String> list = eventTask.export();
                assertEquals("E", list.getFirst());
            }
        }
    }
}
