import java.time.LocalDate;
import java.util.*;
import java.io.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
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
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

//    public static void main(String[] args) throws IOException {
//        ArrayList<Task> tasks = new ArrayList<>();
//        try {
//            File myObj = new File("data/duke.txt");
//            if(myObj.exists()) {
//                Scanner myReader = new Scanner(myObj);
//                while (myReader.hasNextLine()) {
//                    String data = myReader.nextLine();
//                    Character type = data.charAt(0);
//                    boolean done = false;
//                    if(data.charAt(4)=='1') {
//                        done = true;
//                    }
//                    data = data.substring(8);
//                    int mid = data.indexOf("|");
//                    String name;
//                    String date = null;
//                    int year = -999;
//                    int mon = -999;
//                    int day = -999;
//                    if(mid > 0) {
//                        name = data.substring(0, mid-1);
//                        date = data.substring(mid+2);
//                        year = Integer.valueOf(date.substring(0, 4));
//                        mon = Integer.valueOf(date.substring(5, 7));
//                        day = Integer.valueOf(date.substring(8));
//                    } else {
//                        name = data;
//                    }
//                    if(type == 'T') {
//                        tasks.add(new ToDo(name, done));
//                    } else if(type == 'D') {
//                        LocalDate d = LocalDate.of(year, mon, day);
//                        tasks.add(new Deadline(name, d, done));
//                    } else if(type == 'E') {
//                        LocalDate d = LocalDate.of(year, mon, day);
//                        tasks.add(new Event(name, d, done));
//                    }
//
//                }
//                myReader.close();
//            } else {
//                myObj.createNewFile();
//            }
//
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scanner in = new Scanner(System.in);
//        System.out.println("    ____________________________________________________________");
//        System.out.println("     Hello! I'm Duke");
//        System.out.println("     What can I do for you?");
//        System.out.println("    ____________________________________________________________");
//        System.out.println("Enter a command:");
//        String commands = in.nextLine();
//        //int taskCount = 0;
//        while(!commands.equals("bye")) {
//            if(commands.equals("list")) {
//                System.out.println("    ____________________________________________________________");
//                System.out.println("     Here are the tasks in your list:");
//                int i = 1;
//                for(Task s : tasks) {
//                    if(s != null) {
//                        System.out.println("     " + i + "." + s.toString());
//                        i++;
//                    } else {
//                        break;
//                    }
//                }
//                System.out.println("    ____________________________________________________________");
//            } else if(commands.split(" ")[0].equals("done")) {
//                int n = Integer.parseInt(commands.split(" ")[1]);
//                tasks.get(n-1).markAsDone();
//                System.out.println("    ____________________________________________________________");
//                System.out.println("     Nice! I've marked this task as done:");
//                System.out.println("       " + tasks.get(n-1).toString());
//                System.out.println("    ____________________________________________________________");
//                Task t = tasks.get(n-1);
//                String old = null;
//                if(t instanceof ToDo) {
//                    old = "T | 0 | " + t.name;
//                } else if(t instanceof Deadline) {
//                    old = "D | 0 | " + t.name;
//                } else if(t instanceof Event) {
//                    old = "E | 0 | " + t.name;
//                }
//
//                BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"));
//                String oldContent = "";
//                String line = reader.readLine();
//                while (line != null) {
//                    oldContent = oldContent + line + System.lineSeparator();
//                    line = reader.readLine();
//                }
//                String newContent = null;
//                if(t instanceof ToDo) {
//                    newContent = oldContent.replace(old, "T | 1 | " + t.name);
//                } else if(t instanceof Deadline) {
//                    newContent = oldContent.replace(old, "D | 1 | " + t.name);
//                } else if(t instanceof Event) {
//                    newContent = oldContent.replace(old, "E | 1 | " + t.name);
//                }
//
//                FileWriter writer = new FileWriter("data/duke.txt");
//                writer.write(newContent);
//                reader.close();
//                writer.close();
//            } else if(commands.split(" ")[0].equals("todo")) {
//                int iend = commands.indexOf(" ");
//                String n = commands.substring(iend+1);
//                tasks.add(new ToDo(n));
//                try{
//                    tasks.get(tasks.size()-1).addTask(tasks.size());
//                    BufferedWriter writer = new BufferedWriter(
//                            new FileWriter("data/duke.txt", true));
//                    writer.write("T | 0 | " + n + "\n");
//                    writer.close();
//                } catch(DukeException e){
//                    System.out.println("    ____________________________________________________________");
//                    System.out.println(e.getMessage());
//                    System.out.println("    ____________________________________________________________");
//                }
//            } else if(commands.split(" ")[0].equals("deadline")) {
//                try{
//                    int iend1 = commands.indexOf(" ");
//                    int iend = commands.indexOf("/");
//                    String subString1= commands.substring(iend1+1 , iend-1);
//                    String subString2= commands.substring(iend+4);
//                    System.out.println(subString1);
//                    System.out.println(subString2);
//                    int year = Integer.valueOf(subString2.substring(0, 4));
//                    int mon = Integer.valueOf(subString2.substring(5, 7));
//                    int day = Integer.valueOf(subString2.substring(8));
//                    tasks.add(new Deadline(subString1, LocalDate.of(year, mon, day)));
//                    tasks.get(tasks.size()-1).addTask(tasks.size());
//                    BufferedWriter writer = new BufferedWriter(
//                            new FileWriter("data/duke.txt", true));
//                    writer.write("D | 0 | " + subString1 + " | " + subString2 + "\n");
//                    writer.close();
//                }
//                catch(DukeException e){
//                    System.out.println("    ____________________________________________________________");
//                    System.out.println(e.getMessage());
//                    System.out.println("    ____________________________________________________________");
//                }
//                catch(StringIndexOutOfBoundsException e){
//                    System.out.println("    ____________________________________________________________");
//                    System.out.println("     ☹ OOPS!!! The due date of a deadline cannot be empty. (Format: /by + date[YYYY-MM-DD])");
//                    System.out.println("    ____________________________________________________________");
//                }
//            } else if(commands.split(" ")[0].equals("event")) {
//                try{
//                    int iend1 = commands.indexOf(" ");
//                    int iend = commands.indexOf("/");
//                    String subString1= commands.substring(iend1+1 , iend-1);
//                    String subString2= commands.substring(iend+4);
//                    int year = Integer.valueOf(subString2.substring(0, 4));
//                    int mon = Integer.valueOf(subString2.substring(5, 7));
//                    int day = Integer.valueOf(subString2.substring(8));
//                    tasks.add(new Event(subString1, LocalDate.of(year, mon, day)));
//                    tasks.get(tasks.size()-1).addTask(tasks.size());
//                    BufferedWriter writer = new BufferedWriter(
//                            new FileWriter("data/duke.txt", true));
//                    writer.write("E | 0 | " + subString1 + " | " + subString2 + "\n");
//                    writer.close();
//                } catch(DukeException e){
//                    System.out.println("    ____________________________________________________________");
//                    System.out.println(e.getMessage());
//                    System.out.println("    ____________________________________________________________");
//                }
//                catch(StringIndexOutOfBoundsException e){
//                    System.out.println("    ____________________________________________________________");
//                    System.out.println("     ☹ OOPS!!! The start and end date of an event cannot be empty.(Format: /at + duration[YYYY-MM-DD])");
//                    System.out.println("    ____________________________________________________________");
//                }
//            } else if(commands.split(" ")[0].equals("delete")) {
//                int n = Integer.parseInt(commands.split(" ")[1]);
//                System.out.println("    ____________________________________________________________");
//                System.out.println("     Noted. I've removed this task:");
//                System.out.println("       " + tasks.get(n-1).toString());
//                System.out.println("     Now you have " + (tasks.size()-1) + " tasks in the list.");
//                System.out.println("    ____________________________________________________________");
//                tasks.remove(n-1);
//
//                BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"));
//                String oldContent = "";
//                String line = reader.readLine();
//                int counter = 0;
//                while (line != null) {
//                    if(counter != n-1) {
//                        oldContent = oldContent + line + System.lineSeparator();
//                    }
//                    counter++;
//                    line = reader.readLine();
//                }
//
//                FileWriter writer = new FileWriter("data/duke.txt");
//                writer.write(oldContent);
//                reader.close();
//                writer.close();
//
//            }else {
//                System.out.println("    ____________________________________________________________");
//                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                System.out.println("    ____________________________________________________________");
//            }
//            System.out.println("Enter a command:");
//            commands = in.nextLine();
//        }
//        System.out.println("    ____________________________________________________________");
//        System.out.println("     Bye. Hope to see you again soon!");
//        System.out.println("    ____________________________________________________________");
//
//    }
}
