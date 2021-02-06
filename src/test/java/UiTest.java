import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class UiTest {

    @Test
    public void testResponseToList() {
         PrintStream out = System.out;
         ByteArrayOutputStream content = new ByteArrayOutputStream();
         System.setOut(new PrintStream(content));
         Ui ui = new Ui();
         ui.respondToList(0);
         assertEquals("Your list is currently empty! Let's start adding tasks!",
                 content.toString().trim());
         System.setOut(out);
    }

}
