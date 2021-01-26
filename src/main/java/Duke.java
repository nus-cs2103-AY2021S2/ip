import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    static final String INDENT = "        ";
    static final String BORDER = INDENT + "__________________________________________________";
    static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void print(String s) {
        String[] sSplit = s.split("\n");
        System.out.println(BORDER);
        for(String line: sSplit) {
            System.out.println(INDENT + line);
        }
        System.out.println(BORDER + "\n");
    }

    public static String getNumTasks() {
        return "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    public static void saveData() {
        try {
            FileWriter dataOut = new FileWriter("duke.txt");
            for (Task t : tasks) {
                String currLine = t.getSymbol() + "#" + t.getStatus() + "#" + t.getName();
                if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    currLine += "#" + d.getTime();
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    currLine += "#" + e.getTime();
                }
                currLine += "\n";
                dataOut.write(currLine);
            }
            dataOut.close();
        } catch (IOException e) {
            System.out.println("Cannot save tasks" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String logo = "      _       __  __  \n"
                    + "     | |     / _|/ _| \n"
                    + "     | | ___| |_| |_  \n"
                    + " _   | |/ _ \\  _|  _|\n"
                    + "| |__| |  __/ | | |   \n"
                    + " \\____/ \\___|_| |_| \n";
        print("Hello I'm\n" + logo + "\nWhat can I do for you?");

        try {
            File data = new File("duke.txt");
            Scanner dataIn = new Scanner(data);
            while (dataIn.hasNext()) {
                String[] line = dataIn.nextLine().split("#", 3);
                switch (line[0]) {
                case "T":
                    ToDo savedToDo = new ToDo(line[2]);
                    if (line[1].equals("X")) {
                        savedToDo.setDone();
                    }
                    tasks.add(savedToDo);
                    break;
                case "D":
                    Deadline savedDeadline = new Deadline(line[2].split("#")[0], line[2].split("#")[1]);
                    if (line[1].equals("X")) {
                        savedDeadline.setDone();
                    }
                    tasks.add(savedDeadline);
                    break;
                case "E":
                    Event savedEvent = new Event(line[2].split("#")[0], line[2].split("#")[1]);
                    if (line[1].equals("X")) {
                        savedEvent.setDone();
                    }
                    tasks.add(savedEvent);
                }
            }
        } catch(FileNotFoundException e){

        }


        Scanner sc = new Scanner(System.in);
        while(true) {
            String[] msg = sc.nextLine().split("/");
            String[] msg0 = msg[0].split(" ", 2);
            Command cmd;
            try {
                cmd = Command.valueOf(msg0[0]);
            } catch(IllegalArgumentException e) {
                print((new DukeException("I can't understand the message")).toString());
                continue;
            }
            switch(cmd) {

            case list:
                if (tasks.size() == 0) {
                    print("No tasks right now");
                } else {
                    String toPrint = "";
                    for (int i = 0; i < tasks.size(); i++) {
                        toPrint += (i + 1) + "." + tasks.get(i) + "\n";
                    }
                    print(toPrint);
                }
                break;

            case todo:
                if (msg0.length < 2) {
                    print((new DukeException("please provide a description for todo")).toString());
                    break;
                }
                Task todo = new ToDo(msg0[1]);
                tasks.add(todo);
                print("Got it. I've added this task:\n" + todo + getNumTasks());
                break;

            case deadline:
                if (msg0.length < 2) {
                    print((new DukeException("please provide a description for deadline")).toString());
                    break;
                }
                try {
                    String time = msg[1].split(" ", 2)[1];
                    Task deadline = new Deadline(msg0[1], time);
                    tasks.add(deadline);
                    print("Got it. I've added this task:\n" + deadline + getNumTasks());
                } catch(ArrayIndexOutOfBoundsException e) {
                    print((new DukeException("please provide a time for deadline")).toString());
                }
                break;

            case event:
                if (msg0.length < 2) {
                    print((new DukeException("please provide a description for event")).toString());
                    break;
                }
                try {
                    String time = msg[1].split(" ", 2)[1];
                    Task event = new Event(msg0[1], time);
                    tasks.add(event);
                    print("Got it. I've added this task:\n" + event + getNumTasks());
                } catch (ArrayIndexOutOfBoundsException e) {
                    print((new DukeException("please provide a time for event")).toString());
                }
                break;

            case done:
                if(msg0.length != 2) {
                    print((new DukeException("wrong number of arguments for done")).toString());
                    break;
                }
                try {
                    int taskIndex = Integer.parseInt(msg0[1]) - 1;
                    tasks.get(taskIndex).setDone();
                    print("Nice! I've marked this task as done:\n" + tasks.get(taskIndex));
                } catch(IndexOutOfBoundsException e) {
                    print((new DukeException("task number does not exist")).toString());
                } catch(NumberFormatException e) {
                    print((new DukeException("indicate task number as an integer")).toString());
                }
                break;

            case delete:
                if(msg0.length != 2) {
                    print((new DukeException("wrong number of arguments for delete")).toString());
                    break;
                }
                try {
                    int taskIndex = Integer.parseInt(msg0[1]) - 1;
                    Task toRemove = tasks.get(taskIndex);
                    tasks.remove(taskIndex);
                    print("Noted. I've removed this task:\n" + toRemove + getNumTasks());
                } catch(IndexOutOfBoundsException e) {
                    print((new DukeException("task number does not exist")).toString());
                } catch(NumberFormatException e) {
                    print((new DukeException("indicate task number as an integer")).toString());
                }
                break;

            case bye:
                print("Bye. Hope to see you again!");
                saveData();
                return;
            }
        }
    }
}
