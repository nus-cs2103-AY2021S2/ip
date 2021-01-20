import java.util.*;

public class Duke {

	private ArrayList<String> store = new ArrayList<>();

	public void addText (String text) {
		this.store.add(text);
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
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!(input.equals("bye"))) {
        	if (input.equals("list")) {
        		int counter = 1;
        		for (String elem: this.store) {
        			System.out.println(counter + ". " + elem);
        			counter += 1;
        		}
        	} else {
	            this.addText(input);
	            System.out.println("added: " + input);
	        }
	        in = new Scanner(System.in);
	        input = in.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}