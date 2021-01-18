package main.java;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {
            String s = sc.nextLine();
            if (s.equals("bye")) break;
            else System.out.println(s);
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
