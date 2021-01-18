import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
    	/*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        ChatBot c = new ChatBot();

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while(!input.equals("bye")) {
        	c.parser(input);
        	input = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Bye. Hope to see you again soon!");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
    }
}
