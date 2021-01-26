import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Duke {
    public static String tab = "     ";
    public static String line = "     ............................................................";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int numTasks = 0;
    public static StringBuffer stringBufferOfData = new StringBuffer();
    public static String pathString;
    public static Path filepath;

    public static void main(String[] args) throws IOException {
        printIntro();
        Scanner scan = new Scanner(System.in);

        String currentDir = System.getProperty("user.dir");
        pathString = currentDir + "/data/duke.txt";
        filepath = Paths.get(pathString);

        try {
            loadFileContents(pathString);
        } catch (FileNotFoundException e) {
            Path dirPath = Paths.get(currentDir + "/data");
            Files.createDirectories(dirPath);
            Files.createFile(filepath);
        }

        loop:
        while (scan.hasNextLine()) {
            String input = scan.nextLine();

            System.out.println(line);

            switch (input) {
                case "bye":
                    printBye();
                    break loop;
                case "list":
                    printList();
                    break;
                default:
                    try {
                        String[] inputArr = input.split(" ", 2);
                        String task = inputArr[0];

                        String scannedInput = inputArr[1];
                        String description;
                        LocalDate date;
                        int taskIndex;

                        switch (task) {
                            case "done":
                                taskIndex = Integer.parseInt(scannedInput) - 1;
                                String before = tasks.get(taskIndex).formatData();

                                tasks.get(taskIndex).markAsDone();
                                String after = tasks.get(taskIndex).formatData();

                                modifyFile(before, after);

                                printDone(taskIndex);
                                break;
                            case "delete":
                                taskIndex = Integer.parseInt(scannedInput) - 1;
                                Task deletedTask = tasks.get(taskIndex);

                                deleteFromFile(deletedTask.formatData());

                                tasks.remove(taskIndex);
                                printDelete(deletedTask.toString());

                                numTasks--;
                                printNumTasks();
                                break;
                            case "todo":
                                description = scannedInput;
                                ToDos todo = new ToDos(description);
                                tasks.add(todo);
                                printAdd(numTasks);
                                addToFile(pathString, todo.formatData());

                                numTasks++;
                                printNumTasks();
                                break;
                            case "deadline":
                                String[] arrOfInputD = scannedInput.split("/by ");
                                description = arrOfInputD[0];
                                date = LocalDate.parse(arrOfInputD[1]);
                                Deadlines deadline = new Deadlines(description, date);
                                tasks.add(deadline);
                                printAdd(numTasks);
                                addToFile(pathString, deadline.formatData());

                                numTasks++;
                                printNumTasks();
                                break;
                            case "event":
                                String[] arrOfInputE = scannedInput.split("/at ");
                                description = arrOfInputE[0];
                                date = LocalDate.parse(arrOfInputE[1]);
                                Events event = new Events(description, date);
                                tasks.add(event);
                                printAdd(numTasks);
                                addToFile(pathString, event.formatData());

                                numTasks++;
                                printNumTasks();
                                break;
                            default:
                                printIdkError();
                                break;
                        }
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (input.equals("todo") || input.equals("deadline") || input.equals("event")
                                || input.equals("done") || input.equals("delete")) {
                            printEmptyDescError(input);
                        } else {
                            printIdkError();
                        }
                    }
            }
            System.out.println(line);
        }
    }

    public static void printIntro() {
        String logo =
                          " _____   _   _\n"
                        + "| ____| | | | |\n"
                        + "| |___  | | | | __   __\n"
                        + "|  ___| | | | | \\ \\ / /\n"
                        + "| |___  | | | |  \\ v /\n"
                        + "|_____| |_| |_|  /  /\n"
                        + "                /__/\n";

        System.out.println("   C H A T   W I T H\n" + logo);

        System.out.println(line + "\n"
                + tab + "Hi there! I'm Elly.\n"
                + tab + "How can I help you today?\n"
                + line);
    }

    public static void printList() {
        System.out.println(tab + "Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            int num = i + 1;
            Task task = tasks.get(i);
            System.out.println(
                    tab + num + "." + task.toString());
        }
    }

    public static void printDone(int index) {
        System.out.println(tab + "Nice! I've marked this task as done:");
        System.out.println(tab + tasks.get(index).toString());
    }

    public static void printDelete(String string) {
        System.out.println(tab + "Noted. I've removed this task:");
        System.out.println(tab + string);
    }

    public static void printAdd(int index) {
        System.out.println(tab + "Got it. I've added this task:");
        System.out.println(tab + tasks.get(index).toString());
    }

    public static void printNumTasks() {
        System.out.println(tab + "Now you have " + numTasks + " tasks in the list.");
    }

    public static void printBye() {
        System.out.println(tab + "Goodbye, can't wait to see you again!");
        System.out.println(line);
    }

    public static void printEmptyDescError(String task) {
        System.out.println(tab + "Oops! Description of " + task + " cannot be empty.");
    }

    public static void printIdkError() {
        System.out.println(tab + "I'm sorry, I'm not sure what that means.");
    }

    private static void loadFileContents(String pathString) throws FileNotFoundException {
        File file = new File(pathString);
        Scanner scanFile = new Scanner(file);

        while (scanFile.hasNext()) {
            String fileData = scanFile.nextLine();
            if (numTasks <= 0) {
                stringBufferOfData.append(fileData);
            } else {
                stringBufferOfData.append("\n").append(fileData);
            }

            String[] dataArr = fileData.split(" \\| ", 4);
            String taskType = dataArr[0];
            String isDone = dataArr[1];
            String desc = dataArr[2];

            switch (taskType) {
                case "T":
                    ToDos newTodo = new ToDos(desc, isDone.equals("1"));
                    tasks.add(newTodo);
                    numTasks++;
                    break;
                case "D":
                    LocalDate by = LocalDate.parse(dataArr[3]);
                    Deadlines newDeadline = new Deadlines(desc, by, isDone.equals("1"));
                    tasks.add(newDeadline);
                    numTasks++;
                    break;
                case "E":
                    LocalDate at = LocalDate.parse(dataArr[3]);
                    Events newEvent = new Events(desc, at, isDone.equals("1"));
                    tasks.add(newEvent);
                    numTasks++;
                    break;
            }
        }
    }

    private static void addToFile(String pathString, String data) throws IOException {
        FileWriter fw;
        if (numTasks <= 0) {
            fw = new FileWriter(pathString);
            fw.write(data);
            stringBufferOfData.append(data);
        } else {
            fw = new FileWriter(pathString, true);
            fw.write(System.lineSeparator() + data);
            stringBufferOfData.append("\n").append(data);
        }

        fw.close();
    }

    private static void modifyFile(String before, String after) throws IOException {
        int startIndex = stringBufferOfData.indexOf(before);
        int endIndex = startIndex + before.length();
        stringBufferOfData.replace(startIndex, endIndex, after);

        FileWriter fw = new FileWriter(pathString);
        fw.write(stringBufferOfData.toString());
        fw.close();
    }

    private static void deleteFromFile(String data) throws IOException {
        int startIndex = stringBufferOfData.indexOf(data);
        int endIndex = startIndex + data.length() + 1;
        stringBufferOfData.delete(startIndex, endIndex);

        FileWriter fw = new FileWriter(pathString);
        fw.write(stringBufferOfData.toString());
        fw.close();
    }
}
