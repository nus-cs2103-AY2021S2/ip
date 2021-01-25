import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class Kobe {
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;
    public static String line2 = ind + "____________________________________________________________\n";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static String home = System.getProperty("user.home");

    public static void main(String[] args) {

        System.out.println(line + "Hello! I'm Kobe\n" + ind + "What can I do for you?\n" + line);

        //Retrieve data
        Path path = Paths.get(home + "/ip/src/main/data/kobe.txt"); //relative path
        boolean directoryExists = java.nio.file.Files.exists(path); //not necessary, try-catch works

        try (BufferedReader br = new BufferedReader(
                new FileReader(home + "/ip/src/main/data/kobe.txt", StandardCharsets.US_ASCII))) {

//            ArrayList<String> textFile = new ArrayList<>();
            String readLine = br.readLine();
            do {
                System.out.println(readLine);
                readLine = br.readLine();
                //Need to add actual tasks too
            }
            while (readLine != null);

            System.out.println(line + "Here are tasks that Kobe has retireved!\n" + line);
        } catch (IOException e) {
            //do nothing
        }


        Scanner sc = new Scanner(System.in);

        try {
            while (sc.hasNext()) {
                //Read the whole line, dissect each command word, including the condition after "/"
                String command = sc.nextLine();
                String[] commandArr = command.split(" ");
                String text = commandArr[0];

                if (text.equals("bye")) {
                    goodbye();
                    break;
                } else if (text.equals("list")) {
                    showList();
                } else if (text.equals("done")) {
                    int taskNumber = Integer.parseInt(commandArr[1]) - 1;
                    completeTask(taskNumber);
                } else if (text.equals("delete")) {
                    int taskNumber = Integer.parseInt(commandArr[1]) - 1;
                    deleteTask(taskNumber);
                } else {
                    String taskName = "";
                    String type = text;
                    String condition = "";


                    String[] commandArrFirst2Parts = command.split(" ", 2);

////                    Check for correct splitting
//                    System.out.println("First2Parts: " + Arrays.toString(commandArrFirst2Parts));
                    String firstWord = commandArrFirst2Parts[0];
//
                    if (commandArrFirst2Parts.length == 1) {
                        if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                            String errMessage = "Oh no! Kobe doesn't want your " + firstWord + " to be empty!";
                            throw new CustomExceptions.IncompleteDecriptionException(errMessage);
                        } else {
                            String errMessage = "Oh no! Kobe doesn't know what you mean!";
                            throw new CustomExceptions.IncorrectDecriptionException(errMessage);
                        }
                    }

                    String[] commandArrSecond2Parts = commandArrFirst2Parts[1].split(" /", 2);

                    //                //Check for correct splitting
                    //                System.out.println("Second2Parts: " + Arrays.toString(commandArrSecond2Parts));


                    taskName = commandArrSecond2Parts[0];

                    //If the array is in 2 parts, there is a condition, add that
                    if (commandArrSecond2Parts.length > 1) {
                        condition = commandArrSecond2Parts[1];
                    }

                    addItem(taskName, type, condition);
                }
            }
        } catch (CustomExceptions.IncompleteDecriptionException e) {
            System.out.println(e);
        } catch (CustomExceptions.IncorrectDecriptionException e) {
            System.out.println(e);
        }
//        } catch (KobeException e) {
//            System.out.println(e);
//        }

        sc.close();
    }

    public static void addItem(String echoedText, String type, String condition) {
        Task currentTask = new Task(echoedText, type, condition);
        tasks.add(currentTask);
        System.out.println(line + "Got it! Kobe added this task:\n" + ind + ind +
                 currentTask);
        System.out.println(ind + "Kobe sees that you have " + tasks.size() + " task(s) in the list.\n" + line);
    }

    public static void goodbye() {
        saveFile();
        System.out.println(line + "Bye. Kobe saved your list.\n" + ind
                + "Kobe hopes to see you again soon!\n" + line);
    }

    public static void showList() {
        System.out.print(line + "Here are the tasks in your list:\n");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.print(ind + (i+1) + ". " + tasks.get(i) + "\n");
        }
        System.out.println(line);
    }

    public static void completeTask(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        System.out.print(line + "Nice work! Kobe will mark your task as done!\n" + ind);
        System.out.println(ind + tasks.get(taskNumber));
        System.out.println(line);
    }

    public static void deleteTask(int taskNumber) {
        if (tasks.isEmpty()) { //Managing empty lists from the start
            System.out.print(line + "Kobe sees no more tasks from the list!\n" + line + "\n");
        } else {
            System.out.print(line + "Okay! Kobe will remove your task from the list!\n" + ind);
            System.out.println(ind + tasks.get(taskNumber));
            tasks.remove(taskNumber);
            System.out.println(ind + "Kobe sees that you now have " + tasks.size() + " task(s) in the list.");
            if (tasks.isEmpty()) { //If it's now empty, inform them.
                System.out.print(ind + "Your list is now empty!\n");
            }
            System.out.println(line);
        }
    }

    //UPDATE THE KOBE.TXT FILE
    public static void saveFile() {
//        java.nio.file.Path path = java.nio.file.Paths.get("home/ip/src/main/data/kobe.txt");
        java.nio.file.Path path = java.nio.file.Paths.get(home + "/ip/src/main/data");
//        boolean directoryExists = java.nio.file.Files.exists(path);
//        System.out.println("Directory Home: " + home);
//        System.out.println("Directory: " + path.toString());
//        System.out.println("Directory exists?: " + directoryExists);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(home + "/ip/src/main/data/kobe.txt",
                    StandardCharsets.US_ASCII))) {

            for(int i = 0; i < tasks.size(); i++) {
                bw.write(ind + (i+1) + ". " + tasks.get(i) + "\n");
            }

        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
