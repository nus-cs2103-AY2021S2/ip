package duke;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.function.Consumer;

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

		functions = new Hashtable<>();

		functions.put("done", this::done);
		
		functions.put("todo", this::toDo);

		functions.put("deadline", this::deadline);

		functions.put("event", this::event);

		functions.put("delete", this::delete);

		functions.put("find", this::find);
    }

	public void parser(String s) {
		Scanner sc = new Scanner(s);
		String inputs = sc.next();
		this.initialize();
		if (inputs.equals("list")) {
			this.list();
		} else if (inputs.equals("taskson")) {
			this.tasksOnDay(sc.nextLine().stripLeading());
		} else if (inputs.equals("clear")) {
			this.clear();
		} else if (!this.checkValid(inputs)) {
			Ui.invalidInput();
		} else if (sc.hasNext()) {
			functions.get(inputs).accept(sc.nextLine().stripLeading());
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
		Task t = this.mem.get(i);
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

	void find(String s) {
		Ui.find(this.mem, s);
	}

	void clear() {
		this.mem.clear();
		Ui.clear();
	}

	boolean checkValid(String s) {
		return this.functions.containsKey(s);
	}
}
