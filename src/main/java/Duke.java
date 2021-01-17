import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("POWERED BY\n" + logo);
        greet();
        String userInput = sc.next();
        while (!userInput.equals("bye")) {
            echo(userInput);
            userInput = sc.next();
        }
        exit();
    }

    public static void greet() {
        print("Hello! I'm Duke.\n\t  How can I help you?");
    }

    public static void echo(String message) {
        print(message);
    }

    public static void exit() {
        print("Goodbye. See you later!");
    }

    public static void print(String message) {
        String horizLine = "\n\t____________________________________________________________\n";
        System.out.println(horizLine + "\t  " + message + horizLine);
    }
}
