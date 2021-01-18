import java.util.ArrayList;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.function.Consumer;

class ChatBot {

	private final ArrayList<Task> mem;
	private static Hashtable<String, Consumer<String>> functions;

	ChatBot() {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Hello! I'm Duke");
		System.out.println("      What can I do for you?");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		this.mem = new ArrayList<Task>();
	}

	void initialize() {

		functions = new Hashtable<String, Consumer<String>>();

		functions.put("done", x -> this.done(x));
		
		functions.put("todo", x -> this.toDo(x));

		functions.put("deadline", x -> this.deadline(x));

		functions.put("event", x -> this.event(x));
    }

	void parser(String s) {
		Scanner sc = new Scanner(s);
		String inputs = sc.next();
		this.initialize();
		if (inputs.equals("list")) {
			this.list();
		} else if (!this.checkValid(inputs)) {
			System.out.println("    ____________________________________________________________");
			System.out.println("");
			System.out.println("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
			System.out.println("    ____________________________________________________________");
			System.out.println("");
		} else if (sc.hasNext()) {
			this.functions.get(inputs).accept(sc.nextLine().stripLeading());
		} else {
			System.out.println("    ____________________________________________________________");
			System.out.println("");
			System.out.println("      ☹ OOPS!!! The description of a " + inputs + " cannot be empty.");
			System.out.println("    ____________________________________________________________");
			System.out.println("");
		}
	}

	void store(String s) {
		this.mem.add(new Task(s));
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      added:  " + s);
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	void store(Task t) {
		this.mem.add(t);
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Got it. I've added this task: ");
		System.out.println("      " + t);
		System.out.println("      now you have " + this.mem.size() + " tasks in the list.");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	void list() {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Here are the tasks in your list:");
		for (int i = 1; i <= this.mem.size(); i += 1) {
			System.out.println("      " + i + ".  " + this.mem.get(i - 1));
		}
		
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	void done(String s) {
		int i = Integer.parseInt(s);
		this.mem.get(i - 1).finish();
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Nice! I've marked this task as done:");
		System.out.println("      " + this.mem.get(i - 1));
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	void toDo(String s) {
		if (s.equals("")) {
			System.out.println("    ____________________________________________________________");
			System.out.println("");
			System.out.println("      ☹ OOPS!!! The description of a todo cannot be empty.");
			System.out.println("    ____________________________________________________________");
			System.out.println("");
		} else {
			ToDo t = new ToDo(s);
			this.store(t);
		}
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

	boolean checkValid(String s) {
		return s.equals("todo") || s.equals("deadline") || s.equals("event") || s.equals("done");
	}
}
