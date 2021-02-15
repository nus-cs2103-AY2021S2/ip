package logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import snom.logic.Parser;

public class ParserTest {
    private String userInput = "deadline return book /by 2021-09-27 15:00";

    @Test
    public void getCommandStr() {
        String commandStr = Parser.parseCommandStr(userInput);
        assertEquals(commandStr, "deadline");
    }

    @Test
    public void getCommandContent() {
        String commandStr = Parser.parseCommandContent(userInput);
        assertEquals(commandStr, "return book /by 2021-09-27 15:00");
    }
}
