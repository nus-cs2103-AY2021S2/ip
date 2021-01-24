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

class ChatBot {

	private final ArrayList<Task> mem;
	private final Storage storage;
	private static Hashtable<String, Consumer<String>> functions;

	ChatBot() {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Hello! I'm Duke");
		System.out.println("      What can I do for you?");
		System.out.println("    ____________________________________________________________");
		System.out.println("");

		this.mem = new ArrayList<Task>();
		this.storage = new Storage();

		try {
			BufferedReader br = Files.newBufferedReader(this.storage.load().toPath());
			String line;

			while ((line = br.readLine()) != null) {
				String[] info = line.split("1!1");

				System.out.println(line);
				System.out.println(info[0]);
				System.out.println(info[1]);
				System.out.println(info[2]);

				if (info[0].equals("todo")) {
					System.out.println(info[1]);
					this.mem.add(new ToDo(info[1], Boolean.parseBoolean(info[2])));
				} else if (info[0].equals("deadline")) {
					this.mem.add(new Deadline(info[1], info[2], Boolean.parseBoolean(info[3])));
				} else if (info[0].equals("event")) {
					this.mem.add(new Event(info[1], info[2], Boolean.parseBoolean(info[3])));
				}
			}

			System.out.println(this.mem);
		} catch (IOException e) {

		}
	}

	void initialize() {

		functions = new Hashtable<String, Consumer<String>>();

		functions.put("done", x -> this.done(x));
		
		functions.put("todo", x -> this.toDo(x));

		functions.put("deadline", x -> this.deadline(x));

		functions.put("event", x -> this.event(x));

		functions.put("delete", x -> this.delete(x));
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
		this.storage.save(this.mem);
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
		Task t = this.mem.get(i - 1);
		this.mem.remove(i - 1);
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Noted. I've removed this task:");
		System.out.println("      " + t);
		System.out.println("      now you have " + this.mem.size() + " tasks in the list.");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	boolean checkValid(String s) {
		return s.equals("todo") || s.equals("deadline") || s.equals("event") || s.equals("done") || s.equals("delete");
	}
/*
	void save() {
		try {
			Path location = Path.of(Duke.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			Path dir = location.resolve("data");
			Path file = dir.resolve("list.txt");

			Files.createDirectories(dir);

			boolean fileExists = java.nio.file.Files.exists(file);
			if (!fileExists) {
				Files.createFile(file);
			}
		
		ArrayList<String> list = new ArrayList<String>();

		for (Task t : this.mem) {
			list.add(t.saveName());
		}
		
		Files.write(file, list);

		} catch (URISyntaxException e) {

		} catch (IOException e) {

		}
		/*
		boolean directoryExists = java.nio.file.Files.exists(dir);

		if (directoryExists) {
			//dir.resolve("list.txt");
			boolean fileExists = java.nio.file.Files.exists(file);
		} else {

		}
		
	}
	*/
}
