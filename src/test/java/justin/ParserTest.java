package justin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class performs JUnit test on the Parser.java
 * class to ensure input and output are of expected values
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */

public class ParserTest {

    String command = "";

    @Test
    public void checkCommandTest() throws JustinException {

        String expectedOutput2 = "BYE";
        String expectedOutput = "TODO";

        Parser parserCheck1 = new Parser("todo test command");
        Parser parserCheck2 = new Parser("bye");

        assertEquals(expectedOutput, parserCheck1.checkCommand());
        assertEquals(expectedOutput2, parserCheck2.checkCommand());


    }

}
