import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {

    public static void main(String[] args) throws DukeException {

        List<Task> contentList = new ArrayList<>();

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        String filePath = "duke.txt"; // File path to change
        refreshFile(filePath); // Makes a new empty file or remakes a already existing file

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            StringBuilder output = new StringBuilder();

            try {
                if (input.equals("bye")) {
                    try {
                        appendToFile(filePath, printList(contentList));
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    textWarper("Bye. Hope to see you again soon!");
                    break;

                } else if (input.equals("list")) {
                    output.append("Here are the tasks in your list: \n");
                    output.append(printList(contentList));

                } else if (input.startsWith("done")) {
                    output.append("Nice! I've marked this task as done: \n  ");
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    contentList.get(index).setCompleted();
                    output.append(contentList.get(index));

                } else if (input.endsWith("todo") || input.endsWith("deadline") || input.endsWith("event")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");

                } else if (input.startsWith("todo")) {
                    String[] arr = input.split(" ", 2);
                    Task temp = new ToDo(arr[1]);
                    contentList.add(temp);
                    taskAdded(output, contentList, temp);

                } else if (input.startsWith("deadline")) {
                    String[] arr = input.split(" ", 2);
                    Task temp = new Deadline(arr[1]);
                    contentList.add(temp);
                    taskAdded(output, contentList, temp);

                } else if (input.startsWith("event")) {
                    String[] arr = input.split(" ", 2);
                    Task temp = new Event(arr[1]);
                    contentList.add(temp);
                    taskAdded(output, contentList, temp);

                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    output.append("Noted. I've removed this task: \n  ");
                    output.append(contentList.get(index) + "\n");
                    contentList.remove(index);
                    output.append(String.format("Now you have %d tasks in the list.", contentList.size()));

                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                output.append(e.toString());
            } catch (IndexOutOfBoundsException e) {
                output.append("You have nothing in the list!");
            }

            textWarper(output.toString());
        }

    }

    private static void taskAdded(StringBuilder output, List<Task> contentList, Task temp) {
        output.append("Got it. I've added this task:\n ");
        output.append(temp.toString() + "\n");
        output.append(String.format("Now you have %d tasks in the list.", contentList.size()));
    }

    private static void textWarper(String a) {
        System.out.println("____________________________________________________________");
        System.out.println(a);
        System.out.println("____________________________________________________________");
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void refreshFile(String filePath) {
        try {
            Files.delete(Paths.get(filePath));
            writeToFile(filePath, "");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static String printList(List<Task> contentList) {
        StringBuilder printStr = new StringBuilder();
        for (int i = 0; i < contentList.size(); i++) {
            String textToAdd = String.format("%d.%s%n", i + 1, contentList.get(i).toString());
            printStr.append(textToAdd);
        }
        return printStr.toString();
    }

}
