import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        chatBot();

    }

    public static void chatBot(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
		
		TaskList tl = new TaskList();
		
        while (!in.equals("bye")) {
            String[] split = in.split("\\s");
			
			String cmd = split[0];
			
			switch(cmd) {
				case "bye":
					break;
				case "list":
					tl.printList();
					break;
				case "done":		
					Task t = tl.markDone(Integer.parseInt(split[1]));
					
					System.out.println("Nice! I've marked this task as done:");
					System.out.println(t);
					break;
				default:
					tl.add(in);
					System.out.println("added: "+in);
					break;
			}
			
			in = sc.nextLine();
			
        }
		
		System.out.println("Bye. Hope to see you again soon!");
					
        
    }
	
}

class Task {
	private String name;
	private boolean done;
	
	public Task(String n) {
		name = n;
		done = false;
	}
	
	public void mark() {
		done = true;
	}
	
	public String toString() {
		if (done)
			return "[X] "+name;
		else
			return "[ ] "+name;
	}
}

class TaskList {
	
	private List<Task> taskList;
	
	public TaskList(){
		taskList = new ArrayList<Task>();
	}
	
	public void printList() {
		for (int i = 0; i < taskList.size(); i++) {
			System.out.println(""+(i+1)+". "+taskList.get(i));
		}
	}
	
	public void add(String obj) {
		taskList.add(new Task(obj));
	}
	
	public Task markDone(int i) {
		Task t = taskList.get(i-1);
		t.mark();
		
		return t;
	}
	
}
