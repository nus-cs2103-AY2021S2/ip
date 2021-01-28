
import java.io.IOException;

import java.text.ParseException;


public class Duke {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (NullPointerException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        new Duke("data.txt").run();
        /*List<Task> taskList = new ArrayList<Task>(100);
        
        String filePath = "data.txt";
        FileWriter fw = new FileWriter(filePath, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String readLine = "";
        while ((readLine = br.readLine()) != null) {
            String[] read = readLine.split("]", 2);
            String type = read[0].replace("[", "");
            if (type.contains("T")) {
                String sequence = read[1];
                String[] data = sequence.split("] ");
                Task newTask = new ToDos(data[1]);
                if (sequence.contains("\u2718")) {
                    newTask.markAsDone();
                }
                taskList.add(newTask);
            } else if (type.contains("D")) {
                try{
                String sequence = read[1];
                String[] data = sequence.split("] ");
                String secondData = data[1].replace("(by: ", "").replace(")", "");
                String[] seperateTime = secondData.split(" ", 2);
                SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
                Date date = formatter.parse(seperateTime[1]);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
                Task newTask = new Deadline(seperateTime[0], dateFormat.format(date));
                if (sequence.contains("\u2718")) {
                    newTask.markAsDone();
                }
                taskList.add(newTask);
            } catch (ParseException ex) {
                String sequence = read[1];
                String[] data = sequence.split("] ");
                String secondData = data[1].replace("(by: ", "").replace(")", "");
                SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
                Date date = formatter.parse(secondData);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
                Task newTask = new Deadline(data[0], dateFormat.format(date));
                if (sequence.contains("\u2718")) {
                    newTask.markAsDone();
                }
                taskList.add(newTask);
            }
        
        } else if (type.contains("E")) {
            try{
                String sequence = read[1];
                String[] data = sequence.split("] ");
                String secondData = data[1].replace("(at: ", "").replace(")", "");
                String[] seperateTime = secondData.split(" ", 2);
                SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
                Date date = formatter.parse(seperateTime[1]);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Task newTask = new Events(seperateTime[0], dateFormat.format(date));
                if (sequence.contains("\u2718")) {
                   newTask.markAsDone();
                }
                taskList.add(newTask);
            } catch (ParseException ex) {
                String sequence = read[1];
                String[] data = sequence.split("] ");
                String secondData = data[1].replace("(at: ", "").replace(")", "");
                SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
                Date date = formatter.parse(secondData);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Task newTask = new Events(data[0], dateFormat.format(date));
                if (sequence.contains("\u2718")) {
                   newTask.markAsDone();
                }
                taskList.add(newTask);
                
                }
                
            }
        }
        
        
        br.close();
        
        
        // inserts correct file path separator on *nix and Windows
        // works on *nix
        // works on Windows
        
            Scanner sc = new Scanner(System.in);
            String logo = "Hello! I'm Duke\n"
                    + "What can I do for you?\n";
            System.out.println(logo);
        
            while (sc.hasNext()) {
                try {
                    String line = sc.nextLine();
                    String[] lineSplit = line.split(" ", 2);
                    Command command = Command.valueOf(lineSplit[0]);
        
                    Scanner fileScanner = new Scanner(new File(filePath));
        
                    if (command.equals(Command.bye)) {
                        break;
                    } else {
                        if (command.equals(Command.list)) {
                            int length = lineSplit.length;
                            if (length > 1) {
                                String[] item = lineSplit[1].split("/by ");
                                LocalDate date = LocalDate.parse(item[0]);
                                System.out.println("____________________________________________________________\n"
                                        + "Here are the tasks in your list due on "
                                        + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
                                while (fileScanner.hasNext()) {
                                    String curTask = fileScanner.nextLine();
                                    String[] string = curTask.split(": ");
                                    if (string.length > 1) {
                                        String dueDate = string[1].replace(")", "");
                                        if (dueDate.equals(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")))) {
                                            System.out.println(curTask);
                                        }
                                    }
                                
                                }
                                System.out.println("____________________________________________________________");
                            } else {
                                System.out.println("____________________________________________________________\n"
                                        + "Here are the tasks in your list");
                                /*for (int i = 1; i <= taskList.size(); i++) {
                                    Task curTask = taskList.get(i - 1);
                                    System.out.println(i + "." + curTask.toString());
                                }
                                while (fileScanner.hasNext()) {
                                    System.out.println(fileScanner.nextLine());
                                }
                                System.out.println("____________________________________________________________");
        
                            }
                        } else if (command.equals(Command.done)) {
                            try {
                                FileWriter tfw = new FileWriter(filePath);
                                BufferedWriter tbw = new BufferedWriter(tfw);
                                PrintWriter tpw = new PrintWriter(tbw);
        
                                int index = Integer.valueOf(lineSplit[1]) - 1;
                                System.out.println(taskList.size());
                                Task curTask = taskList.get(index);
                                curTask.markAsDone();
                                System.out.println("____________________________________________________________\n"
                                        + "Nice! I've marked this task as done:\n" + curTask.toString()
                                        + "\n____________________________________________________________");
        
                                for (int i = 1; i <= taskList.size(); i++) {
                                    Task writeTask = taskList.get(i - 1);
                                    tpw.println(i + "." + writeTask.toString());
                                }
                                tpw.flush();
                                tpw.close();
                                
                            
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                throw new DukeException("\u00a9 OOPS!!! The description of a done cannot be empty.");
                            }
                        } else if (command.equals(Command.delete)) {
                            try {
        
                    
        
                                FileWriter tfw = new FileWriter(filePath);
                                BufferedWriter tbw = new BufferedWriter(tfw);
                                PrintWriter tpw = new PrintWriter(tbw);
                                
        
                                int index = Integer.valueOf(lineSplit[1]) - 1;
                                Task curTask = taskList.get(index);
                                taskList.remove(index);
                                System.out.println("____________________________________________________________\n"
                                        + "Noted. I've removed this task:\n  " + curTask.toString() + "\nNow you have "
                                        + taskList.size() + " tasks in the list.\n"
                                        + "____________________________________________________________");
                                for (int i = 1; i <= taskList.size(); i++) {
                                    Task writeTask = taskList.get(i-1);
                                    tpw.println(i + "." + writeTask.toString());
                                }
                                tpw.close();
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                throw new DukeException("\u00a9 OOPS!!! The description of a delete cannot be empty.");
                            } catch (IndexOutOfBoundsException ex) {
                                throw new DukeException("\u00a9 OOPS!!! There is nothing to delete at " + lineSplit[1]);
                            }
                        } else {
                            if (command.equals(Command.todo)) {
                                try {
                                    String[] item = lineSplit[1].split("/");
                                    Task newTask = new ToDos(item[0]);
                                    taskList.add(newTask);
                                    System.out.println("____________________________________________________________\n"
                                            + "Got it. I've added this task:\n  " + newTask.toString()
                                            + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                                            + "____________________________________________________________");
                                    pw.println(taskList.size() + "." + newTask.toString());
                                } catch (ArrayIndexOutOfBoundsException ex) {
                                    throw new DukeException(
                                            "\u00a9 OOPS!!! The description of a todo cannot be empty.");
                                }
                            } else if (command.equals(Command.deadline)) {
                                try {
                                    String[] item = lineSplit[1].split("/by ");
                                    Task newTask = new Deadline(item[0], item[1]);
                                    taskList.add(newTask);
                                    System.out.println("____________________________________________________________\n"
                                            + "Got it. I've added this task:\n  " + newTask.toString()
                                            + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                                            + "____________________________________________________________");
                                    pw.println(taskList.size() + "." + newTask.toString());
                                } catch (ArrayIndexOutOfBoundsException ex) {
                                    throw new DukeException(
                                            "\u00a9 OOPS!!! The description of a deadline cannot be empty.");
                                }
                            } else if (command.equals(Command.event)) {
                                try {
                                    String[] item = lineSplit[1].split("/at ");
                                    Task newTask = new Events(item[0], item[1]);
                                    taskList.add(newTask);
                                    System.out.println("____________________________________________________________\n"
                                            + "Got it. I've added this task:\n  " + newTask.toString()
                                            + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                                            + "____________________________________________________________");
                                    pw.println(taskList.size() + "." + newTask.toString());
                                } catch (ArrayIndexOutOfBoundsException ex) {
                                    throw new DukeException(
                                            "\u00a9 OOPS!!! The description of a event cannot be empty.");
                                }
                            } else {
                                throw new DukeException(
                                        "\u00a9 OOPS!!! I'm sorry, but I don't know what that means :-(");
                            }
                            pw.flush();
        
                        }
                    }
        
                } catch (DukeException ex) {
                    System.out.println("____________________________________________________________\n"
                            + ex.getMessage() + "\n____________________________________________________________");
                } catch (IllegalArgumentException ex) {
                    System.out.println("____________________________________________________________\n"
                            + "\u00a9 OOPS!!! I'm sorry, but I don't know what that means :-("
                            + "\n____________________________________________________________");
                } catch (DateTimeParseException ex) {
                    System.out.println("____________________________________________________________\n"
                            + "\u00a9 OOPS!!! I'm sorry, but I don't know what the date means :-("
                            + "\n____________________________________________________________");
                } catch (NullPointerException ex) {
                    System.out.println("____________________________________________________________\n"
                            + "\u00a9 OOPS!!! Nothing found :-("
                            + "\n____________________________________________________________");
                }
            }
            pw.close();
            sc.close();
            System.out.println("____________________________________________________________\n"
                    + "Bye. Hope to see you again soon!"
                    + "\n____________________________________________________________");
        }*/
    }
}