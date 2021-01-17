import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        System.out.println("Greetings. My name is I-01B, but you may call me CHEF. What can I assist you with?");
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("I hope I have been of assistance. Goodbye.");
                break;
            } else {
                System.out.println(command);
            }
        }
    }
}
