package tlylt.haha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    void tokenize_normalCommand_stringArray() {
        assertTrue(Arrays.equals(new String[]{"todo", "work"}, Parser.tokenize("todo work")));
        assertTrue(Arrays.equals(new String[]{"done", "1"}, Parser.tokenize("done 1")));
        assertTrue(Arrays.equals(new String[]{"bye"}, Parser.tokenize("bye")));
        assertTrue(Arrays.equals(new String[]{"deadline", "homework /by 2020-01-02 18:00"},
                Parser.tokenize("deadline homework /by 2020-01-02 18:00")));
    }

    @Test
    void taskNumber_notANumber_exceptionThrown() {
        assertThrows(HahaTaskNumberNotIntException.class, () -> Parser.getTaskNumber("one"));
    }

    @Test
    void taskNumber_number_int() throws HahaTaskNumberNotIntException {
        assertEquals(2, Parser.getTaskNumber("done 2"));
    }
}

