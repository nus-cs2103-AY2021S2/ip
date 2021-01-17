import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("**************************\nHey, " +
                "I am Duke.\nHow can I help you?\n" +
                "**************************");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<String> tasks = new ArrayList<>();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                System.out.println("**************************");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));

                }
                System.out.println("**************************");

            } else {
                System.out.println("   >>> " + input);
                tasks.add(input);
            }
            input = sc.nextLine();

        }


        System.out.println("**************************\n" +
                "Goodbye and see you soon!\n" +
                "**************************");

    }
}
