package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Parser {
    /**
     * Handle each line
     *
     * @param line the current line to process
     * @param tasks the current task list to process
     * @return response of the command
     */
    public String processLine(String line, TaskList tasks) {
        try {
            if (line.compareTo("bye") == 0) {
                // Bye command, print and exit immediately.
                return "Bye!";
            } else if (line.compareTo("list") == 0) {
                return commandList(line, tasks);
            } else if (line.startsWith("done ")) {
                return commandDone(line, tasks);
            } else if (line.startsWith("delete ")) {
                return commandDelete(line, tasks);
            } else if (line.startsWith("find ")) {
                return commandFind(line, tasks);
            } else if (line.startsWith("tag ")) {
                return commandTag(line, tasks);
            } else {
                // No command, add the line task based on the prefix inside.
                return processNewTask(line, tasks);
            }
        } catch (NumberFormatException e) {
            return "Task index must be a number!";
        } catch (IndexOutOfBoundsException e) {
            return "Task index must be in range!";
        } catch (DukeUnknownCommandException e) {
            return "Unknown command detected, ignoring!";
        } catch (DukeEmptyDescriptionException e) {
            return "Task description cannot be empty, ignoring!";
        } catch (DukeException e) {
            return "Reached an error!";
        } catch (DateTimeParseException e) {
            return "Invalid Date Format!";
        }
    }

    /**
     * List command, print out all the previous lines.
     *
     * @param line the current line to process
     * @param tasks the current task list to process
     * @return response of the command
     */
    public String commandList(String line, TaskList tasks) {
        String response = IntStream.range(0, tasks.size())
                .mapToObj(i -> String.format("%d. %s\n", i + 1, tasks.get(i)))
                .collect(Collectors.joining());
        return response;
    }

    /**
     * Done command, set the task as done.
     *
     * @param line the current line to process
     * @param tasks the current task list to process
     * @return response of the command
     */
    public String commandDone(String line, TaskList tasks) {
        String indexStr = line.substring(5);
        int index = Integer.parseInt(indexStr) - 1;
        AbstractTask currentTask = tasks.get(index);
        currentTask.markDone();
        return String.format("Marked task %d as done:\n%s\n", index, currentTask);
    }

    /**
     * Delete command, delete the task.
     *
     * @param line the current line to process
     * @param tasks the current task list to process
     * @return response of the command
     */
    public String commandDelete(String line, TaskList tasks) {
        String indexStr = line.substring(7);
        int index = Integer.parseInt(indexStr) - 1;
        String response = String.format("Deleted task %d: %s\n", index, tasks.get(index));
        tasks.remove(index);
        return response;
    }

    /**
     * Find string in the list of tasks
     *
     * @param line the current line to process
     * @param tasks the current task list to process
     * @return response of the command
     */
    public String commandFind(String line, TaskList tasks) {
        String findStr = line.substring(5);
        boolean isFound = tasks.stream()
                .map(AbstractTask::toString)
                .anyMatch(x -> x.contains(findStr));
        if (!isFound) {
            return "No tasks with the keyword found!";
        }
        String response = "I've found the following task(s) with the specified keyword:\n";
        ArrayList<String> foundTasks = tasks.stream()
                .map(AbstractTask::toString)
                .filter(x -> x.contains(findStr))
                .collect(Collectors.toCollection(ArrayList::new));
        response += IntStream.range(0, foundTasks.size())
                .mapToObj(x -> String.format("%d. %s\n", x + 1, foundTasks.get(x)))
                .collect(Collectors.joining());
        return response;
    }

    /**
     * Add a tag to a task
     *
     * @param line the current line to process
     * @param tasks the current task list to process
     * @return response of the command
     */
    public String commandTag(String line, TaskList tasks) {
        String[] tokens = line.split(" ");
        assert(tokens[0].equals("find"));

        if (tokens.length < 4) {
            return "Invalid command for tag!";
        }
        int taskIdx = Integer.parseInt(tokens[2]) - 1;
        if (tokens[1].equals("add")) {
            tasks.get(taskIdx).addTag(tokens[3]);
        } else if (tokens[1].equals("delete")) {
            tasks.get(taskIdx).deleteTag(tokens[3]);
        } else {
            return "Invalid tag command!";
        }
        return "Task tag " + tokens[1] + " complete";
    }

    /**
     * Handle a new task
     *
     * @param line the current task to process
     * @param tasks the current task list to process
     */
    public String processNewTask(String line, TaskList tasks) throws DukeException {
        if (line.startsWith("todo ")) {
            // Todo command, add a Todo class
            line = line.substring(5);
            tasks.add(new Todo(line));
        } else if (line.startsWith("deadline ")) {
            // Deadline command, add a Deadline class
            int byIdx = line.indexOf(" /by ");
            if (byIdx == -1) {
                return "Need a time for the deadline, \"/by\" not found!";
            }
            String byStr = line.substring(byIdx + 5);
            String task = line.substring(9, byIdx);
            tasks.add(new Deadline(task, byStr));
        } else if (line.startsWith("event ")) {
            // Event command, add an Event class
            int atIdx = line.indexOf(" /at ");
            if (atIdx == -1) {
                return "Need a time for the event, \"/at\" not found!";
            }
            String atStr = line.substring(atIdx + 5);
            String task = line.substring(6, atIdx);
            tasks.add(new Event(task, atStr));
        } else if (line.equals("todo") || line.equals("deadline") || line.equals("event")) {
            throw new DukeEmptyDescriptionException();
        } else {
            throw new DukeUnknownCommandException();
        }
        String response = String.format("Now you have %d tasks.\n", tasks.size());
        response += "added: " + tasks.get(tasks.size() - 1);
        return response;
    }
}
