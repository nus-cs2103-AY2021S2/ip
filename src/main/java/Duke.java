import java.util.*;

public class Duke {

	private ArrayList<Task> store = new ArrayList<>();

	public void addText (String text) {
		Task t = new Task(text);
		this.store.add(t);
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
        	if (input.equals("list")) {
        		System.out.println("Here are the tasks in your list:");
        		int counter = 1;
        		for (Task elem: this.store) {
        			System.out.println(counter + ". " + "[" + elem.getStatusIcon() + "] " + elem.toString());
        			counter += 1;
        		}
        	} else if (input.split(" ")[0].equals("done")) {
        		int tasknumber = Integer.valueOf(input.split(" ")[1]);
        		this.store.get(tasknumber -1).markAsDone();
        		System.out.println("Nice! I've marked this task as done:");
        		System.out.println("[" + this.store.get(tasknumber -1).getStatusIcon() + "] " + this.store.get(tasknumber -1).toString());
        	} else {
	            this.addText(input);
	            System.out.println("added: " + input);
	        }
	        sc = new Scanner(System.in);
	        input = sc.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}