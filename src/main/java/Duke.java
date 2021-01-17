import java.util.Scanner;

public class Duke {
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
    
    // Constants
    private static final String line = "____________________________________________________________";

    // Attributes
    private Scanner scanner;
    private boolean isActive;

    public Duke() {
        this.scanner = new Scanner(System.in);
        this.isActive = true;
    }

    public void start() {
        greet();
        while(isActive) {
            listen();
        }
    }
    
    private void greet() {
        String greeting = "Hello! I'm Duke" + "\n"
        + "What can I do for you?";
        print(greeting);
    }

    private void listen() {
        String input = scanner.nextLine();
        switch(input) {
            case "bye":
                isActive = false;
                String output = "Bye. Hope to see you again soon!";
                print(output);
                break;
            default:
                print(input);
                break;
        }
    }

    private void print(String input) {
        System.out.println(line + "\n" + input + "\n" + line + "\n");
    }
}

