package duke;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.function.Consumer;

public class Parser {

	private final TaskList mem;
	private final Storage storage;
	private final Hashtable<String, Consumer<String>> functions;

	/**
	 * loads the txt file into mem and displays the welcome message
	 */
	public Parser() {		
		this.storage = new Storage();
		this.mem = this.storage.load();
		functions = new Hashtable<String, Consumer<String>>();
		this.initialize();
		Ui.welcome();
	}

	private void initialize() {

		functions.put("done", this::done);
		
		functions.put("todo", this::toDo);

		functions.put("deadline", this::deadline);

		functions.put("event", this::event);

		functions.put("delete", this::delete);

		functions.put("find", this::find);
    }

	/**
	 * reads the string and determines which function to call
	 * @param s user input
	 */
	public void parser(String s) {
		Scanner sc = new Scanner(s);
		String inputs = sc.next();
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

	private void store(Task t) {
		this.mem.store(t);
		Ui.store(t, this.mem.size());
	}

	private void list() {
		Ui.list(this.mem);
	}

	private void done(String s) {
		int i = Integer.parseInt(s);
		Task t = this.mem.get(i);
		t.finish();
		Ui.done(t);
	}

	private void toDo(String s) {
		ToDo t = new ToDo(s);
		this.store(t);
	}

	private void deadline(String s) {
		String[] inputs = s.split(" /by ");
		Deadline t = new Deadline(inputs[0], inputs[1]);
		this.store(t);
	}

	private void event(String s) {
		String[] inputs = s.split(" /at ");
		Event t = new Event(inputs[0], inputs[1]);
		this.store(t);
	}

	private void delete(String s) {
		int i = Integer.parseInt(s);
		Task t = this.mem.get(i);
		this.mem.delete(t);
		Ui.delete(t, this.mem.size());
	}

	private void tasksOnDay(String s) {
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
