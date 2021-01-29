import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class that runs the entire Duke application
 */
public class Duke {

    private static final String FOLDER_PATH = "../src/main/java/data/";
    private static final String FILE_NAME = "All Tasks.txt";

    /**
     * Runs the Duke application
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        //only take in lines and not by whitespace, coz have one case where " " keeps the sc running to the next
        //String relPath = "./src/main/java/data/All Tasks.txt";//for runtest.sh put .. coz the path for that is diff
        // compared to this

        try {
            //File f = new File("./");
            //System.out.println(f.getAbsolutePath());//to get the path to see which path java is looking
            ArrayList<Task> prevTasks = FileAccessor.readFromTasks(FOLDER_PATH + FILE_NAME, new ArrayList<Task>());
            TaskList.setList(prevTasks);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            //System.out.println("EXCEPTION");
            try {
                Files.createDirectory(Paths.get(FOLDER_PATH));
                TaskList.setList(new ArrayList<Task>());
            } catch (IOException e1){}
            //shld just be ioexception, shldnt come to this catch block
            //File f = new File(relPath); //no need to create file here will get auto created when writing
        }

        System.out.println(Ui.introduce());

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            input = Parser.trimWhiteSpaces(input);
            if (input.equals("bye")) {
                break;
            } else if (input.length() == 0) {
                //if just enter spaces
                System.out.println(Ui.informOnlySpaces());
            } else if (input.equals("list")) {
                try {
                    System.out.println(TaskList.printList());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    String[] split = Parser.splitFirstAndRest(input);
                    if (split[0].equals("done")) {
                        try {
                            int num = Parser.makeToInt(split[1]);
                            Task done = TaskList.doneTask(num - 1);
                            System.out.println(Ui.doneTask(done));
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (split[0].equals("delete")) {
                        try {
                            int num = Parser.makeToInt(split[1]);
                            Task del = TaskList.deleteTask(num - 1);
                            System.out.println(Ui.deleteTask(del, TaskList.getListSize()));
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (split[0].equals("todo") || split[0].equals("deadline")
                            || split[0].equals("event")) {
                        Task task;
                        if (split[0].equals("todo")) {
                            task = new Todo(split[1]);
                        } else if (split[0].equals("deadline")) {
                            task = new Deadline(split[1]);
                        } else {
                            task = new Event(split[1]);
                        }
                        TaskList.addTask(task);
                        System.out.println(Ui.addTask(task, TaskList.getListSize()));
                    } else {
                        throw new IllegalArgumentException();
                    }

                    try {
                        FileAccessor.writeToTasks(FOLDER_PATH + FILE_NAME, TaskList.getList());
                    } catch (IOException e) {
                        System.out.println(Ui.informUnableSave());
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(Ui.informIllegalArgExc());
                } catch (DateTimeParseException e) {
                    System.out.println(Ui.informDateTimeParseExc());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                    //print error specific messages
                }
            }
        }
        System.out.println(Ui.exit());
    }

    public static void main(String[] args) {
        Duke.run();
    }
}
