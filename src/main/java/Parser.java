import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Parser {
	
	private boolean end;
	
	public Parser() {
		end = false;
	}
	
	public boolean checkEnd() {
		return end;
	}
	
	public String process(String in, TaskList tl){
		
		try {
			
			String res = "";
			
			String[] split = in.split("\\s");
			
			String cmd = split[0];
			
			switch(cmd) {
				case "bye":
					end = true;
					res = "Bye. Hope to see you again soon!";
					break;
				case "list":
					res = "Here are the tasks in your list:\n";
					res += tl.printList();
					break;
				case "done":
					if (split.length <= 1) {
						throw new DukeException("OOPS!!! A number needs to be specified.");
					}
					
					try {
						Task t = tl.markDone(Integer.parseInt(split[1]));
						
						res = "Nice! I've marked this task as done:\n";
						res += t;
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
					
					res = "Got it. I've added this task:\n";
					res += td + "\n";
					res += "Now you have "+tl.count()+" tasks in the list.";
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
					
					LocalDate dt = LocalDate.now();
					
					try {
					
						dt = LocalDate.parse(split[1]);
					
					} catch(DateTimeParseException dte) {
						throw new DukeException("OOPS!!! The date format should be in YYYY-MM-DD");
					}
					
					Task dl = new Task(split[0], 'D', dt);
					tl.add(dl);
					
					res = "Got it. I've added this task:\n";
					res += dl + "\n";
					res += "Now you have "+tl.count()+" tasks in the list.\n";
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
					
					LocalDate dtt = LocalDate.now();
					
					try {
					
						dtt = LocalDate.parse(split[1]);
					
					} catch(DateTimeParseException e) {
						throw new DukeException("OOPS!!! The date format should be in YYYY-MM-DD");
					}
					
					Task ev = new Task(split[0], 'E', dtt);
					tl.add(ev);
					
					res = "Got it. I've added this task:\n";
					res += ev + "\n";
					res += "Now you have "+tl.count()+" tasks in the list.\n";
					break;
				case "delete":
					if (split.length <= 1) {
						throw new DukeException("OOPS!!! A number needs to be specified.");
					}
					
					try {
						Task t = tl.remove(Integer.parseInt(split[1]));
						
						res = "Noted. I've removed this task\n";
						res += t + "\n";
						res += "Now you have "+tl.count()+" tasks in the list.\n";
					} catch(NumberFormatException ne) {
						throw new DukeException("OOPS!!! A number needs to be specified.");
					}
					
					break;
				default:
					throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
					
			}
			
			return res;
			
		} catch(DukeException de) {
			return de.getMessage();
		}
		
	
	}
	
}