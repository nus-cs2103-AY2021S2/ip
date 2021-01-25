package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.function.Consumer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.net.URISyntaxException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

	private final TaskList mem;
	private final Storage storage;
	private static Hashtable<String, Consumer<String>> functions;

	public Parser() {		
		this.storage = new Storage();
		this.mem = this.storage.load();
		Ui.welcome();
	}

	void initialize() {

		functions = new Hashtable<String, Consumer<String>>();

		functions.put("done", x -> this.done(x));
		
		functions.put("todo", x -> this.toDo(x));

		functions.put("deadline", x -> this.deadline(x));

		functions.put("event", x -> this.event(x));

		functions.put("delete", x -> this.delete(x));
    }

	public void parser(String s) {
		Scanner sc = new Scanner(s);
		String inputs = sc.next();
		this.initialize();
		if (inputs.equals("list")) {
			this.list();
		} else if (inputs.equals("taskson")) {
			this.tasksOnDay(sc.nextLine().stripLeading());
		} else if (!this.checkValid(inputs)) {
			Ui.invalidInput();
		} else if (sc.hasNext()) {
			this.functions.get(inputs).accept(sc.nextLine().stripLeading());
		} else {
			Ui.emptyDescription(inputs);
		}
		this.storage.save(this.mem.get());
	}

	void store(Task t) {
		this.mem.store(t);
		Ui.store(t, this.mem.size());
	}

	void list() {
		Ui.list(this.mem);
	}

	void done(String s) {
		int i = Integer.parseInt(s);
		Task t = this.mem.get(i - 1);
		t.finish();
		Ui.done(t);
	}

	void toDo(String s) {
		ToDo t = new ToDo(s);
		this.store(t);
	}

	void deadline(String s) {
		String[] inputs = s.split(" /by ");
		Deadline t = new Deadline(inputs[0], inputs[1]);
		this.store(t);
	}

	void event(String s) {
		String[] inputs = s.split(" /at ");
		Event t = new Event(inputs[0], inputs[1]);
		this.store(t);
	}

	void delete(String s) {
		int i = Integer.parseInt(s);
		Task t = this.mem.get(i);
		this.mem.delete(t);
		Ui.delete(t, this.mem.size());
	}

	void tasksOnDay(String s) {
		Ui.tasksOnDay(this.mem, s);
	}

	boolean checkValid(String s) {
		return s.equals("todo") || s.equals("deadline") || s.equals("event") || s.equals("done") || s.equals("delete");
	}
}
