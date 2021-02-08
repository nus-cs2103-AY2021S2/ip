package alice.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	/** Mapping from command to it's format **/
	public static final Map<String, Pattern> regexMap = new HashMap<>();

	/** Mapping from command to it's constructor **/
	public static final Map<String, Function<String[], Command>> functionMap = new HashMap<>();

	static {
		regexMap.put("todo", Pattern.compile("todo\\s+(.*)"));
		regexMap.put("deadline", Pattern.compile("deadline\\s+(.*)/by\\s+(.*)"));
		regexMap.put("event", Pattern.compile("event\\s+(.*)/at\\s+(.*)"));
		regexMap.put("done", Pattern.compile("done\\s+(\\d+)"));
		regexMap.put("delete", Pattern.compile("delete\\s+(\\d+)"));
		regexMap.put("list", Pattern.compile("^list$"));
		regexMap.put("bye", Pattern.compile("^bye$"));
		regexMap.put("undo", Pattern.compile("^undo$"));
		regexMap.put("find", Pattern.compile("^find\\s+(.*)"));

	}

	static {
		functionMap.put("todo", CommandAdd::new);
		functionMap.put("deadline", CommandAdd::new);
		functionMap.put("event", CommandAdd::new);
		functionMap.put("done", CommandDone::new);
		functionMap.put("delete", CommandDelete::new);
		functionMap.put("list", CommandList::new);
		functionMap.put("bye", CommandBye::new);
		functionMap.put("find", CommandFind::new);
		functionMap.put("undo", CommandUndo::new);
	}

	/**
	 * Returns a command given an input string.
	 * If command is identified, an EchoCommand is returned.
	 *
	 * @param input input given by the user.
	 * @return Command command of specific type
	 */
	public static Command parse(String input) {
		List<String> tmpList = new ArrayList<>();
		input = input.trim();
		String[] tokens = input.split("\\s+");
		Pattern regex = regexMap.get(tokens[0]);
		if (regex == null) {
			return new CommandEcho(new String[]{input});
		}
		Matcher matcher = regex.matcher(input);
		if (matcher.find()) {
			for (int i = 1; i <= matcher.groupCount(); i++) {
				tmpList.add(matcher.group(i).trim());
			}
		}
		tmpList.add(0, tokens[0]);
		assert functionMap.containsKey(tokens[0]);
		return functionMap.get(tokens[0]).apply(tmpList.toArray(new String[]{}));
	}
}
