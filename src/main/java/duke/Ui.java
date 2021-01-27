package duke;

import java.util.Scanner;

public class Ui {
<<<<<<< Updated upstream
	
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
	
=======
    
    private Parser p;
    
    /**
    * Constructor for the Ui class.
    */
    public Ui() {
        p = new Parser();
    }
    
    /**
    * Prints the greeting for the chatbot.
    */
    public void greeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    
    /**
    * Gets the input of the user and passes it to the Parser for processing
    * @param tl TaskList to be perform the commands on
    */
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
    
>>>>>>> Stashed changes
}