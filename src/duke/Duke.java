package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    public static void main(String[] args) {


        ArrayList<Task> list = loadFile();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke! \n" +
                "What would you like to do today? \n" +
                "***********************************");

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
                            task);
                    list.remove(index);
                    saveFile(list);
                    System.out.println("Now you have " + list.size() +
                            " task(s) in the list. \n");
                } else if (input.contains("todo") ||
                        input.contains("deadline") ||
                        input.contains("event")) {
                    task = Task.parseInput(input);
                    list.add(task);
                    saveFile(task.stringToSave());
                    System.out.println("Alright! I've added this task: \n   " +
                            task + "\nNow you have " + list.size() +
                            " task(s) in the list. \n");
                } else {
                    throw new DukeInvalidCommandException();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Oh no! This task does not exist. D:\n" );
            } catch (DateTimeParseException e) {
                System.out.println("Oh no! " +
                        " Please key in the date in the format YYYY-MM-DD.\n");
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

    static Task fileToTask(String input) {
        return Task.fileReader(input);
    }

    static ArrayList<Task> loadFile() {
        String path = "./data/duke.txt";
        String directory = "./data";
        File d = new File(directory);
        File f = new File(path);
        ArrayList<Task> list = new ArrayList<>();
        if (!d.exists()){
            d.mkdirs();
        }
        try {
            f.createNewFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                try {
                    String line = sc.nextLine();
                    Task task = fileToTask(line);
                    list.add(task);
                } catch (Exception e) {}
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    static void saveFile(String input) {
        String path = "./data/duke.txt";
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(input + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
        }
    }

    static void saveFile(ArrayList<Task> list) {
        String path = "./data/duke.txt";
        try {
            FileWriter fw = new FileWriter(path);
            for(Task t: list) {
                fw.write(t.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


