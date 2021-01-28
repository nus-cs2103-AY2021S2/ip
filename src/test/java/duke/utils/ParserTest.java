package duke.utils;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
	@Test
	void extractInstruction() throws DukeException {
		String testInput = "todo swimming";
		String actualInstruction = Parser.extractInstruction(testInput);
		assertEquals("todo", actualInstruction);
	}

	@Test
	void extractInstruction_emptyInstruction_Exception() {
		try {
			String testInput = "";
			String actualInstruction = Parser.extractInstruction(testInput);
			fail();
		} catch (DukeException e) {
			assertEquals("Walao, command cannot be empty!", e.getMessage());
		}
	}

	




}
