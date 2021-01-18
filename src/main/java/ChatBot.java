import java.util.ArrayList;

class ChatBot {

	private final ArrayList<String> mem;

	ChatBot() {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Hello! I'm Duke");
		System.out.println("      What can I do for you?");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		this.mem = new ArrayList<String>();
	}

	void parser(String s) {
		if (s.equals("list")) {
			this.list();
		} else {
			this.store(s);
		}
	}

	void store(String s) {
		this.mem.add(s);
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      added:  " + s);
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	void list() {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		for (int i = 1; i <= this.mem.size(); i += 1) {
			System.out.println("      " + i + ".  " + this.mem.get(i - 1));
		}
		
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}
}