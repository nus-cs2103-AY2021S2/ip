package duke.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.dukeException.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class Command {
    /** The input command by user */
    private String commandName;
    final String DATA_PATH  = "data/duke.txt";
    final int todoLength = 5;
    final int eventLength = 6;
    final int deadlineLength = 9;


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
    public String execute(TaskList tasks) throws IOException, DukeException, DukeException {
        ArrayList<Task> tList = tasks.getTasks();
        String output = "";
        if (commandName.equals("list")) {
            output = executeList (tList);
        } else if (commandName.split(" ")[0].equals("done")) {
            output = executeDone (tList, tasks);
        } else if (commandName.split(" ")[0].equals("delete")) {
            output = executeDelete (tList, tasks);
        } else if (commandName.split(" ")[0].equals("todo")) {
            output = executeToDo (tList, tasks);
        } else if (commandName.split(" ")[0].equals("deadline")) {
            output = executeDeadline (tList, tasks);
        } else if (commandName.split(" ")[0].equals("event")) {
            output = executeEvent (tList, tasks);
        } else if (commandName.split(" ")[0].equals("find")) {
            output  = executeFind (tasks);
        } else if (commandName.equals("bye")) {
            output = "     Bye. Hope to see you again soon!\n";
        } else if(commandName.equals("")) {
            output = "      Please enter something!\n";
        } else {
            output = "      OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
        assert output != "" : "output is null";
        return output;
    }

    public String executeList(ArrayList<Task> tList) {
        String output = "     Here are the tasks in your list:\n";
        int taskCounter = 1;
        for (Task s : tList) {
            if (s != null) {
                output += "     " + taskCounter + "." + s.toString() + "\n";
                taskCounter++;
            } else {
                break;
            }
        }
        return output;
    }

    public String executeDone(ArrayList<Task> tList, TaskList tasks) throws IOException {
        String output = "";
        try {
            int index = Integer.parseInt(commandName.split(" ")[1]);
            tList.get(index - 1).markAsDone();
            output = "     Nice! I've marked this task as done:\n";
            output += "       " + tList.get(index - 1).toString() + "\n";
            Task currTask = tList.get(index - 1);
            tasks.setTasks(tList);
            String oldTask = null;
            if (currTask instanceof ToDo) {
                oldTask = "T | 0 | " + currTask.getName();
            } else if (currTask instanceof Deadline) {
                oldTask = "D | 0 | " + currTask.getName();
            } else {
                assert currTask instanceof Event;
                oldTask = "E | 0 | " + currTask.getName();
            }
            BufferedReader reader = new BufferedReader(new FileReader(DATA_PATH));
            String oldContent = "";
            String line = reader.readLine();
            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = null;
            if (currTask instanceof ToDo) {
                newContent = oldContent.replace(oldTask, "T | 1 | " + currTask.getName());
            } else if (currTask instanceof Deadline) {
                newContent = oldContent.replace(oldTask, "D | 1 | " + currTask.getName());
            } else {
                assert currTask instanceof Event;
                newContent = oldContent.replace(oldTask, "E | 1 | " + currTask.getName());
            }
            FileWriter writer = new FileWriter(DATA_PATH);
            writer.write(newContent);
            reader.close();
            writer.close();

        } catch (IOException e) {
            output += e.getMessage();
        } catch (NumberFormatException e) {
            output += "Please enter a valid number!";
        }
        return output;




    }

    public String executeDelete(ArrayList<Task> tList, TaskList tasks) throws IOException {
        String output = "";
        try {
            int index = Integer.parseInt(commandName.split(" ")[1]);
            output = "     Noted. I've removed this task:\n";
            output += "       " + tList.get(index - 1).toString() + "\n";
            output += "     Now you have " + (tList.size() - 1) + " tasks in the list.\n";
            tList.remove(index - 1);
            tasks.setTasks(tList);
            BufferedReader reader = new BufferedReader(new FileReader(DATA_PATH));
            String oldContent = "";
            String line = reader.readLine();
            int taskCounter = 0;
            while (line != null) {
                if (taskCounter != index - 1) {
                    oldContent = oldContent + line + System.lineSeparator();
                }
                taskCounter++;
                line = reader.readLine();
            }
            FileWriter writer = new FileWriter(DATA_PATH);
            writer.write(oldContent);
            reader.close();
            writer.close();
        } catch (IOException e) {
            output += e.getMessage();
        } catch (NumberFormatException e) {
            output += "Please enter a valid number!";
        }
        return output;
    }

    public String executeToDo (ArrayList<Task> tList, TaskList tasks) throws DukeException {
        String output = "";
        if (commandName.length() < todoLength) {
            ToDo todo = new ToDo(commandName);
            output = todo.addTask(0);
        } else {
            int end = commandName.indexOf(" ");
            String name = commandName.substring(end + 1);
            try {
                tList.add(new ToDo(name));
                output = tList.get(tList.size() - 1).addTask(tList.size());
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(DATA_PATH, true));
                writer.write("T | 0 | " + name + "\n");
                writer.close();
                tasks.setTasks(tList);
            } catch (DukeException | IOException e) {
                output += e.getMessage();
            }
        }
        return output;
    }

    public String executeDeadline (ArrayList<Task> tList, TaskList tasks) throws DukeException {
        String output = "";
        if (commandName.length() < deadlineLength) {
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
                        new FileWriter(DATA_PATH, true));
                writer.write("D | 0 | " + subString1 + " | " + subString2 + "\n");
                writer.close();
                tasks.setTasks(tList);
            } catch (DukeException e) {
                output += e.getMessage();
            } catch (StringIndexOutOfBoundsException | IOException e) {
                output += "      OOPS!!! The due date of a deadline "
                        + "cannot be empty. (Format: /by + date[YYYY-MM-DD])";
            }
        }
        return output;
    }

    public String executeEvent (ArrayList<Task> tList, TaskList tasks) throws DukeException {
        String output = "";
        if (commandName.length() < eventLength) {
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
                        new FileWriter(DATA_PATH, true));
                writer.write("E | 0 | " + subString1 + " | " + subString2 + "\n");
                writer.close();
                tasks.setTasks(tList);
            } catch (DukeException e) {
                output += e.getMessage();
            } catch (StringIndexOutOfBoundsException | IOException e) {
                output += "      OOPS!!! The start and end date of "
                        + "an event cannot be empty.(Format: /at + duration[YYYY-MM-DD])";
            }
        }
        return output;
    }

    public String executeFind (TaskList tasks) throws IOException {
        String output;
        String match = commandName.split(" ")[1];
        BufferedReader reader = new BufferedReader(new FileReader(DATA_PATH));
        String line = reader.readLine();
        int allTaskCounter = 0;
        int matchTaskCounter = 1;
        output = "     Here are the matching tasks in your list:\n";
        while (line != null) {
            if (line.indexOf(match) != -1) {
                output += "     " + matchTaskCounter + "." + tasks.getTasks().get(allTaskCounter).toString() + "\n";
                matchTaskCounter++;
            }
            line = reader.readLine();
            allTaskCounter++;
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
