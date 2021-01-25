import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private ArrayList<Task> listToDo;
    private File taskFile;

    private Duke() {
        try {
            this.listToDo = new ArrayList<>();
            Path filePath = Paths.get("data/DanhDuke.txt");
            if (Files.exists(filePath)) {
                this.taskFile = filePath.toFile();
            } else if (Files.exists(Paths.get("data"))) {
                Files.createFile(Paths.get("data/DanhDuke.txt"));
                this.taskFile = filePath.toFile();
            } else {
                Files.createDirectories(Paths.get("data"));
                Files.createFile(Paths.get("data/DanhDuke.txt"));
                this.taskFile = filePath.toFile();
            }
        } catch (IOException ie) {
            System.out.println("Something went wrong" + ie.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        echoHi();
        Duke myDuke = new Duke();
        writeBack(myDuke);
        Scanner input = new Scanner(System.in);
        boolean signalToExit = false;
        while (!signalToExit && input.hasNextLine()) {
            String command = input.nextLine();
            if (command.startsWith("list")) {
                if (command.length() != 4) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    printList(myDuke);
                }
            } else if (command.startsWith("bye")) {
                if (command.length() != 3) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    signalToExit = true;
                }
            } else if (command.startsWith("done ")) {
                if (command.length() == 5 || !isNumeric(command.substring(5))) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else if (Integer.parseInt(command.substring(5)) > myDuke.listToDo.size()) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    markTaskDone(myDuke, Integer.parseInt(command.substring(5)));
                }
            } else if (command.startsWith("delete ")) {
                if (command.length() == 7 || !isNumeric(command.substring(7))) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else if (Integer.parseInt(command.substring(7)) > myDuke.listToDo.size()) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    deleteTask(myDuke, Integer.parseInt(command.substring(7)));
                }
            } else if (command.startsWith("todo ")) {
                if (command.length() == 5) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    addToList(myDuke, command.substring(5));
                }
            } else if (command.startsWith("deadline ")) {
                if (command.length() == 9 || !command.contains("/by ")) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else if (command.indexOf("/by ") + 4 == command.length()) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    addToList(myDuke, command.substring(9));
                }
            } else if (command.startsWith("event ")) {
                if (command.length() == 6 || !command.contains("/at ")) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else if (command.indexOf("/at ") + 4 == command.length()) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    addToList(myDuke, command.substring(6));
                }
            } else {
                try {
                    executeFalseCommand(command);
                } catch (DukeException err) {
                    printErrMsg(err);
                }
            }
            if (command.equals("bye")) {
                echoBye();
            }
        }
    }

    public static void echoBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }

    public static void echoHi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    ____________________________________________________________");
        System.out.println(logo);
        System.out.println("Hello! I'm Danh's Duke\nWhat can I do for you, Mr Danh?");
        System.out.println("    ____________________________________________________________\n");
    }

    public static void printList(Duke duke) {
        int index = 1;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (Task task : duke.listToDo) {
            System.out.format("     %d. " + task.printTask() + "\n", index);
            index++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void addToList(Duke duke, String taskdescription) throws IOException {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        Task task;
        if (taskdescription.contains("/at")) {
            String taskName = taskdescription.substring(0, taskdescription.indexOf("/at"));
            String dlTime = taskdescription.substring(taskdescription.indexOf("/at") + 4);
            task = new Event(taskName, dlTime);
        } else if (taskdescription.contains("/by")) {
            String taskName = taskdescription.substring(0, taskdescription.indexOf("/by"));
            String dlTime = taskdescription.substring(taskdescription.indexOf("/by") + 4);
            task = new Deadline(taskName, dlTime);
        } else {
            task = new ToDo(taskdescription);
        }
        FileWriter fw = new FileWriter(duke.taskFile, true);
        fw.write(task.printTask() + "\n");
        fw.close();
        System.out.println("       " + task.printTask());
        duke.listToDo.add(task);
        System.out.format("     Now you have %d tasks in the list.\n", duke.listToDo.size());
        System.out.println("    ____________________________________________________________\n");
    }

    public static void markTaskDone(Duke duke, int index) throws IOException {
        System.out.println("    ____________________________________________________________");
        Task task = duke.listToDo.get(index - 1);
        task.markAsDone();
        FileWriter fw = new FileWriter(duke.taskFile);
        String toWrite = "";
        for (Task task1 : duke.listToDo) {
            toWrite += task1.printTask() + "\n";
        }
        fw.write(toWrite);
        fw.close();
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + task.printTask());
        System.out.println("    ____________________________________________________________\n");
    }

    public static void deleteTask(Duke duke, int index) throws IOException {
        System.out.println("    ____________________________________________________________");
        Task task = duke.listToDo.get(index - 1);
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       " + task.printTask());
        System.out.format("     Now you have %d tasks in the list.\n", duke.listToDo.size() - 1);
        System.out.println("    ____________________________________________________________\n");
        duke.listToDo.remove(index - 1);
        FileWriter fw = new FileWriter(duke.taskFile);
        String toWrite = "";
        for (Task task1 : duke.listToDo) {
            toWrite += task1.printTask() + "\n";
        }
        fw.write(toWrite);
        fw.close();
    }

    public static void executeFalseCommand(String command) throws DukeException {
        if (command.startsWith("list")) {
            throw new DukeException("     list command should not have body, Sir!");
        } else if (command.startsWith("bye")) {
            throw new DukeException("     bye command should not have body, Sir!");
        } else if (command.startsWith("done ")) {
            throw new DukeException("     No body or wrong body format or unvalid number for done command, Sir!");
        } else if (command.startsWith("delete ")) {
            throw new DukeException("     No body or wrong body format or unvalid number for delete command, Sir!");
        } else if (command.startsWith("todo ")) {
            throw new DukeException("     No body detected for todo command, Sir!");
        } else if (command.startsWith("deadline ")) {
            throw new DukeException("     no body detected or no dlTime detected for deadline command, Sir!");
        } else if (command.startsWith("event ")) {
            throw new DukeException("     no body detected or no eTime detected for Event command, Sir!");
        } else {
            throw new DukeException("     Invalid command format");
        }
    }

    public static void printErrMsg(DukeException err) {
        System.out.println("    ____________________________________________________________\n" + err.getMessage() + "\n" + "    ____________________________________________________________\n");
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double randomNo = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void writeBack(Duke duke) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(duke.taskFile));
        String line = reader.readLine();
        while (line != null) {
            switch (line.substring(1,2)) {
                case "T":
                    if (line.charAt(4) == ' ') {
                        duke.listToDo.add(new ToDo(line.substring(7)));
                    } else {
                        ToDo newToDo = new ToDo(line.substring(7));
                        newToDo.markAsDone();
                        duke.listToDo.add(newToDo);
                    }
                    break;
                case "D":
                    int dlIndex = line.indexOf("(by: ");
                    if (line.charAt(4) == ' ') {
                        duke.listToDo.add(new Deadline(line.substring(7, dlIndex - 1), line.substring(dlIndex + 5, line.length() - 1)));
                    } else {
                        Deadline newDL = new Deadline(line.substring(7, dlIndex - 1), line.substring(dlIndex + 5, line.length() - 1));
                        newDL.markAsDone();
                        duke.listToDo.add(newDL);
                    }
                    break;
                default:
                    int etIndex = line.indexOf("(at: ");
                    if (line.charAt(4) == ' ') {
                        duke.listToDo.add(new Deadline(line.substring(7, etIndex - 1), line.substring(etIndex + 5, line.length() - 1)));
                    } else {
                        Deadline newDL = new Deadline(line.substring(7, etIndex - 1), line.substring(etIndex + 5, line.length() - 1));
                        newDL.markAsDone();
                        duke.listToDo.add(newDL);
                    }
            }
            line = reader.readLine();
        }
        reader.close();
    }
}


