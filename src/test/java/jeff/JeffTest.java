package jeff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JeffTest {
    @Test
    public void getResponseTest() {
        Jeff jeff = new Jeff("data.txt");
        assertEquals("Bye. Hope to see you again!", jeff.getResponse("bye"));
    }
}
