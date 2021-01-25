import java.util.*;
import java.io.*;

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
		
		TaskList tl = loadTaskList();
		
        while (!in.equals("bye")) {
			
			try {
				
				String[] split = in.split("\\s");
				
				String cmd = split[0];
				
				switch(cmd) {
					case "list":
						System.out.println("Here are the tasks in your list:");
						tl.printList();
						break;
					case "done":
						if (split.length <= 1) {
							throw new DukeException("OOPS!!! A number needs to be specified.");
						}
						
						try {
							Task t = tl.markDone(Integer.parseInt(split[1]));
							
							System.out.println("Nice! I've marked this task as done:");
							System.out.println(t);
						} catch(NumberFormatException ne) {
							throw new DukeException("OOPS!!! A number needs to be specified.");
						}
						
						break;
					case "todo":
						split = in.split("todo\\s");
						
						if (split.length <= 1) {
							throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
						}
						
						Task td = new Task(split[1]);
						tl.add(td);
						
						System.out.println("Got it. I've added this task:");
						System.out.println(td);
						System.out.println("Now you have "+tl.count()+" tasks in the list.");
						break;
					case "deadline":
						split = in.split("deadline\\s");
						
						if (split.length <= 1) {
							throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
						}
						
						split = split[1].split("\\s/by\\s");
						
						if (split.length <= 1) {
							throw new DukeException("OOPS!!! A date and time is needed.");
						}
						
						Task dl = new Task(split[0], 'D', split[1]);
						tl.add(dl);
						
						System.out.println("Got it. I've added this task:");
						System.out.println(dl);
						System.out.println("Now you have "+tl.count()+" tasks in the list.");
						break;
					case "event":
						split = in.split("event\\s");
						
						if (split.length <= 1) {
							throw new DukeException("OOPS!!! The description of a event cannot be empty.");
						}
						
						split = split[1].split("\\s/at\\s");
						
						if (split.length <= 1) {
							throw new DukeException("OOPS!!! A date and time is needed.");
						}
						
						Task ev = new Task(split[0], 'E', split[1]);
						tl.add(ev);
						
						System.out.println("Got it. I've added this task:");
						System.out.println(ev);
						System.out.println("Now you have "+tl.count()+" tasks in the list.");
						break;
					case "delete":
						if (split.length <= 1) {
							throw new DukeException("OOPS!!! A number needs to be specified.");
						}
						
						try {
							Task t = tl.remove(Integer.parseInt(split[1]));
							
							System.out.println("Noted. I've removed this task");
							System.out.println(t);
							System.out.println("Now you have "+tl.count()+" tasks in the list.");
						} catch(NumberFormatException ne) {
							throw new DukeException("OOPS!!! A number needs to be specified.");
						}
						
						break;
					default:
						throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
						
				}
			
			} catch(DukeException de) {
				System.out.println(de.getMessage());
			}
			
			in = sc.nextLine();
			
        }
		
		saveTaskList(tl);
		System.out.println("Bye. Hope to see you again soon!");
					
        
    }
	
	private static TaskList loadTaskList(){
		File f = new File("tasklist");
		try {
			if (f.createNewFile()) {
				return new TaskList();
			} else {
				
				BufferedReader br = new BufferedReader(new FileReader(f));
				
				TaskList tl = new TaskList();
				
				String line;
				while((line = br.readLine()) != null) {
					String[] split = line.split("\\|");
					
					// Create new task for each line
					char type = split[0].charAt(0);
					
					boolean done = false;
					if (split[1].equals("D")) {
						done = true;
					}
					
					String name = split[2];
					
					if (type == 'D' || type == 'E') {
						String dateTime = split[3];
						
						Task t = new Task(name, type, dateTime);
						if (done)
							t.mark();
						
						tl.add(t);
						
					} else {
						Task t = new Task(name);
						if (done)
							t.mark();
						
						tl.add(t);
					}
				}
				
				return tl;
					
				
			}
			
		} catch(IOException e) {
			System.out.println(e.toString());
			return new TaskList();
		}
	}
	
	private static void saveTaskList(TaskList tl){
		File f = new File("tasklist");
		
		try {
			FileWriter fw = new FileWriter(f, false);
			
			for (int i = 0; i < tl.count(); i++) {
				
				Task t = tl.getTask(i);
				
				String line = "" + t.getType() + "|";
				if (t.getDone()) {
					line += "D|";
				} else {
					line += "ND|";
				}
				
				line += t.getName();
				
				if (t.getType() == 'D' | t.getType() == 'E') {
					line += "|" + t.getDate();
				}
				
				line += "\n";
				
				fw.write(line);
				
			}
			
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			System.out.println("Error writing to file!");
			
		}
		
	}
	
}

class Task {
	private String name;
	private char type;
	private String dateTime;
	private boolean done;
	
	public Task(String n, char t, String dt) {
		name = n;
		type = t;
		dateTime = dt;
		done = false;
	}
	
	public Task(String n) {
		name = n;
		type = 'T';
		done = false;
	}
	
	public char getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDate() {
		return dateTime;
	}
	
	public boolean getDone() {
		return done;
	}
	
	public void mark() {
		done = true;
	}
	
	public String toString() {
		
		String str = "["+type+"]";
		
		if (done)
			str += "[X] "+name;
		else
			str += "[ ] "+name;
		
		switch (type) {
			case 'D':
				str += " (by: "+dateTime+")";
				break;
			case 'E':
				str += " (at: "+dateTime+")";
				break;
		}
		
		return str;
		
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
	
	public void add(Task t) {
		taskList.add(t);
	}
	
	public Task remove(int i) throws DukeException{
		if (i <= 0 || i > taskList.size()) {
			throw new DukeException("OOPS!!! There is no item at that position.");
		}
		
		Task t = taskList.remove(i-1);
		
		return t;
	}
	
	public Task markDone(int i) throws DukeException{
		if (i <= 0 || i > taskList.size()) {
			throw new DukeException("OOPS!!! There is no item at that position.");
		}
		
		Task t = taskList.get(i-1);
		t.mark();
		
		return t;
	}
	
	public int count() {
		return taskList.size();
	}
	
	public Task getTask(int i) {
		return taskList.get(i);
	}
	
}
