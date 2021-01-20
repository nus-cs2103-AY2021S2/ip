import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        ArrayList<String> listOfText = new ArrayList<>();

        System.out.println("\t_____________________________________________________________");
        System.out.println("\t Hello! I'm Duke\n\t What can I do for you");
        System.out.println("\t_____________________________________________________________");

        String textInput = read.nextLine();
        Boolean checkBye = false;
        if (textInput.toLowerCase().equals("bye")) {
            checkBye = true;
        }

        while (!checkBye) {
            if (textInput.toLowerCase().equals("list")) {
                int i = 1;
                System.out.println("\t_____________________________________________________________");
                for (String s : listOfText) {
                    System.out.println("\t " + i + ". " + s);
                    i++;
                }
                System.out.println("\t_____________________________________________________________");
            } else {
                listOfText.add(textInput);
                System.out.println("\t_____________________________________________________________");
                System.out.println("\t added: " + textInput);
                System.out.println("\t_____________________________________________________________");
            }
            textInput = read.nextLine();
            if (textInput.toLowerCase().equals("bye")) {
                checkBye = true;
            }
        }

        System.out.println("\t_____________________________________________________________");
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t_____________________________________________________________");
    }
}
