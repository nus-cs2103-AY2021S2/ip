import java.util.Scanner;

public class Ui {
    private Scanner s = new Scanner(System.in);

    public void showLine() {
        System.out.println("----------------------------------------------");
    }

    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.printLine();
        System.out.println("Hello! I`m Duke");
        System.out.println("How can i help you?");
        this.printLine();
    }

    public String askFilePath() {
        System.out.println("Please enter file name : ");
        return s.nextLine();
    }

    public void showError(String error) {
        System.out.println(error);
    }
}
