package duke.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testGenerateFileFormatString() {
        assertEquals("D // 0 // project1 // 2019-12-01",
                new Deadline(false, "project1", LocalDate.parse("2019-12-01")).generateFileFormatString());
    }
}
