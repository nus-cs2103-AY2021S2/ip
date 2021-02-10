package duke.Tag;

import static duke.tag.Tag.splitTag;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.tag.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TagTest {
    @Test
    public void splitTag_simpleInput_stringArrayReturned() {
        String[] result = splitTag("buy books #important #ohno");
        assertEquals("buy books ", result[0]);
        assertEquals("#important #ohno", result[1]);
    }

    @Test
    public void processTags_simpleInput_correctListReturned() {
        ArrayList<Tag> expectedResult = new ArrayList<>();
        expectedResult.add(new Tag("nice"));
        expectedResult.add(new Tag("sweet"));
        assertEquals(expectedResult.get(0).getTitle(), Tag.processTags("#nice #sweet").get(0).getTitle());
        assertEquals(expectedResult.get(1).getTitle(), Tag.processTags("#nice #sweet").get(1).getTitle());
    }
}
