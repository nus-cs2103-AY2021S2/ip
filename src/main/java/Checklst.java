import java.util.Scanner;

public class Checklst {
    
    public static void main(String[] args) {
        Checklst checklst = new Checklst();
        checklst.run();
    }

    public static void sendOutput(String output) {
        System.out.println("\t----------------------------------------");
        System.out.println("\t" + output);
        System.out.println("\t----------------------------------------");
    }

    public void run() {
        sendOutput("Hello I'm Checklst! What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            sendOutput(input);
            input = scanner.nextLine();
        }

        sendOutput("Bye! Hope to see you again!");

        scanner.close();
    }


}
