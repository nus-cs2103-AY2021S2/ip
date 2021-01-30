package duke.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    
    @Test
    public void parseInputTest() {
        assertThrows(DukeInputException.class, () -> Parser.parseInput(""));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("a"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("a "));

        assertThrows(DukeInputException.class, () -> Parser.parseInput("todo"));

        assertThrows(DukeInputException.class, () -> Parser.parseInput("deadline"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("deadline /by "));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("deadline /by t /by a"));

        assertThrows(DukeInputException.class, () -> Parser.parseInput("event"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("event /at "));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("event /at a /at b"));

        assertThrows(DukeInputException.class, () -> Parser.parseInput("done"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("done a"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("done 1a2"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("done     1"));
        
        assertThrows(DukeInputException.class, () -> Parser.parseInput("delete"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("delete a"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("delete 1a2"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("delete    1"));
    }

    @Test
    public void checkImportFormatTest() {
        assertThrows(DukeInputException.class, () -> Parser.checkImportFormat("Z;1;a;2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.checkImportFormat("D;3;;2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.checkImportFormat("D;1;a;201-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.checkImportFormat("D;1;a;2011-01-01;"));
        assertThrows(DukeInputException.class, () -> Parser.checkImportFormat("D;1;a;2011-01-01;b"));
        assertThrows(DukeInputException.class, () -> Parser.checkImportFormat("D;1;a;"));
        assertThrows(DukeInputException.class, () -> Parser.checkImportFormat("D;1;a;;2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.checkImportFormat(";D;1;a;;2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.checkImportFormat("a"));
        assertThrows(DukeInputException.class, () -> Parser.checkImportFormat(""));
    }
}
