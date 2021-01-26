import java.io.IOException;
import java.util.Scanner;

import static simulator.Design.printBox;
import static simulator.Design.WELCOME_MSG;
import static simulator.Design.LOGO;
import static simulator.Design.GREETING_MSG;


public class Main {
    public static void main(String[] args) {
        System.out.println(WELCOME_MSG + LOGO);
        ChatBot cb = new ChatBot();
        cb.startUp();
        printBox(GREETING_MSG);


        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals(("bye"))) {
            cb.process(input);
            input = sc.nextLine();
        }

        try {
            cb.save();
        } catch (IOException ex) {
            printBox(ex.getMessage());
        }


    }
}
