import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Ui {
	
	private Parser p;
	
	public Ui() {
		p = new Parser();
	}
	
	public void greeting() {
		System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
	}
	
	public void getInput(TaskList tl) {
		
		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine();
		
		String out = p.process(in, tl);
		
		while (!p.checkEnd()) {
			System.out.println(out);
			
			in = sc.nextLine();
			out = p.process(in, tl);
		}
		
		System.out.println(out);
		
	}
	
}