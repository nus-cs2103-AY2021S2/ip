package duke.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class SampleDataTest {
    @Test
    public void loadSampleDataTest() {
        assertDoesNotThrow(() -> SampleData.loadSampleData());
    }
}
