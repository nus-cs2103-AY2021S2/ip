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
        System.out.println("Hello! I'm Duke! \n" +
                "What would you like to do today? \n" +
                "***********************************");
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            Task task;
            try {
                if (input.equals("list")) {
                    displayList(list);
                    System.out.println("\n");
                } else if (input.contains("done")) {
                    String[] command = input.split(" ");
                    task = list.get(Integer.parseInt(command[1]) - 1);
                    System.out.println("Good job! I've marked this task as done:\n    " +
                            task.markDone() +
                            "\n");
                } else if (input.contains("delete")) {
                    String[] command = input.split(" ");
                    int index = Integer.parseInt(command[1]) - 1;
                    task = list.get(index);
                    System.out.println("Alright, I've deleted this task:\n    " +
                            task +
                            "\n");
                    list.remove(index);
                    System.out.println("Now you have " + list.size() +
                            " task(s) in the list. \n");
                } else if (input.contains("todo") ||
                        input.contains("deadline") ||
                        input.contains("event")) {

                    if (input.contains("todo")) {
                        task = new ToDo(input);
                    } else if (input.contains("deadline")) {
                        task = new Deadline(input);
                    } else {
                        task = new Event(input);
                    }
                    list.add(task);
                    System.out.println("Alright! I've added this task: \n   " +
                            task + "\nNow you have " + list.size() +
                            " task(s) in the list. \n");
                } else {
                    throw new DukeInvalidCommandException();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Oh no! This task does not exist. D:" );
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }

        sc.close();
        System.out.println("Bye! Stay on task!");
    }

    static void displayList(ArrayList<Task> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n",i + 1, list.get(i).toString());
        }
    }
}


