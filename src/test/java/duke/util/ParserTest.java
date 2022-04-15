package duke.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseInputTest() {
        assertDoesNotThrow(() -> Parser.parseInput("bye"));
        assertDoesNotThrow(() -> Parser.parseInput("list"));
        assertDoesNotThrow(() -> Parser.parseInput("done 1"));
        assertDoesNotThrow(() -> Parser.parseInput("done 1 2"));
        assertDoesNotThrow(() -> Parser.parseInput("done 1 2 3"));
        assertDoesNotThrow(() -> Parser.parseInput("todo a"));
        assertDoesNotThrow(() -> Parser.parseInput("deadline a /by 2011-01-01"));
        assertDoesNotThrow(() -> Parser.parseInput("event a /at 2011-01-01"));
        assertDoesNotThrow(() -> Parser.parseInput("delete 1"));
        assertDoesNotThrow(() -> Parser.parseInput("delete 1 2"));
        assertDoesNotThrow(() -> Parser.parseInput("delete 1 2 3"));
        assertDoesNotThrow(() -> Parser.parseInput("save"));
        assertDoesNotThrow(() -> Parser.parseInput("load"));
        assertDoesNotThrow(() -> Parser.parseInput("help"));
        assertDoesNotThrow(() -> Parser.parseInput("search a"));
        assertDoesNotThrow(() -> Parser.parseInput("sort"));

        assertThrows(DukeInputException.class, () -> Parser.parseInput(""));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("a"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("a "));

        assertThrows(DukeInputException.class, () -> Parser.parseInput("todo"));

        assertThrows(DukeInputException.class, () -> Parser.parseInput("deadline"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("deadline /by "));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("deadline /by 1"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("deadline 2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("deadline/by2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("deadline /by t /by a"));

        assertThrows(DukeInputException.class, () -> Parser.parseInput("event"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("event /at "));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("event /at 1"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("event 2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("event/at2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("event /at a /at b"));

        assertThrows(DukeInputException.class, () -> Parser.parseInput("done"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("done 1 1"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("done 1  2"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("done 1 2  3"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("done a"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("done 1a2"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("done     1"));

        assertThrows(DukeInputException.class, () -> Parser.parseInput("delete"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("delete 1 1"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("delete 1  2"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("delete 1 2  3"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("delete a"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("delete 1a2"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("delete    1"));

        assertThrows(DukeInputException.class, () -> Parser.parseInput("search"));

        assertThrows(DukeInputException.class, () -> Parser.parseInput("bye a"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("list a"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("save a"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("load a"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("help a"));
        assertThrows(DukeInputException.class, () -> Parser.parseInput("sort a"));
    }

    @Test
    public void parseImportTest() {
        assertDoesNotThrow(() -> Parser.parseImport("T;1;0;a"));
        assertDoesNotThrow(() -> Parser.parseImport("D;1;0;a;2011-01-01"));
        assertDoesNotThrow(() -> Parser.parseImport("E;0;0;a;2011-01-01"));

        assertThrows(DukeInputException.class, () -> Parser.parseImport("Z;1;0;a;2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.parseImport("D;3;0;;2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.parseImport("D;1;0;a;201-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.parseImport("D;1;0;a;2011-01-01;"));
        assertThrows(DukeInputException.class, () -> Parser.parseImport("D;1;0;a;2011-01-01;b"));
        assertThrows(DukeInputException.class, () -> Parser.parseImport("D;1;0;a;"));
        assertThrows(DukeInputException.class, () -> Parser.parseImport("D;1;0;a;;2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.parseImport(";D;1;0;a;;2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.parseImport("D;1;2;a;2011-01-01"));
        assertThrows(DukeInputException.class, () -> Parser.parseImport("a"));
        assertThrows(DukeInputException.class, () -> Parser.parseImport(""));
    }

    @Test
    public void parseYesNoTest() {
        assertDoesNotThrow(() -> Parser.parseYesNo("y"));
        assertDoesNotThrow(() -> Parser.parseYesNo("n"));

        assertThrows(DukeInputException.class, () -> Parser.parseYesNo(""));
        assertThrows(DukeInputException.class, () -> Parser.parseYesNo("a"));
    }
}
