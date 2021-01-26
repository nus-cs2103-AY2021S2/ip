
public class Ui {

	/**
     * Prints a big Duke and greets the user. Prompts user to start input.
     */
    public void greet() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke :) What can I do for you?");
        System.out.println("----------------------------------------------------------");
    }

    public void separateLine() {
    	System.out.println("----------------------------------------------------------");
    }

    public void sayBye() {
    	System.out.println("Byebye~ Hope to see you again soon!");
    }
}