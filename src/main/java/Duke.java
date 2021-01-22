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
		
		InList il = new InList(in);
		
		System.out.println("added: "+in);
		
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
			
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
	
}

class InList {
	
	private List<String> objList;
	
	public InList(String obj){
		objList = new ArrayList<String>();
		objList.add(obj);
	}
	
	public void printList() {
		for (int i = 0; i < objList.size(); i++) {
			System.out.println(""+(i+1)+". "+objList.get(i));
		}
	}
	
	public void add(String obj) {
		objList.add(obj);
	}
	
}
