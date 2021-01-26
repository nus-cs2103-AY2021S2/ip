import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");//only take in lines and not by whitespace, coz have one case where " " keeps the sc running to the next
        ArrayList<Task> tasks = new ArrayList<>();
        String relPath = "./src/main/java/data/All Tasks.txt";

        try {
            //File f = new File("./");
            //System.out.println(f.getAbsolutePath());//to get the path to see which path java is looking
            FileAccessor.ReadFromTasks(relPath, tasks);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.out.println("EXCEPTION");
            //File f = new File("./src/main/java/data/All Task.txt");
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________"+
                "________________\n";
        System.out.println(line + " Hey there! I'm Duke\n" +
                " How can I help you?\n" + line);

        while(sc.hasNext()) {
            String str = sc.nextLine();
            str = str.trim();
            if (str.equals("bye")) {
                break;
            } else if(str.length()==0) {//if just enter spaces
                System.out.println(line + " Please enter a task\n"
                        + line);
            } else if (str.equals("list")) {
                System.out.print(line);
                if (tasks.size() > 0) {
                    System.out.println(" Here are the tasks in your list:");
                    for (int j = 1; j <= tasks.size(); j++) {
                        System.out.println(" " + j + ". " + tasks.get(j - 1));
                    }
                } else {
                    System.out.println(" No tasks so far!");
                }
                System.out.println(line);
            } else {
                try {
                    String[] split = str.split(" ");
                    String rest = "";
                    for (int i = 1; i < split.length; i++) {
                        rest = rest + " " + split[i];
                    }
                    if (split[0].equals("done")) {
                        try {
                            int num = Integer.parseInt(split[1]);
                            System.out.println(line + tasks.get(num - 1).doneTask()
                                    + "\n" + line);
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            if (tasks.size()==0){
                                System.out.println(line+" No tasks to complete!\n"+line);
                            } else {
                                System.out.println(line + " Enter 'done' followed by a number between " +
                                        "1 and " + tasks.size() + "\n" + line);
                            }
                        }
                    } else if (split[0].equals("delete")) {
                        try {
                            int num = Integer.parseInt(split[1]);
                            System.out.println(line + " I've removed the following task:\n"
                                    + " " +tasks.get(num - 1));
                            tasks.remove(num-1);
                            System.out.println(" You now have "+ tasks.size() + " tasks" +
                                    " in the list\n" + line);
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            if (tasks.size()==0){
                                System.out.println(line+" No tasks to delete!\n" + line);
                            } else {
                                System.out.println(line + " Enter 'delete' followed by a number between " +
                                        "1 and " + tasks.size() + "\n" + line);
                            }
                        }
                    } else if (split[0].equals("todo") || split[0].equals("deadline")
                            || split[0].equals("event")) {
                        if (split[0].equals("todo")) {
                            tasks.add(new Todo(rest));
                        } else if (split[0].equals("deadline")) {
                            tasks.add(new Deadline(rest));
                        } else {
                            tasks.add(new Event(rest));
                        }
                        System.out.println(line + " Got it. I've added this task:\n" +
                                " " + tasks.get(tasks.size() - 1) + "\n" + " Now you have "
                                + tasks.size() + " tasks in the list\n" + line);
                    } else {
                        throw new IllegalArgumentException();
                    }

                    try {
                        FileAccessor.WriteToTasks(relPath, tasks);
                    } catch (IOException e) {
                        System.out.println("Unable to save to hard drive");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(line + " Please enter 'todo (your task)', " +
                            "or 'deadline (your task) / (deadline date timing)',\n or " +
                            "'event (event name) / (event date timing' to add tasks.\n " +
                            "To see your tasks enter 'list'.\n To complete a task enter " +
                            "'done (number of the task in the list)'.\n And to close Duke " +
                            "enter 'bye'.\n"+ line);
                }
            }
        }

        System.out.println(line + " Bye, see you again!\n"
                + line);
    }
}
