import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.duke.DukeException;
import main.duke.command.CBye;
import main.duke.command.CDeadline;
import main.duke.command.CDelete;
import main.duke.command.CDone;
import main.duke.command.CFind;
import main.duke.command.ICommand;
import main.duke.io.Parser;

public class ParserTest {
    private static final Parser parser = new Parser();
    @Test
    public void parseByeTest(){
        ICommand bye = new CBye();
        try{
            ICommand parseBye = parser.parse("bye");
            assertEquals(bye.getClass(), parseBye.getClass());
        }
        catch(DukeException dukeException){
            System.out.println(dukeException.getMessage());
        }
    }

    @Test
    public void parseDeadlineTest(){
        try{
            ICommand parseDeadline = parser.parse("deadline sleep /by 1111-11-11 1111");
            assertEquals(parseDeadline.getClass(), CDeadline.class);
        }
        catch(DukeException dukeException){
            System.out.println(dukeException.getMessage());
        }

    }

    @Test
    public void parseDeleteTest(){
        try{
            ICommand parseDelete = parser.parse("delete 1");
            assertEquals(parseDelete.getClass(), CDelete.class);
        }
        catch (DukeException dukeException){
            System.out.println(dukeException.getMessage());
        }
    }

    @Test
    public void parseFindTest(){
        try{
            ICommand parseFind = parser.parse("find sleep");
            assertEquals(parseFind.getClass(), CFind.class);
        }
        catch (DukeException dukeException){
            System.out.println(dukeException.getMessage());
        }
    }

    @Test
    public void parseDoneTest(){
        try{
            ICommand parseDone = parser.parse("done 1");
            assertEquals(parseDone.getClass(), CDone.class);
        }
        catch (DukeException dukeException){
            System.out.println(dukeException.getMessage());
        }
    }
}
