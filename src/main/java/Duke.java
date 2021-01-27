import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");//only take in lines and not by whitespace, coz have one case where " " keeps the sc running to the next
        String relPath = "./src/main/java/data/All Tasks.txt";//for runtest.sh put .. coz the path for that is diff
        // compared to this

        try {
            //File f = new File("./");
            //System.out.println(f.getAbsolutePath());//to get the path to see which path java is looking
            ArrayList<Task> prevTasks = FileAccessor.ReadFromTasks(relPath, new ArrayList<Task>());
            TaskList.setList(prevTasks);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            //System.out.println("EXCEPTION");
            try {
                Files.createDirectory(Paths.get("./src/main/java/data/"));
                TaskList.setList(new ArrayList<Task>());
            } catch (IOException e1){}//shld just be ioexception, shldnt come to this catch block
            //File f = new File(relPath); //no need to create file here will get auto created when writing
        }

        //String line = "____________________________________________"+
        //        "________________\n";
        System.out.println(Ui.intro());

        while(sc.hasNext()) {
            String str = sc.nextLine();
            str = Parser.trimWhiteSpaces(str);
            if (str.equals("bye")) {
                break;
            } else if(str.length()==0) {//if just enter spaces
                System.out.println(Ui.onlySpaces());
            } else if (str.equals("list")) {
                try {
                    System.out.println(TaskList.printList());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    String[] split = Parser.firstAndRest(str);
                    if (split[0].equals("done")) {
                        try {
                            int num = Parser.makeToInt(split[1]);
                            Task done = TaskList.doneTask(num - 1);
                            System.out.println(Ui.doneTask(done));
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());//rmb do for parse
                        }
                    } else if (split[0].equals("delete")) {
                        try {
                            int num = Parser.makeToInt(split[1]);
                            Task del = TaskList.deleteTask(num - 1);
                            System.out.println(Ui.deleteTask(del, TaskList.listSize()));
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());//rmb do for parse
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
                        System.out.println(Ui.addTask(task, TaskList.listSize()));
                    } else {
                        throw new IllegalArgumentException();
                    }

                    try {
                        FileAccessor.WriteToTasks(relPath, TaskList.getList());
                    } catch (IOException e) {
                        System.out.println(Ui.unableSave());
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(Ui.illegalArgExc());
                } catch (DateTimeParseException e) {
                    System.out.println(Ui.dateTimeParseExc());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());//e.getmessage as for the diff array
                    // exception
                    // print their specific msg
                }
            }
        }

        System.out.println(Ui.bye());
    }
}
