import java.util.Scanner;

public class Duke {

    private Scanner sc = new Scanner(System.in);
    final String lines = "----------------------------------------";

    public void displayWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi There! Greetings from \n" + logo);
        System.out.println(lines + "\n" + " Good day! I'm Duke" + "\n" + " How can I help you today? " + "\n" + lines);

    }

    public void displayEndMessage(){
        sc.close();
        System.out.println( lines + "\n" + " Bye. Hope to see you again!" + "\n" + lines);
    }

    public void main(String[] args) {

        displayWelcomeMessage();

        String userInput = sc.nextLine();

        while(!userInput.equals("bye")) {
            System.out.println("\t" + lines + "\n\t" + " " + userInput + "\n\t" + lines);
            userInput = sc.nextLine();
        }
        displayEndMessage();
    }
}