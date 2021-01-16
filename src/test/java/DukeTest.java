import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.*;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void parseInputEchoToOutput() {
        assertEquals("list", Duke.parseInput("list"));
        assertEquals("blah", Duke.parseInput("blah"));
    }

    @Test
    public void parseInputByeOutputFarewell() {
        assertEquals("Bye. Hope to see you again soon!", Duke.parseInput("bye"));
    }

    @Test
    public void chatInputByeExitsLoop() throws IOException {
        String testInput = "bye\n" + "hello\n" + "bye\n";

        InputStream ins = new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Duke.chatLoop(ins, out);

        assertEquals("Bye. Hope to see you again soon!\n", new String(out.toByteArray()));
    }

    @Test
    public void chatLoopWritesToOutput() throws IOException {
        String testInput = "echoThis\n" + "echoThisAsWell\n" + "echoThisFinally\n";

        InputStream ins = new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(ins));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Duke.chatLoop(ins, out);

        assertEquals(null, in.readLine());
        assertEquals(testInput, new String(out.toByteArray()));
    }
}