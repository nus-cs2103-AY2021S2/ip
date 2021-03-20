package duke.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class Command {
//    /**
//     * The default data path of the file storing all tasks in this project
//     */
//    final String dataPath = "data/duke.txt";
//    /**
//     * The starting index of the name of a todo task
//     */
//    final int todoLength = 5;
//    /**
//     * The starting index of the name of a event task
//     */
//    final int eventLength = 6;
//    /**
//     * The starting index of the name of a deadline task
//     */
//    final int deadlineLength = 9;
//    /**
//     * The input command by user
//     */
//    private String commandName;
//
//    /**
//     * Class constructor.
//     */
//    public Command(String commandName) {
//        this.commandName = commandName;
//    }
//
//    public Command() {
//
//    }
//
//    /**
//     * Processes tasks according to the command.
//     *
//     * @param tasks the tasks before execution.
//     * @param storage
//     * @return all the processed tasks.
//     * @throws IOException If an input or output
//     *                     exception occurred
//     */
//    public String execute(TaskList tasks, Storage storage) throws IOException, DukeException {
//        ArrayList<Task> tList = tasks.getTasks();
//        String output = "";
//        if (commandName.equals("list")) {
//            output = executeList(tList);
//        } else if (commandName.split(" ")[0].equals("done")) {
//            output = executeDone(tList, tasks);
//        } else if (commandName.split(" ")[0].equals("delete")) {
//            output = executeDelete(tList, tasks);
//        } else if (commandName.split(" ")[0].equals("todo")) {
//            output = executeToDo(tList, tasks);
//        } else if (commandName.split(" ")[0].equals("deadline")) {
//            output = executeDeadline(tList, tasks);
//        } else if (commandName.split(" ")[0].equals("event")) {
//            output = executeEvent(tList, tasks);
//        } else if (commandName.split(" ")[0].equals("find")) {
//            output = executeFind(tasks);
//        } else if (commandName.equals("bye")) {
//            output = "Bye. Hope to see you again soon!\n";
//        } else if (commandName.equals("")) {
//            output = "Please enter something!\n";
//        } else if (commandName.equals("help")) {
//            output = executeHelp();
//        } else {
//            output = "OOPS!!! I'm sorry, but I don't know what that means :-( \n\n";
//            output += "Enter 'help' to see all the commands.";
//        }
//        assert output != "" : "output is null";
//        return output;
//    }
//    /**
//     * Returns all the available commands.
//     *
//     * @return all the available commands with an example for user reference.
//     */
//    public String executeHelp() {
//        String output = "Duke User Guide: \n";
//        output += "1. `list` - List all the tasks \n";
//        output += "2. `todo` - Create a ToDO task \n (e.g. `todo CS2103 ip`) \n";
//        output += "3. `deadline` - Create a Deadline task \n (e.g. `deadline CS2103 Quiz /by 2021-02-19`) \n";
//        output += "4. `event` - Create an Event task \n (e.g. `event CS2103 Lecture /at 2021-02-19`) \n";
//        output += "5. `done` - Mark a task as done \n (e.g. `done 3`) \n";
//        output += "6. `delete` - Delete a task \n (e.g. `delete 3`) \n";
//        output += "7. `find` - Find a task \n (e.g. `find CS2103`) \n";
//        output += "8. `bye` - Quit the program \n";
//        return output;
//    }

//    /**
//     * Finds one specified task from the task list.
//     *
//     * @param tList the copy of tasks before execution.
//     * @param task  the task to look for.
//     * @return the found task if it exists.
//     */
//    public Task findExact(ArrayList<Task> tList, Task task) {
//        for (Task t : tList) {
//            if (t.getName().equals(task.getName())) {
//                return t;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Adds a todo task into the task list.
//     *
//     * @param tList the copy of tasks before execution.
//     * @param tasks the tasks before execution.
//     * @return all tasks after adding one todo task.
//     * @throws DukeException If an input or output
//     *                       exception occurred
//     */
//    public String executeToDo(ArrayList<Task> tList, TaskList tasks) throws DukeException {
//        String output = "";
//        int spaceIndex = commandName.indexOf(" ");
//        if (commandName.length() <= todoLength
//                || commandName.substring(spaceIndex).trim().equals("")) {
//            output = "OOPS!!! Task name cannot be empty";
//            return output;
//        }
//        int end = commandName.indexOf(" ");
//        String name = commandName.substring(end + 1);
//        Task newTask = new ToDo(name);
//        if (findExact(tList, newTask) != null) {
//            output = "OOPS!!! This Task has already existed. \n";
//            return output + findExact(tList, newTask).toString();
//        }
//        tList.add(newTask);
//        output = tList.get(tList.size() - 1).addTask(tList.size());
//        output += writeFile("T | 0 | " + name + "\n");
//        tasks.setTasks(tList);
//        return output;
//    }
//
//    /**
//     * Adds a task into the date file.
//     *
//     * @param task the task to be added.
//     * @return empty string if task added successfully.
//     */
//    public String writeFile(String task) {
//        try {
//            BufferedWriter writer = new BufferedWriter(
//                    new FileWriter(dataPath, true));
//            writer.write(task);
//            writer.close();
//            return "";
//        } catch (IOException e) {
//            return e.getMessage();
//        }
//
//    }
//
//    /**
//     * Adds a deadline task into the task list.
//     *
//     * @param tList the copy of tasks before execution.
//     * @param tasks the tasks before execution.
//     * @return all tasks after adding one deadline task.
//     * @throws DukeException If an input or output
//     *                       exception occurred
//     */
//    public String executeDeadline(ArrayList<Task> tList, TaskList tasks) throws DukeException {
//        String output = "";
//        try {
//            int spaceIndex = commandName.indexOf(" ");
//            int slashIndex = commandName.indexOf("/");
//            String taskName = "";
//            String taskDate = "";
//            if (commandName.length() <= deadlineLength
//                    || commandName.substring(spaceIndex, slashIndex).trim().equals("")) {
//                return "OOPS!!! Deadline name cannot be empty";
//            }
//            Task newTask = getTaskFromCommand("Deadline", spaceIndex, slashIndex, taskName, taskDate);
//            if (findExact(tList, newTask) != null) {
//                output = "OOPS!!! This Task has already existed. \n";
//                return output + findExact(tList, newTask).toString();
//            }
//            tList.add(newTask);
//            output = tList.get(tList.size() - 1).addTask(tList.size());
//            output += writeFile("D | 0 | " + taskName + " | " + taskDate + "\n");
//            tasks.setTasks(tList);
//        } catch (StringIndexOutOfBoundsException e) {
//            output += "OOPS!!! The due date of a deadline "
//                    + "cannot be empty. (Format: /by + date[YYYY-MM-DD])";
//        }
//        return output;
//    }
//
//    public Task getTaskFromCommand(String type, int spaceIndex, int slashIndex, String taskName, String taskDate) {
//        taskName = commandName.substring(spaceIndex + 1, slashIndex - 1);
//        taskDate = commandName.substring(slashIndex + 4);
//        int year = Integer.valueOf(taskDate.substring(0, 4));
//        int mon = Integer.valueOf(taskDate.substring(5, 7));
//        int day = Integer.valueOf(taskDate.substring(8));
//        Task newTask;
//        if (type.equals("Deadline")) {
//            newTask = new Deadline(taskName, LocalDate.of(year, mon, day));
//        } else {
//            newTask = new Event(taskName, LocalDate.of(year, mon, day));
//        }
//        return newTask;
//    }
//
//
//    /**
//     * Adds a event task into the task list.
//     *
//     * @param tList the copy of tasks before execution.
//     * @param tasks the tasks before execution.
//     * @return all tasks after adding one event task.
//     * @throws DukeException If an input or output
//     *                       exception occurred
//     */
//    public String executeEvent(ArrayList<Task> tList, TaskList tasks) throws DukeException {
//        String output = "";
//        try {
//            int spaceIndex = commandName.indexOf(" ");
//            int slashIndex = commandName.indexOf("/");
//            if (commandName.length() <= eventLength
//                    || commandName.substring(spaceIndex, slashIndex).trim().equals("")) {
//                return "OOPS!!! Event name cannot be empty";
//            }
//            String taskName = "";
//            String taskDate = "";
//            Task newTask = getTaskFromCommand("Event", spaceIndex, slashIndex, taskName, taskDate);
//            if (findExact(tList, newTask) != null) {
//                output = "OOPS!!! This Task has already existed. \n";
//                return output + findExact(tList, newTask).toString();
//            }
//            tList.add(newTask);
//            output = tList.get(tList.size() - 1).addTask(tList.size());
//            output += writeFile("E | 0 | " + taskName + " | " + taskDate + "\n");
//            tasks.setTasks(tList);
//        } catch (StringIndexOutOfBoundsException e) {
//            output += "OOPS!!! The start and end date of "
//                    + "an event cannot be empty.(Format: /at + duration[YYYY-MM-DD])";
//        }
//        return output;
//    }
//
//    /**
//     * Finds all the tasks which partially or fully match the given task from the task list .
//     *
//     * @param tasks the tasks to look for.
//     * @return all the found tasks if any exists.
//     * @throws IOException If an input or output
//     *                     exception occurred
//     */
//    public String executeFind(TaskList tasks) throws IOException {
//        String output;
//        String match = commandName.split(" ")[1];
//        BufferedReader reader = new BufferedReader(new FileReader(dataPath));
//        String line = reader.readLine();
//        int allTaskCounter = 0;
//        int matchTaskCounter = 1;
//        while (line != null) {
//            if (line.indexOf(match) != -1) {
//                matchTaskCounter++;
//                break;
//            }
//            line = reader.readLine();
//            allTaskCounter++;
//        }
//        if (matchTaskCounter == 1) {
//            output = "Here is no matching task in your list.\n";
//            return output;
//        }
//        matchTaskCounter = 1;
//        output = "Here are the matching tasks in your list:\n";
//        while (line != null) {
//            if (line.indexOf(match) != -1) {
//                output += matchTaskCounter + "." + tasks.getTasks().get(allTaskCounter).toString() + "\n";
//                matchTaskCounter++;
//            }
//            line = reader.readLine();
//            allTaskCounter++;
//        }
//        return output;
//    }
        public String execute(TaskList tasks, Storage storage) throws IOException {
            return "";
        }
//
//        public String execute() {
//            return "";
//        }
}
