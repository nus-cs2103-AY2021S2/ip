package duke;

import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Handle each line
     *
     * @param line the current line to process
     * @return response of the command
     */
    public String processLine(String line, TaskList tasks) {
        if (line.compareTo("bye") == 0) {
            // Bye command, print and exit immediately.
            return "Bye!";
        } else if (line.compareTo("list") == 0) {
            // List command, print out all the previous lines.
            String response = "";
            for (int i = 0; i < tasks.size(); i++) {
                response += String.format("%d. %s\n", i + 1, tasks.get(i));
            }
            return response;
        } else if (line.startsWith("done ")) {
            // Done command, set the task as done.
            String indexStr = line.substring(5);
            try {
                int index = Integer.parseInt(indexStr) - 1;
                AbstractTask currentTask = tasks.get(index);
                currentTask.markDone();
                return String.format("Marked task %d as done:\n%s\n", index, currentTask);
            } catch (NumberFormatException e) {
                return "Task index must be a number!";
            } catch (IndexOutOfBoundsException e) {
                return "Task index must be in range!";
            }
        } else if (line.startsWith("delete ")) {
            // Done command, set the task as done.
            String indexStr = line.substring(7);
            try {
                int index = Integer.parseInt(indexStr) - 1;
                String response = String.format("Deleted task %d: %s\n", index, tasks.get(index));
                tasks.remove(index);
                return response;
            } catch (NumberFormatException e) {
                return "Task index must be a number!";
            } catch (IndexOutOfBoundsException e) {
                return "Task index must be in range!";
            }
        } else if (line.startsWith("find ")) {
            // Find string in the list of tasks
            String findStr = line.substring(5);
            int index = 0;
            boolean isFound = false;
            for (AbstractTask task : tasks) {
                if (task.toString().contains(findStr)) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                return "No tasks with the keyword found!";
            } else {
                String response = "I've found the following task(s) with the specified keyword:\n";
                for (AbstractTask task : tasks) {
                    if (task.toString().contains(findStr)) {
                        index++;
                        response += String.format("%d. %s\n", index, task.toString());
                    }
                }
                return response;
            }
        } else {
            // No command, add the line task based on the prefix inside.
            try {
                return processNewTask(line, tasks);
            } catch (DukeUnknownCommandException e) {
                //Handle Unknown Command Exception
                return "Unknown command detected, ignoring!";
            } catch (DukeEmptyDescriptionException e) {
                //Handle Empty Description Exception
                return "Task description cannot be empty, ignoring!";
            } catch (DukeException e) {
                return "Reached an error!";
            } catch (DateTimeParseException e) {
                return "Invalid Date Format!";
            }
        }
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
