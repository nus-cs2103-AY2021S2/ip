import alice.command.CommandAdd;
import alice.command.CommandEcho;
import alice.command.CommandList;
import alice.command.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ParseTest {
	@Test
	public void parseTodo_correct_success() {
		assertEquals(new CommandAdd(new String[]{"todo", "a"}), Parser.parse("todo a"));
	}

	@Test
	public void parseDeadline_correct_success() {
		assertEquals(
				new CommandAdd(new String[]{"deadline", "a", "2020/1/2"}),
				Parser.parse("deadline a /by 2020/1/2")
		);
	}

	@Test
	public void parseEvent_correct_success() {
		assertEquals(
				new CommandAdd(new String[]{"event", "a", "2020/1/2"}),
				Parser.parse("event a /at 2020/1/2")
		);
	}

	@Test
	public void parseList_correct_success() {
		assertEquals(
				new CommandList(new String[]{"list"}),
				Parser.parse("list"));
	}

	@Test
	public void parseList_listExcess_failure() {
		assertNotEquals(
				new CommandList(new String[]{"list"}),
				Parser.parse("list abc def ghi"));
	}

	@Test
	public void parse_list_success() {
		assertEquals(
				new CommandList(new String[]{"list"}),
				Parser.parse("list")
		);
	}

	@Test
	public void parseEvent_listExcess_success() {
		String input = "list abc def ghi";
		assertEquals(
				new CommandEcho(new String[]{input}),
				Parser.parse(input)
		);
	}

	@Test
	public void parseEvent_excessWhitespace_whitespaceRemoved() {
		assertEquals(
				new CommandAdd(new String[]{"event", "a", "2020/1/2"}),
				Parser.parse("event  a     /at  2020/1/2")
		);
	}

	@Test
	public void parseInvalid_invalidCommand_echoReturned() {
		String input = "command abc";
		assertEquals(
				new CommandEcho(new String[]{input}),
				Parser.parse(input)
		);
	}
}
