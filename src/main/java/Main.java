import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner sc = new Scanner(System.in);
        boolean toTerminate = false;
        while (!toTerminate) {
            String input = sc.nextLine();

            switch (input) {
            case "bye":
                duke.formattedPrint("Bye. Hope to see you again soon!");
                toTerminate = true;
                break;
            default:
                duke.formattedPrint(input);
                break;
            }
        }
    }
}
