import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String line = "---------------------------\n";

        Scanner sc = new Scanner(System.in);
        System.out.println(line + "what's up? :)" + "\n" + line);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(line + userInput + "\n" + line);
        }
        System.out.println("bye, have a good day! :)");
    }
}
