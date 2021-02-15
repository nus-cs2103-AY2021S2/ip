import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    public TaskList() {}

    /**
     * Returns and prints the specific Task type.
     * Adds the task into the list if the Task is either "Todo", "Deadline" or "Event".
     * if the command given is "done", then the task will be marked as done but stay in the list.
     * if the command given is "delete", then the task wil be deleted and removed from the list.
     * if the command given is "find", then the task will proceed to scan through the list.
     * and return the respective tasks that have the matching keyword.
     * @param input the task to be added/deleted/edited in the list.
     * @param sc the scanner that was used to obtain the input.
     *           and will thus be used to obtain the remaining lines.
     */

    public static void createTask(String input, Scanner sc, ArrayList<Task> list) {
        Storage store = new Storage();
        String home = store.getHome();
        String file = store.getDefaultFilePath();
        if (input.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                int count = i + 1;
                Task a = list.get(i);
                System.out.println(count + ". " + a);
            }
        } else if (input.equals("done")) {
            int tag = sc.nextInt() - 1;
            Task d = list.get(tag);
            d.markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(d + "\n");

            String temp = home + File.separator + "temp.txt";
            //create temp file
            File tempf = new File(temp);
            //copy contents of old file to temp file
            try {
                Files.copy(Paths.get(file), Paths.get(temp));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //delete contents of old file
            try {
                new FileWriter(file, false).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scanner sf = null;
            try {
                sf = new Scanner(tempf);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < list.size(); i++) {
                Task t = list.get(i);
                if (i != tag) {
                    try {
                        store.appendToFile(file, sf.nextLine() + "\n");
                    } catch (IOException e){
                        System.out.println("OOPS!!! " + e.getMessage());
                    }
                } else {
                    try {
                        t.markAsDone();
                        Storage.appendToFile(file, "(DONE)" + sf.nextLine() + "\n");
                    } catch (IOException e){
                        System.out.println("OOPS!!! " + e.getMessage());
                    }
                }
            }
            //delete temp file
            try {
                Files.delete(Paths.get(temp));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (input.equals("delete")) {
            int tag = sc.nextInt() - 1;
            int len = list.size() - 1;
            System.out.println("Noted. I've removed this task:");
            System.out.println("\t" + list.get(tag));
            System.out.println("Now you have " + len + " tasks in the list.\n");

            String temp = home + File.separator + "temp.txt";
            //create temp file
            File tempf = new File(temp);
            //copy contents of old file to temp file
            try {
                Files.copy(Paths.get(file), Paths.get(temp));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //delete contents of old file
            try {
                new FileWriter(file, false).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scanner sf = null;
            try {
                sf = new Scanner(tempf);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i <= len; i++) {
                if (i != tag) {
                    try {
                        Storage.appendToFile(file,  sf.nextLine() + "\n");
                    } catch (IOException e){
                        System.out.println("OOPS!!! " + e.getMessage());
                    }
                }
            }
            //delete temp file
            try {
                Files.delete(Paths.get(temp));
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.remove(tag);
        } else if (input.equals("todo")) {
            String task = sc.nextLine();
            try {
                Todo td = Todo.makeTodo(task);
                list.add(td);
                int len = list.size();
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + td);
                System.out.println("Now you have " + len + " tasks in the list.\n");

                try {
                    Storage.appendToFile(file, "Todo:" + " 0" + task + "\n");
                } catch (IOException e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                }
            } catch (DukeException ex){
                System.err.println("OOPS!!! The description of a todo cannot be empty.\n");
            }
        } else if (input.equals("deadline")) {
            String line = sc.nextLine();
            try {
                Deadline dl = Deadline.makeDeadline(line);
                list.add(dl);
                int len = list.size();
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + dl);
                System.out.println("Now you have " + len + " tasks in the list.");
                try {
                    Storage.appendToFile(file,  "Deadline:" + " 0"+ line + "\n");
                } catch (IOException e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                }
            } catch (DukeException ex){
                System.err.println("OOPS!!! The description of a deadline cannot be empty.\n");
            }
        } else if (input.equals("event")) {
            String line = sc.nextLine();
            try {
                Event event = Event.makeEvent(line);
                list.add(event);
                int len = list.size();
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + event);
                System.out.println("Now you have " + len + " tasks in the list.");
                try {
                    Storage.writeToFile(file, "Event:" + " 0" + line + "\n");
                } catch (IOException e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                }
            } catch (DukeException ex) {
                System.err.println("OOPS!!! The description of an event cannot be empty.\n");
            }
        } else if (input.equals("find")) {
            String keyword = sc.next();
            int matchCount = 0;
            ArrayList<Integer> num = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String taskDes = list.get(i).des;
                if (taskDes.toLowerCase().indexOf(keyword.toLowerCase()) != -1) {
                    num.add(i);
                    matchCount++;
                }
            }
            if (matchCount != 0) {
                System.out.println("Here are the matching tasks in your list:");
                for (int j = 0; j < num.size(); j++) {
                    System.out.println(list.get(num.get(j)));
                }
            } else {
                System.out.println("There are no tasks matching this keyword :-(");
            }
        } else {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    public static String guiTask(String[] input, ArrayList<Task> list) {
        Storage store = new Storage();
        String home = store.getHome();
        String file = store.getDefaultFilePath();
        if (input[0].equals("list")) {
            String output = "Here are the tasks in your list\n";
            for (int i = 0; i < list.size(); i++) {
                int count = i + 1;
                Task a = list.get(i);
                output = output + count + ". " + a + "\n";
            }
            return output;
        } else if (input[0].equals("done")) {
            int tag = Integer.parseInt(input[1]) - 1;
            Task d = list.get(tag);
            d.markAsDone();
            String output = "Nice! I've marked this task as done: \n";
            output = output + d + "\n";

            String temp = home + File.separator + "temp.txt";
            //create temp file
            File tempf = new File(temp);
            //copy contents of old file to temp file
            try {
                Files.copy(Paths.get(file), Paths.get(temp));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //delete contents of old file
            try {
                new FileWriter(file, false).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scanner sf = null;
            try {
                sf = new Scanner(tempf);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < list.size(); i++) {
                Task t = list.get(i);
                if (i != tag) {
                    try {
                        store.appendToFile(file, sf.nextLine() + "\n");
                    } catch (IOException e){
                        System.out.println("OOPS!!! " + e.getMessage());
                    }
                } else {
                    try {
                        t.markAsDone();
                        Storage.appendToFile(file, "(DONE)" + sf.nextLine() + "\n");
                    } catch (IOException e){
                        System.out.println("OOPS!!! " + e.getMessage());
                    }
                }
            }
            //delete temp file
            try {
                Files.delete(Paths.get(temp));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return output;
        } else if (input[0].equals("delete")) {
            int tag = Integer.parseInt(input[1]) - 1;
            int len = list.size() - 1;
            String output = "Noted. I've removed this task:\n";
            output = output + "\t" + list.get(tag) + "\n";
            output = output + "Now you have " + len + "tasks in the list. \n";
            list.remove(tag);

            String temp = home + File.separator + "temp.txt";
            //create temp file
            File tempf = new File(temp);
            //copy contents of old file to temp file
            try {
                Files.copy(Paths.get(file), Paths.get(temp));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //delete contents of old file
            try {
                new FileWriter(file, false).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scanner sf = null;
            try {
                sf = new Scanner(tempf);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i <= len; i++) {
                if (i != tag) {
                    try {
                        Storage.appendToFile(file,  sf.nextLine() + "\n");
                    } catch (IOException e){
                        System.out.println("OOPS!!! " + e.getMessage());
                    }
                }
            }
            //delete temp file
            try {
                Files.delete(Paths.get(temp));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return output;
        } else if (input[0].equals("todo")) {
            String task = "";
            if (input.length == 1) {
                return "OOPS!!! The description of a todo cannot be empty.\n";
            } else {
                for (int i = 1; i < input.length; i++) {
                    task = task + input[i] + " ";
                }
            }
            try {
                Todo td = Todo.makeTodo(task);
                list.add(td);
                int len = list.size();
                String output = "Got it. I've added this task: \n";
                output = output + "\t" + td + "\n";
                output = output + "Now you have " + len + " tasks in the list.\n";
                try {
                    Storage.appendToFile(file, "Todo:" + " 0" + task + "\n");
                } catch (IOException e) {
                    return "OOPS!!! " + e.getMessage();
                }
                return output;
            } catch (DukeException ex){
                return "OOPS!!! The description of a todo cannot be empty.\n";
            }
        } else if (input[0].equals("deadline")) {
            String line = "";
            if (input.length == 1) {
                return "OOPS!!! The description of a deadline cannot be empty.\n";
            } else {
                for (int i = 1; i < input.length; i++) {
                    line = line + input[i] + " ";
                }
            }
            try {
                Deadline dl = Deadline.makeDeadline(line);
                list.add(dl);
                int len = list.size();
                String output = "Got it. I've added this task:\n";
                output = output + "\t" + dl + "\n";
                output = output+ "Now you have " + len + " tasks in the list.\n";
                try {
                    Storage.appendToFile(file,  "Deadline:" + " 0"+ line + "\n");
                } catch (IOException e) {
                    return "OOPS!!! " + e.getMessage();
                }
                return output;
            } catch (DukeException ex){
                return "OOPS!!! The description of a deadline cannot be empty.\n";
            }
        } else if (input[0].equals("event")) {
            String line = "";
            if (input.length == 1) {
                return "OOPS!!! The description of an event cannot be empty.\n";
            } else {
                for (int i = 1; i < input.length; i++) {
                    line = line + input[i] + " ";
                }
            }
            try {
                Event event = Event.makeEvent(line);
                list.add(event);
                int len = list.size();
                String output = "Got it. I've added this task:\n";
                output = output + "\t" + event + "\n";
                output = output+ "Now you have " + len + " tasks in the list.\n";
                try {
                    Storage.writeToFile(file, "Event:" + " 0" + line + "\n");
                } catch (IOException e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                }
                return output;
            } catch (DukeException ex) {
                return "OOPS!!! The description of an event cannot be empty.\n";
            }
        } else if (input[0].equals("find")) {
            String keyword = input[1];
            int matchCount = 0;
            ArrayList<Integer> num = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String taskDes = list.get(i).des;
                if (taskDes.toLowerCase().indexOf(keyword.toLowerCase()) != -1) {
                    num.add(i);
                    matchCount++;
                }
            }
            if (matchCount != 0) {
                String output = "Here are the matching tasks in your list:\n";
                for (int j = 0; j < num.size(); j++) {
                    output = output + list.get(num.get(j)) + "\n";
                }
                return output;
            } else {
                return "There are no tasks matching this keyword :-(\n";
            }
        } else {
            assert input.length == 1 : "Oops!! What are you trying to say?";
            return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
    }
}
