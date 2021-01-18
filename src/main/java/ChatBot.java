import java.util.ArrayList;
import java.util.Scanner;

class ChatBot {

	private final ArrayList<Task> mem;

	ChatBot() {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Hello! I'm Duke");
		System.out.println("      What can I do for you?");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		this.mem = new ArrayList<Task>();
	}

	void parser(String s) {
		Scanner sc = new Scanner(s);
		String inputs = sc.next();
		if (inputs.equals("list")) {
			this.list();
		} else if (inputs.equals("done")) {
			this.done(sc.nextInt());
		} else if (inputs.equals("todo")) {
			this.toDo(sc.nextLine());
		} else if (inputs.equals("deadline")) {
			this.deadline(sc.nextLine());
		} else if (inputs.equals("event")) {
			this.event(sc.nextLine());
		} else {
			this.store(s);
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

	void done(int i) {
		this.mem.get(i - 1).finish();
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Nice! I've marked this task as done:");
		System.out.println("      " + this.mem.get(i - 1));
		System.out.println("    ____________________________________________________________");
		System.out.println("");
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
}
