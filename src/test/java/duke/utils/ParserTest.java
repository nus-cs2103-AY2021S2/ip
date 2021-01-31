package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;


public class ParserTest {


    @Test
    void extractInstructionTest() throws DukeException {
        String testInput = "todo swimming";
        String actualInstruction = Parser.extractInstruction(testInput);
        assertEquals("todo", actualInstruction);
    }

    @Test
    void extractInstruction_emptyInstruction_exception() {
        try {
            String testInput = "";
            Parser.extractInstruction(testInput);
            fail();
        } catch (DukeException e) {
            assertEquals("!!!Walao, command cannot be empty!!!", e.getMessage());
        }
    }

    @Test
    void extractInstruction_wrongInstruction_exception() {
        try {
            String testInput = "ANYHOW";
            Parser.extractInstruction(testInput);
            fail();
        } catch (DukeException e) {
            assertEquals("!!!!!!!I DON'T KNOW WHAT U SAYING BRO!!!!!!!", e.getMessage());
        }
    }


    @Test
    void extractTaskTest() throws DukeException {
        String testInput = "deadline project/2019-10-22";
        String actualTask = Parser.extractTask(testInput, "deadline");
        assertEquals("project", actualTask);
    }

    @Test
    void extractTask_emptyTask_exception() {
        try {
            String testInput = "deadline ";
            Parser.extractTask(testInput, "deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("!!!!!!!!!Walao!NO TASK!!!!!!!!!!", e.getMessage());
        }
    }

    @Test
    void extractDateTest() throws DukeException {
        String testInput = "deadline project /2019-10-22";
        String actualDate = Parser.extractDate(testInput, "deadline");
        assertEquals("Oct 22 2019", actualDate);
    }

    @Test
    void extractDate_withoutSlash_exception() {
        try {
            String testInput = "deadline project";
            Parser.extractDate(testInput, "deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("!!!Fill ur date lah (add date with / in yyyy-mm-dd format)!!!", e.getMessage());
        }

    }
    @Test
    void extractDate_wrongDateFormat_exception() {
        try {
            String testInput = "event project/2019";
            Parser.extractDate(testInput, "event");
            fail();
        } catch (DukeException e) {
            assertEquals("!!!Err, wrong date format.. (yyyy-mm-dd)!!!", e.getMessage());
        }
    }
}
