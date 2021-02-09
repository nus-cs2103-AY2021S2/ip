package duke.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.dukeexception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class Command {
    /** The input command by user */
    private String commandName;


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
    public String execute(TaskList tasks) throws IOException, DukeException {
        ArrayList<Task> tList = tasks.getTasks();
        String output = "";
        if (commandName.equals("list")) {
            output = "     Here are the tasks in your list:\n";
            int counter = 1;
            for (Task s : tList) {
                if (s != null) {
                    output += "     " + counter + "." + s.toString() + "\n";
                    counter++;
                } else {
                    break;
                }
            }
        } else if (commandName.split(" ")[0].equals("done")) {
            int index = Integer.parseInt(commandName.split(" ")[1]);
            tList.get(index - 1).markAsDone();
            output = "     Nice! I've marked this task as done:\n";
            output += "       " + tList.get(index - 1).toString() + "\n";
            Task currTask = tList.get(index - 1);
            tasks.setTasks(tList);
            String old = null;
            if (currTask instanceof ToDo) {
                old = "T | 0 | " + currTask.getName();
            } else if (currTask instanceof Deadline) {
                old = "D | 0 | " + currTask.getName();
            } else if (currTask instanceof Event) {
                old = "E | 0 | " + currTask.getName();
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
                newContent = oldContent.replace(old, "T | 1 | " + currTask.getName());
            } else if (currTask instanceof Deadline) {
                newContent = oldContent.replace(old, "D | 1 | " + currTask.getName());
            } else if (currTask instanceof Event) {
                newContent = oldContent.replace(old, "E | 1 | " + currTask.getName());
            }
            FileWriter writer = new FileWriter("data/duke.txt");
            writer.write(newContent);
            reader.close();
            writer.close();
        } else if (commandName.split(" ")[0].equals("delete")) {
            int index = Integer.parseInt(commandName.split(" ")[1]);
            output = "     Noted. I've removed this task:\n";
            output += "       " + tList.get(index - 1).toString() + "\n";
            output += "     Now you have " + (tList.size() - 1) + " tasks in the list.\n";
            tList.remove(index - 1);
            tasks.setTasks(tList);
            BufferedReader reader = new BufferedReader(new FileReader("/data/duke.txt"));
            String oldContent = "";
            String line = reader.readLine();
            int counter = 0;
            while (line != null) {
                if (counter != index - 1) {
                    oldContent = oldContent + line + System.lineSeparator();
                }
                counter++;
                line = reader.readLine();
            }
            FileWriter writer = new FileWriter("data/duke.txt");
            writer.write(oldContent);
            reader.close();
            writer.close();
        } else if (commandName.split(" ")[0].equals("todo")) {
            if (commandName.length() < 5) {
                ToDo todo = new ToDo(commandName);
                output = todo.addTask(0);
            } else {
                int end = commandName.indexOf(" ");
                String name = commandName.substring(end + 1);
                try {
                    tList.add(new ToDo(name));
                    output = tList.get(tList.size() - 1).addTask(tList.size());
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("data/duke.txt", true));
                    writer.write("T | 0 | " + name + "\n");
                    writer.close();
                    tasks.setTasks(tList);
                } catch (DukeException e) {
                    output += e.getMessage();
                }
            }
        } else if (commandName.split(" ")[0].equals("deadline")) {
            if (commandName.length() < 9) {
                Deadline deadline = new Deadline(commandName, null);
                output = deadline.addTask(0);
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
                    output = tList.get(tList.size() - 1).addTask(tList.size());
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("data/duke.txt", true));
                    writer.write("D | 0 | " + subString1 + " | " + subString2 + "\n");
                    writer.close();
                    tasks.setTasks(tList);
                } catch (DukeException e) {
                    output += e.getMessage();
                } catch (StringIndexOutOfBoundsException e) {
                    output += "      OOPS!!! The due date of a deadline "
                            + "cannot be empty. (Format: /by + date[YYYY-MM-DD])";
                }
            }
        } else if (commandName.split(" ")[0].equals("event")) {
            if (commandName.length() < 6) {
                Event event = new Event(commandName, null);
                output = event.addTask(0);
            } else {
                try {
                    int end1 = commandName.indexOf(" ");
                    int end = commandName.indexOf("/");
                    String subString1 = commandName.substring(end1 + 1 , end - 1);
                    String subString2 = commandName.substring(end + 4);
                    int year = Integer.valueOf(subString2.substring(0, 4));
                    int mon = Integer.valueOf(subString2.substring(5, 7));
                    int day = Integer.valueOf(subString2.substring(8));
                    tList.add(new Event(subString1, LocalDate.of(year, mon, day)));
                    output = tList.get(tList.size() - 1).addTask(tList.size());
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("data/duke.txt", true));
                    writer.write("E | 0 | " + subString1 + " | " + subString2 + "\n");
                    writer.close();
                    tasks.setTasks(tList);
                } catch (DukeException e) {
                    output += e.getMessage();
                } catch (StringIndexOutOfBoundsException e) {
                    output += "      OOPS!!! The start and end date of "
                            + "an event cannot be empty.(Format: /at + duration[YYYY-MM-DD])";
                }
            }
        } else if (commandName.split(" ")[0].equals("find")) {
            String match = commandName.split(" ")[1];
            BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"));
            String line = reader.readLine();
            int counter = 0;
            int secondCounter = 1;
            output = "     Here are the matching tasks in your list:\n";
            while (line != null) {
                if (line.indexOf(match) != -1) {
                    output += "     " + secondCounter + "." + tasks.getTasks().get(counter).toString() + "\n";
                    secondCounter++;
                }
                line = reader.readLine();
                counter++;
            }
        } else if (commandName.equals("bye")) {
            output = "     Bye. Hope to see you again soon!\n";
        } else {
            output = "      OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
        return output;
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
