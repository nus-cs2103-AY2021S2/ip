import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        
        printMessage("Hey! It's PAson, ready to help :)\nHow can I help you today?");
        while(scanner.hasNext()) {
            input = scanner.nextLine().toLowerCase();
            if(input.equals("bye")) {
                printMessage("Bye! I shall go rest now. PAge me when you need me!");
                break;
            }
            printMessage(input);
        }
    }

    public static void printMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
