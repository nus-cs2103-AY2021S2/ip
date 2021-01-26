import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void sendToUser(String text) {
        String line = "    _______________________________________\n    ";
        System.out.println(line + text + "\n" + line);
    }

    public void showError() {
        System.out.println("Error.");
    }

    public void showError(Exception e) {
        System.out.println(e);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void greet() {
        String logo = 
                  " ____        _        \n" 
                + "|  _ \\ _   _| | _____ \n" 
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" 
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm a bot called Duke. Beep boop. \nWhat do you want?\n");
    }

    public void bye() {
        this.sc.close();
        this.sendToUser("BYE AND HAVE A GOOD DAY. Beep boop.");
    }
}