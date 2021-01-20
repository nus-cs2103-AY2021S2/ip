import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hey yo, I'm List.\nI store your words. \n";
        String goodbye = "    Bye bye, catch you soon.";
        Scanner sc = new Scanner(System.in);
        String input = "";
        String[] storedTexts = new String[100];
        int textIndex = 0;

        System.out.println(greeting);
        input = sc.nextLine();
        while(!input.equals("bye")) {

            if(input.equals("list")) {
                for(int i = 1; i <= textIndex; i++) {
                    System.out.println("    " + i + ". " + storedTexts[i - 1]);
                }
                System.out.println();
            } else {
                storedTexts[textIndex] = input;
                System.out.println("    added: " + input + "\n");
                textIndex++;
            }

            input = sc.nextLine();
        }

        System.out.println(goodbye);
    }
}
