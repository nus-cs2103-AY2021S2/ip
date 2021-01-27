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
        ArrayList<Task> tasks = new ArrayList<>();
        String relPath = "./src/main/java/data/All Tasks.txt";//for runtest.sh put .. coz the path for that is diff
        // compared to this

        try {
            //File f = new File("./");
            //System.out.println(f.getAbsolutePath());//to get the path to see which path java is looking
            FileAccessor.ReadFromTasks(relPath, tasks);

        } catch (FileNotFoundException | IllegalArgumentException e) {
            //System.out.println("EXCEPTION");
            try {
                Files.createDirectory(Paths.get("./src/main/java/data/"));
            } catch (IOException e1){}//shld just be ioexception
            //File f = new File(relPath); //no need to create file here will get auto created when writing
        }

        String line = "____________________________________________"+
                "________________\n";
        System.out.println(Ui.intro());

        while(sc.hasNext()) {
            String str = sc.nextLine();
            str = str.trim();
            if (str.equals("bye")) {
                break;
            } else if(str.length()==0) {//if just enter spaces
                System.out.println(Ui.onlySpaces());
            } else if (str.equals("list")) {
                try {
                    System.out.println(Ui.printList(tasks));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
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
                            Task done = null;
                            if(num-1<tasks.size() && num-1>=0) {
                                done = tasks.get(num-1);
                                tasks.get(num - 1).doneTask();//later add exception for the tasklist class
                                System.out.println(Ui.doneTask(done));
                            }
                            ///System.out.println(Ui.doneTask(done));
                            //System.out.println(Ui.doneTask(tasks, num-1));
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            if (tasks.size()==0){
                            //System.out.println(e.getMessage());//rmb do for parse
                                System.out.println(line+" No tasks to complete!\n"+line);
                            } else {
                                //System.out.println(e.getMessage());//rmb do for parse
                                System.out.println(line + " Enter 'done' followed by a number between " +
                                        "1 and " + tasks.size() + "\n" + line);
                            }
                        }
                    } else if (split[0].equals("delete")) {
                        try {
                            int num = Integer.parseInt(split[1]);
                            Task toRem = null;
                            if(num-1<tasks.size() && num-1>=0) {
                                toRem = tasks.get(num-1);
                                tasks.remove(num-1);//later add exception for the tasklist class, and return the deleted
                                System.out.println(Ui.deleteTask(toRem, tasks.size()));
                            }
                            //System.out.println(Ui.deleteTask(toRem, tasks.size()));
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            if (tasks.size()==0){
                                //System.out.println(e.getMessage());//rmb do for parse
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
                        System.out.println(Ui.addTask(tasks));
                    } else {
                        throw new IllegalArgumentException();
                    }

                    try {
                        FileAccessor.WriteToTasks(relPath, tasks);
                    } catch (IOException e) {
                        System.out.println("Unable to save to hard drive");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(Ui.IllegalArgExc());
                } catch (DateTimeParseException e) {
                    System.out.println(line+" Enter date and time in this format yyyy-mm-dd hh:mm\n"+line);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + e.getMessage() + line);//e.getmessage as for the diff array exception
                    // print their specific msg
                }
            }
        }

        System.out.println(Ui.bye());
    }
}
