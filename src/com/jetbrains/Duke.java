package com.jetbrains;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke! \n " +
                "Talk to me. \n" +
                "********************************");
        ArrayList<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                displayList(list);
                System.out.println("\n");
            } else {
                list.add(input);
                System.out.println("added: " + input + "\n");
            }
            input = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye! Come back soon!");
    }

    static void displayList(ArrayList<String> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n",i + 1, list.get(i));
        }
    }
}
