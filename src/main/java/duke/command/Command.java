package duke.command;
import duke.dukeException.DukeException;
import duke.task.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Command {
    /** The input command by user */
    public String commandName;

    /**
     * Class constructor.
     */
    public Command(String commandName) {
        this.commandName = commandName;
    }


    /**
     * Processes tasks according to the command.
     *
     * @param tasks  the tasks before execution.
     * @throws IOException  If an input or output
     *                      exception occurred
     */
    public void execute(TaskList tasks) throws IOException, DukeException {
        ArrayList<Task> tList = tasks.tasks;
        if (commandName.equals("list")) {
            System.out.println("     Here are the tasks in your list:");
            int counter = 1;
            for(Task s : tList) {
                if (s != null) {
                    System.out.println("     " + counter + "." + s.toString());
                    counter++;
                } else {
                    break;
                }
            }
        } else if (commandName.split(" ")[0].equals("done")) {
            int index = Integer.parseInt(commandName.split(" ")[1]);
            tList.get(index-1).markAsDone();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + tList.get(index-1).toString());
            Task currTask = tList.get(index-1);
            tasks.tasks = tList;
            String old = null;
            if (currTask instanceof ToDo) {
                old = "T | 0 | " + currTask.name;
            } else if (currTask instanceof Deadline) {
                old = "D | 0 | " + currTask.name;
            } else if (currTask instanceof Event) {
                old = "E | 0 | " + currTask.name;
            }
            BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"));
            String oldContent = "";
            String line = reader.readLine();
            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = null;
            if (currTask instanceof ToDo) {
                newContent = oldContent.replace(old, "T | 1 | " + currTask.name);
            } else if (currTask instanceof Deadline) {
                newContent = oldContent.replace(old, "D | 1 | " + currTask.name);
            } else if (currTask instanceof Event) {
                newContent = oldContent.replace(old, "E | 1 | " + currTask.name);
            }
            FileWriter writer = new FileWriter("data/duke.txt");
            writer.write(newContent);
            reader.close();
            writer.close();
        } else if (commandName.split(" ")[0].equals("delete")) {
            int index = Integer.parseInt(commandName.split(" ")[1]);
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + tList.get(index-1).toString());
            System.out.println("     Now you have " + (tList.size()-1) + " tasks in the list.");
            tList.remove(index-1);
            tasks.tasks = tList;
            BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"));
            String oldContent = "";
            String line = reader.readLine();
            int counter = 0;
            while (line != null) {
                if (counter != index-1) {
                    oldContent = oldContent + line + System.lineSeparator();
                }
                counter++;
                line = reader.readLine();
            }
            FileWriter writer = new FileWriter("data/duke.txt");
            writer.write(oldContent);
            reader.close();
            writer.close();
        }else if (commandName.split(" ")[0].equals("todo")) {
            if (commandName.length() < 5) {
                ToDo todo = new ToDo(commandName);
                todo.addTask(0);
            } else {
                int end = commandName.indexOf(" ");
                String name = commandName.substring(end+1);
                try{
                    tList.add(new ToDo(name));
                    tList.get(tList.size()-1).addTask(tList.size());
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("data/duke.txt", true));
                    writer.write("T | 0 | " + name + "\n");
                    writer.close();
                    tasks.tasks = tList;
                } catch(DukeException e){
                    System.out.println(e.getMessage());
                }
            }
        } else if (commandName.split(" ")[0].equals("deadline")) {
            if (commandName.length() < 9) {
                Deadline deadline = new Deadline(commandName, null);
                deadline.addTask(0);
            } else {
                try {
                    int end1 = commandName.indexOf(" ");
                    int end = commandName.indexOf("/");
                    String subString1 = commandName.substring(end1 + 1, end - 1);
                    String subString2 = commandName.substring(end + 4);
                    int year = Integer.valueOf(subString2.substring(0, 4));
                    int mon = Integer.valueOf(subString2.substring(5, 7));
                    int day = Integer.valueOf(subString2.substring(8));
                    tList.add(new Deadline(subString1, LocalDate.of(year, mon, day)));
                    tList.get(tList.size() - 1).addTask(tList.size());
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("data/duke.txt", true));
                    writer.write("D | 0 | " + subString1 + " | " + subString2 + "\n");
                    writer.close();
                    tasks.tasks = tList;
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("     ☹ OOPS!!! The due date of a deadline cannot be empty. (Format: /by + date[YYYY-MM-DD])");
                }
            }
        } else if (commandName.split(" ")[0].equals("event")) {
            if (commandName.length() < 6) {
                Event event = new Event(commandName, null);
                event.addTask(0);
            } else {
                try{
                    int end1 = commandName.indexOf(" ");
                    int end = commandName.indexOf("/");
                    String subString1= commandName.substring(end1+1 , end-1);
                    String subString2= commandName.substring(end+4);
                    int year = Integer.valueOf(subString2.substring(0, 4));
                    int mon = Integer.valueOf(subString2.substring(5, 7));
                    int day = Integer.valueOf(subString2.substring(8));
                    tList.add(new Event(subString1, LocalDate.of(year, mon, day)));
                    tList.get(tList.size()-1).addTask(tList.size());
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("data/duke.txt", true));
                    writer.write("E | 0 | " + subString1 + " | " + subString2 + "\n");
                    writer.close();
                    tasks.tasks = tList;
                } catch(DukeException e){
                    System.out.println(e.getMessage());
                }
                catch(StringIndexOutOfBoundsException e) {
                    System.out.println("     ☹ OOPS!!! The start and end date of an event cannot be empty.(Format: /at + duration[YYYY-MM-DD])");
                }
            }
        } else if (commandName.split(" ")[0].equals("find")) {
            String match = commandName.split(" ")[1];
            BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"));
            String line = reader.readLine();
            int counter = 0;
            int secondCounter = 1;
            System.out.println("     Here are the matching tasks in your list:");
            while (line != null) {
                if (line.indexOf(match) != -1) {
                    System.out.println("     " + secondCounter + "." + tasks.tasks.get(counter).toString());
                    secondCounter++;
                }
                line = reader.readLine();
                counter++;
            }
        } else if (commandName.equals("bye")) {
            System.out.println("     Bye. Hope to see you again soon!");
        } else {
            System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Decides if this is the end of program.
     *
     * @return true if command is bye.
     */
    public boolean isExit() {
        return (commandName.equals("bye"));
    }
}
