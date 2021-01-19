import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String inputArr[] = new String[100];
        Scanner sc = new Scanner(System.in);
        System.out.println("    ----------------------------------\n    " + "Hello! I'm Duke\n    What can I do for you?" + "\n    ----------------------------------\n");
        String input = "";
        int n = 0;

        while(true) {
            input = sc.nextLine();

            if(input.equals("bye")) break;
            inputArr[n] = input;
            System.out.println("    ----------------------------------\n    " + inputArr[n] + "\n    ----------------------------------\n");
            n++;
        }
        System.out.println("    ----------------------------------\n    " + "Bye. Hope to see you again soon!" + "\n    ----------------------------------\n");
    }
}