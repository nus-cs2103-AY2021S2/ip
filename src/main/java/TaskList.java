import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class TaskList {
	
	private List<Task> taskList;
	
	public TaskList(){
		taskList = new ArrayList<Task>();
	}
	
	public String printList() {
		String res = "";
		for (int i = 0; i < taskList.size(); i++) {
			res += (i+1)+". "+taskList.get(i)+"\n";
		}
		return res;
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