import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        start();
    }
    
    public static void start() {
        Scanner sc = new Scanner(System.in);
        String request = "";
        printFormatted("Hello! I'm Duke\nWhat can I do for you?");

        LinkedList<Task> lst = new LinkedList<>();
        try {
            load(lst);
        } catch (DukeException ex) {
            printFormatted(ex.getMessage());
        }

        while (!request.equals("bye")) {
            request = sc.nextLine();
            String[] tk = request.split(" ");
            request = tk[0];
            String taskname = "";
            // System.out.println(request);
            for (int i = 1; i < tk.length; i++) {
                taskname += tk[i];
                if (i < tk.length - 1) {
                    taskname += " ";
                }
            }

            if (request.equals("bye")) {
                printFormatted("Bye. Hope to see you again soon!");
                break;
            } else if (request.equals("list")) {
                printList(lst);
            } else if (request.equals("done")) {
                try {
                    int taskNo = Integer.parseInt(taskname);
                    markDone(lst, taskNo);
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                } catch (NumberFormatException ex) {
                    printFormatted("Please enter integer values..");
                }
            } else if (request.equals("delete")) {
                try {
                    int taskNo = Integer.parseInt(taskname);
                    delete(lst, taskNo);
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                } catch (NumberFormatException ex) {
                    printFormatted("Please enter integer values..");
                }
            } else if (request.equals("todo")) {
                try {
                    addTask(lst, taskname, request, "", "");
                    printAdded(lst);
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                }
            } else if (request.equals("deadline")) {
                try {
                    String[] deadStr = formatCommand(taskname);
                    addTask(lst, deadStr[0], request, deadStr[1], deadStr[2]);
                    printAdded(lst);
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                }
            } else if (request.equals("event")) {
                try {
                    String[] eventStr = formatCommand(taskname);
                    addTask(lst, eventStr[0], request, eventStr[1], eventStr[2]);
                    printAdded(lst);
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                }
            } else {
                try {
                    throwDK();
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                }
            }
        }
    }
    
    public static void delete(List<Task> lst, int taskNo) throws DukeException {
        if (taskNo >= lst.size() || taskNo < 0) {
            throw new DukeException(
                    String.format("Tried to delete nothing ????. (Size: %d | Task No: %d)", lst.size(), taskNo));
        }
        Task task = lst.remove(taskNo - 1);
        String msg = "Noted. I've removed this task:\n" +
                "  " + task +
                "\n Now you have " + lst.size() + " tasks in the list.";
        save(lst);
        printFormatted(msg);
    }

    public static void addTodo(List<Task> lst, String taskname) throws DukeException {
        if (taskname.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        lst.add(new Task("T", taskname));
        save(lst);
    }

    public static void addTask(List<Task> lst, String taskname, String type, String date, String preposition) throws DukeException {
        if (taskname.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        }
        if (type.equals("todo")) {
            lst.add(new Task("T", taskname));
        } else if (type.equals("event")) {
            lst.add(new Task("E", taskname, date, preposition));
        } else if (type.equals("deadline")) {
            lst.add(new Task("D", taskname, date, preposition));
        } else {
            throw new DukeException("☹ OOPS!!! Tried to add wrong task type!");
        }
        save(lst);
    }

    public static void throwDK() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static String[] formatCommand(String n) throws DukeException {
        try {
            String[] arr = new String[3];
            arr[0] = n.split("/")[0].split(" ")[0];
            arr[1] = n.split("/")[1].substring(n.split("/")[1].split(" ")[0].length() + 1, n.split("/")[1].length());
            arr[2] = n.split("/")[1].split(" ")[0];
            return arr;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The format you have entered is wrong.");
        }
    }
    
    public static void printAdded(List<Task> lst) {
        String msg = "Got it. I've added this task:\n"
                + "  " + lst.get(lst.size() - 1)
                + "\nNow you have " + lst.size() + " tasks in the list.";
        printFormatted(msg);
    }

    public static void printFormatted(String msg) {
        String appendMsg = "____________________________________________________________\n"
                + msg
                + "\n____________________________________________________________";
        System.out.println(appendMsg);
    }

    public static void printList(List<Task> lst) {
        String msg = "Here are the tasks in your list:\n";
        for (int i = 1; i <= lst.size(); i++) {
            msg += i + ". " + lst.get(i - 1);
            if (i < lst.size()) {
                msg += "\n";
            }
        }
        printFormatted(msg);
    }

    public static void markDone(List<Task> lst, int taskNo) throws DukeException {
        if (taskNo > lst.size() || taskNo < 0) {
            throw new DukeException(
                    String.format("Tried to mark nothing ????. (Size: %d | Task No: %d)", lst.size(), taskNo));
        }
        lst.get(taskNo - 1).setDone();
        String msg = "Nice! I've marked this task as done:\n";
        msg += "  " + lst.get(taskNo - 1);
        save(lst);
        printFormatted(msg);
    }

    public static void save(List<Task> lst) {
        try {
            File saveFile = new File("./data/duke.txt");
            File directory = new File("./data/");

            if(!directory.exists()) {
                directory.mkdir();
            }

            FileWriter fw = new FileWriter(saveFile);
            for (int i = 0; i < lst.size(); i++) {
                Task task = lst.get(i);
                fw.write(task.toSaveFormat());
                fw.write("\n");
                fw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(List<Task> lst) throws DukeException {
        try {
            File saveFile = new File("./data/duke.txt");
            if (saveFile.exists()) {
                Scanner reader = new Scanner(saveFile);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] token = line.split(" \\| ");
                    Task task;
                    if (token[0].equals("T")) {
                        task = new Task(token[0], token[2]);
                    } else if (token[0].equals("E") || token[0].equals("D")) {
                        task = new Task(token[0], token[2], token[3], token[4]);
                    } else {
                        throw new DukeException("Save file is corrupted ): Will be creating a new file");
                    }
                    if (token[1].equals("1")) {
                        task.setDone();
                    }
                    lst.add(task);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
