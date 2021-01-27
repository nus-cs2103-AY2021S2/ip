package duke;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Parser p = new Parser();

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while(!input.equals("bye")) {
        	p.parser(input);
        	input = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________");
		System.out.println();
		System.out.println("      Bye. Hope to see you again soon!");
		System.out.println("    ____________________________________________________________");
		System.out.println();
    }
}
