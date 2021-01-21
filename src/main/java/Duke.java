import java.util.ArrayList;
import java.util.Scanner;
import java.lang.ArrayIndexOutOfBoundsException;

public class Duke {

	private ArrayList<Task> store = new ArrayList<>();

	public void addTask(Task t) {
		this.store.add(t);
	}

	public void deleteTask(int n) {
		this.store.remove(n -1);
	}

	public void printTask(String input) throws DukeException {
		if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
       		if (input.split(" ").length == 1) {
       			throw new EmptyException();
       		}
       	}
		if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
        	int counter = 1;
        	for (Task elem: this.store) {
       			System.out.println(counter + ". " + elem.toString());
      			counter += 1;
       		}
       	} else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
			Task t;
			if (input.contains("todo")) {
				t = new Todo(input.split("todo ")[1]);
			} else if (input.contains("deadline")) {
				String trimmed = input.replaceAll("deadline ", "");
				t = new Deadline(trimmed.split(" by ")[0], trimmed.split(" by ")[1]);
			} else /*(if (input.contains("event"))*/ {
				String trimmed = input.replaceAll("event ", "");
				t = new Event(trimmed.split(" at ")[0], trimmed.split(" at ")[1]);
			}
			this.addTask(t);
			System.out.println("Got it. I've added this task:");
			System.out.println(t.toString());
			System.out.println(String.format("Now you have %s tasks in the list.", this.store.size()));
		} else if (input.contains("done")) {
			int num = Integer.valueOf(input.split(" ")[1]);
			this.store.get(num -1).markAsDone();
		} else if (input.contains("delete")) {
			int deleteat = Integer.valueOf(input.split(" ")[1]);
			this.deleteTask(deleteat);
		} else {
			throw new KeywordException();
		}
	}

	void greet() {
		String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
		System.out.println("Hello! I'm Duke\nWhat can I do for you?");
	}

    void chat() {
        this.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        while (!(input.equals("bye"))) {
        	//this.printTask(input);
        	try {
        		this.printTask(input);
        	} catch (DukeException err) {
        		System.out.println(err.getMessage());
        	}
	        sc = new Scanner(System.in);
	        input = sc.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}