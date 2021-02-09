import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;



public class Duke {

    private static void SaveTasks(ArrayList<Task> list, File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task temp : list) {
            if (temp instanceof Deadline) {
                fw.write("D|" + temp.getStatusIcon() + temp.getDescription() + "|"+ ((Deadline) temp).getBy() + "\n");
            } else if (temp instanceof Event) {
                fw.write("E|" + temp.getStatusIcon() + "|" + temp.getDescription() + "|" +((Event) temp).getDatetime() + "\n");
            } else {
                fw.write("T|" + temp.getStatusIcon() + "|" + temp.getDescription());
            }
        }
        fw.close();
    }

    private static void LoadTasks(ArrayList<Task> list, File f) throws IOException {
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            s = s.trim();

            String[] sArr = s.split("\\|", 4);

            if (sArr[0].trim().equals("D")) {
                Deadline tempD = new Deadline(sArr[2].trim(), sArr[3].trim());
                if (sArr[1].trim().equals("done")) {
                    tempD.isDone = true;
                } else if (sArr[1].trim().equals("not done")){
                    tempD.isDone = false;
                }
                list.add(tempD);
            } else if (sArr[0].trim().equals("E")) {
                Event tempE = new Event(sArr[2].trim(), sArr[3].trim());
                if (sArr[1].trim().equals("done")) {
                    tempE.isDone = true;
                } else if (sArr[1].trim().equals("not done")) {
                    tempE.isDone = false;
                }
                list.add(tempE);
            } else {
                ToDo tempT = new ToDo(sArr[2].trim());
                if (sArr[1].trim().equals("done")) {
                    tempT.isDone = true;
                } else if (sArr[1].trim().equals("not done")) {
                    tempT.isDone = false;
                }
                list.add(tempT);
            }
        }
    }

    public static void main(String[] args) throws DukeException{
        File file = new File("src/main/data/DukeData.txt");
        Path path = Paths.get("src/main/data/DukeData.txt");
        ArrayList<Task> list = new ArrayList<>();

        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            LoadTasks(list, file);
        } catch (Exception e) {
            e.getStackTrace();
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            String word = sc.nextLine();
            if (word.equals("bye")) {
                System.out.println("Bye, hope to see you again!");
                break;
            } else if (word.equals("list")) {
                int size = list.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= size; i++) {
                    System.out.println(i + ". " + list.get(i - 1).toString());
                }
            } else if (word.contains("done")) {
                String[] strArray = word.split(" ");
                int value = Integer.parseInt(strArray[1]);
                Task complete = list.get(value - 1);
                complete.markAsDone();
                System.out.println(" Nice! I've marked this task as done: ");
                System.out.println(complete.toString());
            } else if (word.contains("delete")) {
                String[] arr = word.split(" ");
                int value = Integer.parseInt(arr[1]);
                Task remove = list.get(value - 1);
                list.remove(value - 1);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(remove.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
            } else if ((word.contains("todo")) || (word.contains("event")) || (word.contains("deadline"))) {
                if (word.contains("todo")) {
                    String save = word.replaceAll("todo","");
                    ToDo t = new ToDo(save);
                    if (save.equals("")) {
                        Thread.setDefaultUncaughtExceptionHandler((u, e) -> System.err.println(e.getMessage()));
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    list.add(t);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(t.toString());
                } else if (word.contains("event")) {
                    String[] info = word.split("/at");
                    info[0] = info[0].replaceAll("event","");
                    Event e = new Event(info[0],info[1]);
                    list.add(e);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(e.toString());
                } else if (word.contains("deadline")) {
                    String[] input = word.split("/by");
                    input[0] = input[0].replaceAll("deadline", "");
                    Deadline d = new Deadline(input[0], input[1]);
                    list.add(d);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(d.toString());
                }
                System.out.println("Now you have " + list.size() + " tasks in the list");
            } else {
                Thread.setDefaultUncaughtExceptionHandler((u, e) -> System.err.println(e.getMessage()));
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        try {
            SaveTasks(list, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
