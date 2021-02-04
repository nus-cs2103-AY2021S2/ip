import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static final String filePath = System.getProperty("user.dir") + "/data/duke.txt";
    public static void main(String[] args) throws EmptyArgumentException, InvalidCommandException, IOException {
        String logo =
                " __        _        \n"
                        + "|  _ \\ _   | | __ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| || | || |   <  __/\n"
                        + "|_/ \\,||\\\\___|\n";
        load();
        System.out.println("Hi Im Duke, how may I help you?");
        while(true) {
            try {
                String cmd = sc.next();
                if (cmd.equals("bye")) {
                    byeUser(logo);
                    save(tasks);
                    break;
                } else if (cmd.equals("list")) {
                    listItems();
                } else if (cmd.equals("done")) {
                    int itemNo = sc.nextInt();
                    markItemAsDone(itemNo);
                } else if (cmd.equals("delete")) {
                    int itemNo = sc.nextInt();
                    deleteItem(itemNo);
                } else {
                    String typeOfEvent = cmd;
                    String eventDescription = sc.nextLine();
                    addItem(typeOfEvent, eventDescription);
                }
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (EmptyArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for(Task task : tasks) {
            writer.write(convertToFileFormat(task)+'\n');
        }
        writer.close();
    }
    private static Task convertToTaskFormat(String line) throws EmptyArgumentException {
        String[] arr = line.split(Pattern.quote(" | "));
        Task task;
        if(arr[0].charAt(0) == 'T') task = new ToDos(arr[2].trim());
        else if(arr[0].charAt(0) == 'E') task = new Event(arr[2].trim());
        else task = new Deadline(arr[2]);
        if(arr[1].trim().charAt(0) == '1') task.isCompleted = true;
        else task.isCompleted = false;
        return task;
    }
    private static String convertToFileFormat(Task task) {
        String fileString = "";
        if(task instanceof ToDos) fileString += "T | ";
        else if(task instanceof Deadline) fileString += "D | ";
        else if(task instanceof Event) fileString += "E | ";
        if(task.isCompleted) fileString += "1 | ";
        else fileString += "0 | ";
        fileString += task.description;
        return fileString;
    }
    private static void load() throws IOException, EmptyArgumentException {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                String line = sc.nextLine();
                Task task = convertToTaskFormat(line);
                tasks.add(task);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }



    private static void addItem(String typeOfEvent, String eventDescription) throws EmptyArgumentException, InvalidCommandException {
        if(typeOfEvent.equals("todo")) {
            tasks.add(new ToDos(eventDescription));
        }
        else {
            String[] _eventDescription = eventDescription.split("/");
            StringBuilder firstPart = new StringBuilder(_eventDescription[0]).append(" ");
            StringBuilder secondPart = new StringBuilder(_eventDescription[1]);
            secondPart.insert(0, '(');
            secondPart.insert(3, ':');
            secondPart.append(')');
            firstPart.append(secondPart);
            if (typeOfEvent.equals("deadline")) tasks.add(new Deadline(firstPart.toString()));
            else if (typeOfEvent.equals("event")) tasks.add(new Event(firstPart.toString()));
        }
        System.out.print("added: ");
        System.out.println(tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void deleteItem(int itemNo) {
        Task toBeDeleted = tasks.get(itemNo-1);
        tasks.remove(itemNo-1);
        System.out.println("Noted.I have removed this task:");
        System.out.print("  ");
        System.out.println(toBeDeleted);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void markItemAsDone(int itemNo) {
        tasks.get(itemNo-1).isCompleted = true;
        System.out.println("Nice, I have marked this task as done!");
        System.out.print("  ");
        System.out.print(tasks.get(itemNo-1));
    }

    private static void listItems() {
        System.out.println("here are your tasks:");
        for(int itemNo=1;itemNo<=tasks.size();itemNo++) {
            System.out.print("  ");
            System.out.println("" + itemNo + ". " + tasks.get(itemNo-1));
        }
    }

    private static void byeUser(String logo) {
        System.out.println("Bye bye, have a nice day! Thanks for using " + logo);
    }

}