package main.java;

import main.java.Task;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String welcomeMsg = "Olly: Hey! Welcome to the chatbot. What can I do for you today?";
        System.out.println(welcomeMsg);

        Task[] tasks = new Task[100];

        int textCounter = 0;

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Olly: Goodbye for now, we will meet again.");
                break;
            }

            if (input.equals("list")) {
                System.out.println("Olly: Here you go! Your list of items:");
                for (int i = 0; i < textCounter; i++) {
                    System.out.println(i+1 + ". " + tasks[i]);
                }
            } else if (input.contains("done")) {
                String[] command = input.split(" ");
                Integer index = Integer.parseInt(command[1]);
                if (index > 0 && index <= textCounter) {
                    Task doneTask = tasks[index - 1];
                    doneTask.setStatus(true);
                    System.out.println("Olly: Swee! This task is done:");
                    System.out.println(doneTask);
                } else {
                    System.out.println("Olly: The task number does not work, try again?");
                }
            } else {
                Task newTask = new Task(input, false);
                tasks[textCounter] = newTask;
                textCounter++;
                System.out.println("[Added to list] " + input);
            }
        }
    }
}
