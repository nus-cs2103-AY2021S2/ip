import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void greeting() {
        printLine();
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printMsg("Hello! I'm Duke");
        printMsg("What can I do for you?");
        printLine();
    }

    public void printMsg(String msg) {
        System.out.println("     " + msg);
    }

    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void sayBye() {
        printMsg("Bye. Hope to see you again soon!");
    }
}
