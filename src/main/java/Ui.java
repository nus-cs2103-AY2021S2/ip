import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
        System.out.println("Duke: Hello I'm Duke, what can I do for you?");
        System.out.println("-----------------------------------------------------");
    }

    protected String nextInput() {
        System.out.println("Enter an input ('bye' to quit): ");
        String userInput = sc.nextLine();
        System.out.println("User Input: " + userInput);
        return userInput;
    }

    protected void close() {
        System.out.println("Duke: Bye, hope to see you again! :)");
        System.out.println("-----------------------------------------------------");
    }

}
