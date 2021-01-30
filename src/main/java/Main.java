import static ui.Ui.printBox;

import java.io.IOException;

import java.util.Scanner;

import simulator.ChatBot;

public class Main {
    public static void main(String[] args) {
        ChatBot cb = new ChatBot();
        cb.startUp();

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
