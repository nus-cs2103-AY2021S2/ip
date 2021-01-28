import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class Duke is the main class for the execution of Duke chatbot.
 *
 * @version 21 Jan 2021
 * @author Zhang Peng
 */
public class Duke {
    /**
     * This is the main method for the Duke class .
     * @param args Unused.
     * @return Nothing.
     */


    public static void main(String[] args) throws FileNotFoundException {
        String input;
        ArrayList<Task> arrayList = new ArrayList<>();

        // https://www.javatpoint.com/how-to-create-a-file-in-java

        String path = "duke.txt";
        File dukeFile = new File("duke.txt"); // doest matter if the path exit
        boolean result;

        try {
            result = dukeFile.createNewFile();
            if (result) {
               System.out.println(".txt file created!");
            }


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        //read from the file content into the arrayList at the start.
        Scanner s = new Scanner(dukeFile);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.contains("[D]")) {
                String[] parts = line.split("by");
                String part1 = parts[0];
                String part2 = parts[1];
                Task task = new Deadline(part1.substring(7,part1.length()-1), part2.substring(2,part2.length()-1));
                task.index = arrayList.size() + 1;
                arrayList.add(task);
            } else if (line.contains("[E]")){
                String[] parts = line.split("at");
                String part1 = parts[0];
                String part2 = parts[1];
                Task task = new Event(part1.substring(7,part1.length()-1), part2.substring(2,part2.length()-1));
                task.index = arrayList.size() + 1;
                arrayList.add(task);
            } else if (line.contains("[T]")){
                Task task = new Todo(line.substring(7));
                task.index = arrayList.size() + 1;
                arrayList.add(task);
            }

        }

        // print out the file content
        //printFileContents();

        System.out.println("--------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            try {
                if (!(input.contains("todo") || input.contains("event") || input.contains("deadline")
                        || input.contains("list") || input.contains("done") || input.contains("delete"))) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else if (input.equals("todo") || input.equals("event") || input.equals("deadline")
                        || input.equals("done") || input.equals("delete")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }                                // can add in more exceptions
            } catch (DukeException e) {
                System.out.println("--------------------------");
                System.out.println(e.getMessage());
                System.out.println("--------------------------");
            }
            if (input.equals("bye")) {
                System.out.println("--------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------");

            } else if (input.equals("list")) {
                System.out.println("--------------------------");
                System.out.println("Here are the tasks in your list:");
                for (Task m : arrayList) {
                    System.out.print(m.index + ". " + m);
                    System.out.print("\n");
                }
                System.out.println("--------------------------");

            } else if (input.contains("done") && !(input.equals("done"))) {
                int x = Integer.parseInt(input.substring(5));
                try {
                    if (x > arrayList.size()) {
                        throw new DukeException("☹ OOPS!!! Sorry item no found :-(");
                    } else {
                        System.out.println("--------------------------");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(arrayList.get(x - 1).markAsDone().getStatusIcon() + " "
                                + arrayList.get(x - 1).description);
                        arrayList.set(x - 1, arrayList.get(x - 1).markAsDone());
                        System.out.println("--------------------------");

                    }

                } catch (DukeException e) {
                    System.out.println("--------------------------");
                    System.out.println(e.getMessage());
                    System.out.println("--------------------------");
                }

            } else if (input.contains("todo") && !(input.equals("todo"))) {
                System.out.println("--------------------------");
                System.out.println("Got it. I've added this task: ");
                Task task = new Todo(input.substring(5));
                task.index = arrayList.size() + 1;
                arrayList.add(task);
                System.out.println(task);
                System.out.println("Now you have " + arrayList.size() + " task(s) in the list");
                System.out.println("--------------------------");


            } else if (input.contains("deadline") && !(input.equals("deadline"))) {
                System.out.println("--------------------------");
                System.out.println("Got it. I've added this task: ");
                String[] parts = input.split("/");
                String part1 = parts[0];
                String part2 = parts[1];
                Task task = new Deadline(part1.substring(9), part2.substring(3));
                task.index = arrayList.size() + 1;
                arrayList.add(task);
                System.out.println(task);
                System.out.println("Now you have " + arrayList.size() + " task(s) in the list");
                System.out.println("--------------------------");

            } else if (input.contains("event") && !(input.equals("event"))) {
                System.out.println("--------------------------");
                System.out.println("Got it. I've added this task: ");
                String[] parts = input.split("/");
                String part1 = parts[0];
                String part2 = parts[1];
                Task task = new Event(part1.substring(6), part2.substring(3));
                task.index = arrayList.size() + 1;
                arrayList.add(task);
                System.out.println(task);
                System.out.println("Now you have " + arrayList.size() + " task(s) in the list");
                System.out.println("--------------------------");


            } else if (input.contains("delete") && !(input.equals("delete"))) {

                int deletedNumber = Integer.parseInt(input.substring(7));
                try {
                    if (deletedNumber > arrayList.size()) {
                        throw new DukeException("☹ OOPS!!! Sorry item no found :-(");
                    } else {
                        System.out.println("--------------------------");
                        System.out.println("Noted. I've removed this task: ");
                        int moved = deletedNumber - 1;
                        System.out.println(arrayList.get(moved));
                        arrayList.remove(arrayList.get(moved));
                        for (int i = moved; i < arrayList.size(); i++) {
                            arrayList.set(i, arrayList.get(i).decreaseIndex());
                        }
                        System.out.println("Now you have " + arrayList.size() + " task(s) in the list");
                        System.out.println("--------------------------");
                    }
                } catch (DukeException e) {
                    System.out.println("--------------------------");
                    System.out.println(e.getMessage());
                    System.out.println("--------------------------");
                }
            }
            try {
                int i = 0 ;
                if (arrayList.size() == 0) {

                    System.out.print("");
                } else if (arrayList.size() == 1) {
                    writeToFile(path,arrayList.get(i) + System.lineSeparator());
                } else {
                    writeToFile(path,arrayList.get(i) + System.lineSeparator());
                    i++;
                while (i <= arrayList.size()-1) {
                    appendToFile(path,arrayList.get(i) + System.lineSeparator());
                    i++;  // append to file with in the writetofile.
                }}

            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

    }


    private static void writeToFile(String path, String s) throws IOException{
        FileWriter fw = new FileWriter(path);
        fw.write(s);
        fw.close();
    }

    private static void appendToFile(String path, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(path, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}


/*
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void printFileContents() throws FileNotFoundException {
        File f = new File("duke.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
}
    }


    private static void writeToFile(String path, String s) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(s);
        fw.close();
    }

 */




