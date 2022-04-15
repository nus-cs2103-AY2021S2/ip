import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import exception.DukeInvalidArgumentsException;

public class ParserTest {
    @Test
    public void parserAbleToParseDone() throws DukeInvalidArgumentsException {
        HashMap<String, String> tokenizedInput = Parser.parseInput("done 2");
        
        assertTrue(tokenizedInput.containsKey("command"));
        assertTrue(tokenizedInput.containsKey("info"));
        assertEquals("done", tokenizedInput.get("command"));
        assertEquals("2", tokenizedInput.get("info"));
    }
    @Test
    public void parserAbleToParseTodo() throws DukeInvalidArgumentsException {
        HashMap<String, String> tokenizedInput = Parser.parseInput("todo this is a todo");
        
        assertTrue(tokenizedInput.containsKey("command"));
        assertTrue(tokenizedInput.containsKey("info"));
        assertEquals("todo", tokenizedInput.get("command"));
        assertEquals("this is a todo", tokenizedInput.get("info"));
    }
    @Test
    public void parserAbleToParseDeadline() throws DukeInvalidArgumentsException {
        HashMap<String, String> tokenizedInput = Parser.parseInput("deadline this is a deadline /by 10/10/1010 1010");
        
        assertTrue(tokenizedInput.containsKey("command"));
        assertTrue(tokenizedInput.containsKey("info"));
        assertTrue(tokenizedInput.containsKey("by"));
        assertEquals("deadline", tokenizedInput.get("command"));
        assertEquals("this is a deadline", tokenizedInput.get("info"));
        assertEquals("10/10/1010 1010", tokenizedInput.get("by"));
    }
    @Test
    public void parserAbleToParseEvent() throws DukeInvalidArgumentsException {
        HashMap<String, String> tokenizedInput = Parser.parseInput("event this is a event /at 10/10/1010 1010");
        
        assertTrue(tokenizedInput.containsKey("command"));
        assertTrue(tokenizedInput.containsKey("info"));
        assertTrue(tokenizedInput.containsKey("at"));
        assertEquals("event", tokenizedInput.get("command"));
        assertEquals("this is a event", tokenizedInput.get("info"));
        assertEquals("10/10/1010 1010", tokenizedInput.get("at"));
    }
    @Test
    public void parserAbleToParseList() throws DukeInvalidArgumentsException {
        HashMap<String, String> tokenizedInput = Parser.parseInput("list");
        
        assertTrue(tokenizedInput.containsKey("command"));
        assertEquals("list", tokenizedInput.get("command"));
    }
    @Test
    public void parserAbleToParseDelete() throws DukeInvalidArgumentsException {
        HashMap<String, String> tokenizedInput = Parser.parseInput("delete 2");
        
        assertTrue(tokenizedInput.containsKey("command"));
        assertTrue(tokenizedInput.containsKey("info"));
        assertEquals("delete", tokenizedInput.get("command"));
        assertEquals("2", tokenizedInput.get("info"));
    }

}
