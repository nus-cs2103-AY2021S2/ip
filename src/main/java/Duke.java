import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");

        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
        String[] str = new String[100];
        int k, i = 0;

        while (!string.equals("bye")) {
            if (string.equals("list")) {
                for (int j = 0; j < i; j++) {
                    k = j + 1;
                    System.out.println(k + ". " + str[j]);
                }
                string = scan.nextLine();
            } else {
                System.out.println("added: " + string);
                str[i++] = string;
                string = scan.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon");

    }

}
