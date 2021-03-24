import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.CoreMatchers.instanceOf;

public class ParserTest {

    @DisplayName("Test for list. ")
    @Test
    public void listTest() throws DukeException {
        ParserOutput out = Parser.parse("list");
        assertEquals(out.getAction(), 5);
    }

    @DisplayName("Test for add. ")
    @Test
    public void addTest() throws DukeException {
        ParserOutput out = Parser.parse("todo do something");
        assertEquals(out.getAction(), 3);
    }



    @DisplayName("Test for delete. ")
    @Test
    public void deleteTest() throws DukeException {
        ParserOutput out = Parser.parse("delete 1");
        assertEquals(out.getAction(), 1);
    }

    @DisplayName("Test for done. ")
    @Test
    public void doneTest() throws DukeException {
        ParserOutput out = Parser.parse("done 1");
        assertEquals(out.getAction(), 2);
    }

    @DisplayName("Test for bye. ")
    @Test
    public void byeTest() throws DukeException {
        ParserOutput out = Parser.parse("bye");
        assertEquals(out.isBye(), true);
    }
}