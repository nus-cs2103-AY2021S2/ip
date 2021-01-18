import java.util.Scanner;

public class Level1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("    _________________________________________________");
        System.out.println("     Hello! I'm Duke :)");
        System.out.println("     What can I do for you?");
        System.out.println("    _________________________________________________");

        String[] tasksArray = new String[100];
        int count = 0;

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("    _________________________________________________");
                System.out.println("     Bye. Hope to see you again soon! :)");
                System.out.println("    _________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("    _________________________________________________");
                for (int i = 0; i < count; i++) {
                    System.out.println(i+1 + ". " + tasksArray[i]);
                }
                System.out.println("    _________________________________________________");
            } else {
                tasksArray[count] = input;
                System.out.println("    _________________________________________________");
                System.out.println("     " + "added : " + input);
                System.out.println("    _________________________________________________");
                count++;
            }
        }
    }
}
