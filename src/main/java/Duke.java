import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Duke {

    private static void saveTasks(ArrayList<Task> list, File f) throws IOException {
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

    private static void loadTasks(ArrayList<Task> list, File f) throws IOException {
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            s = s.trim();

            String[] strArray = s.split("\\|", 4);

            if (strArray[0].trim().equals("D")) {
                LocalDate date =LocalDate.parse(strArray[3].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Deadline deadline = new Deadline(strArray[2].trim(), date);
                if (strArray[1].trim().equals("done")) {
                    deadline.isDone = true;
                } else if (strArray[1].trim().equals("not done")){
                    deadline.isDone = false;
                }
                list.add(deadline);
            } else if (strArray[0].trim().equals("E")) {
                Event event = new Event(strArray[2].trim(), strArray[3].trim());
                if (strArray[1].trim().equals("done")) {
                    event.isDone = true;
                } else if (strArray[1].trim().equals("not done")) {
                    event.isDone = false;
                }
                list.add(event);
            } else {
                ToDo todo = new ToDo(strArray[2].trim());
                if (strArray[1].trim().equals("done")) {
                    todo.isDone = true;
                } else if (strArray[1].trim().equals("not done")) {
                    todo.isDone = false;
                }
                list.add(todo);
            }
        }
    }



    public static void main(String[] args) throws DukeException{
        File file = new File("data/DukeData.txt");
        File dir = new File("data/");
        ArrayList<Task> list = new ArrayList<>();

        try {
            if(!dir.exists()) {
                dir.mkdirs();
            }
            if(!file.exists()) {
                file.createNewFile();
            }
            loadTasks(list, file);
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
                    String[] input = word.split("/by ");
                    input[0] = input[0].replaceAll("deadline", "");
                    LocalDate date =LocalDate.parse(input[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Deadline d = new Deadline(input[0], date);
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
            saveTasks(list, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
