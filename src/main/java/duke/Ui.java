package duke;

import java.util.Scanner;

public class Ui {

    private final Scanner sc = new Scanner(System.in);

    private static final String logo = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";

    public Ui(){
    }

    public void welcome(){
        reply("Hello from\n" + logo + "\tHello! I'm Duke\n" + "\tWhat can I do for you?");
    }

    public String getInput(){
        return sc.nextLine();
    }

    public void showError(String message){
        reply(message);
    }

    public void reply(String reply){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + reply);
        System.out.println("\t____________________________________________________________");
    }
}
