import java.util.Scanner;

public class Ui{
    private Scanner s;
    private final static String HELP_LIST = "";
    private final static String DIVIDER = "--------------------------------";

    public Ui(){
        s = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void handlePrint(String message) {
        System.out.println(message);
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
    }

    public String readCommand() {
        return s.nextLine();
    }

    public void exit() {
        System.out.println("Cya!");
    }

    public void showHelp() {
        System.out.println("HELP!");
    }
}
