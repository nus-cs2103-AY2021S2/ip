package duke;

import java.time.format.DateTimeParseException;

public class Parser {

    /*
     * Handle each line
     *
     * @param line the current line to process
     */
    public int processLine(String line, TaskList tasks) {
        if (line.compareTo("bye") == 0) {
            // Bye command, print and exit immediately.
            System.out.println("\tDuke:");
            System.out.println("\tBye!");
            return -1;
        } else if (line.compareTo("list") == 0) {
            // List command, print out all the previous lines.
            System.out.println("\tDuke:");
            for(int i = 0;i < tasks.size();i++) {
                System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
            }
        } else if (line.startsWith("done ")) {
            // Done command, set the task as done.
            String indexStr = line.substring(5);
            try {
                int index = Integer.parseInt(indexStr) - 1;
                AbstractTask currentTask = tasks.get(index);
                currentTask.markDone();
                System.out.printf("\tDuke: Marked task %d as done:\n", index);
                System.out.printf("\t%s\n", currentTask);
            } catch (NumberFormatException e){
                System.out.println("Task index must be a number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task index must be in range!");
            }
        } else if (line.startsWith("delete ")) {
            // Done command, set the task as done.
            String indexStr = line.substring(7);
            try {
                int index = Integer.parseInt(indexStr) - 1;
                System.out.printf("\tDuke: Deleted task %d: %s\n", index, tasks.get(index));
                tasks.remove(index);
            } catch (NumberFormatException e){
                System.out.println("Task index must be a number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task index must be in range!");
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
                System.out.println("No tasks with the keyword found!");
            } else {
                System.out.println("\tI've found the following task(s) with the specified keyword:");
                for (AbstractTask task : tasks) {
                    if (task.toString().contains(findStr)) {
                        index++;
                        System.out.printf("\t%d. %s\n", index, task.toString());
                    }
                }
            }
        } else {
            // No command, add the line task based on the prefix inside.
            try {
                processNewTask(line, tasks);
            } catch (DukeUnknownCommandException e) {
                //Handle Unknown Command Exception
                System.out.println("Unknown command detected, ignoring!");
            } catch (DukeEmptyDescriptionException e) {
                //Handle Empty Description Exception
                System.out.println("Task description cannot be empty, ignoring!");
            } catch (DukeException e) {
                System.out.println("Reached an error!");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date Format!");
            }
        }
        return 0;
    }

    /*
     * Handle a new task
     *
     * @param line the current task to process
     */
    public void processNewTask(String line, TaskList tasks) throws DukeException {
        if (line.startsWith("todo ")) {
            // Todo command, add a Todo class
            line = line.substring(5);
            tasks.add(new Todo(line));
        } else if (line.startsWith("deadline ")) {
            // Deadline command, add a Deadline class
            int byIdx = line.indexOf(" /by ");
            if (byIdx == -1) {
                System.out.println("Need a time for the deadline, \"/by\" not found!");
                return;
            }
            String byStr = line.substring(byIdx + 5);
            String task = line.substring(9, byIdx);
            tasks.add(new Deadline(task, byStr));
        } else if (line.startsWith("event ")) {
            // Event command, add an Event class
            int atIdx = line.indexOf(" /at ");
            if (atIdx == -1) {
                System.out.println("Need a time for the event, \"/at\" not found!");
                return;
            }
            String atStr = line.substring(atIdx + 5);
            String task = line.substring(6, atIdx);
            tasks.add(new Event(task, atStr));
        } else if (line.equals("todo") || line.equals("deadline") || line.equals("event")) {
            throw new DukeEmptyDescriptionException();
        } else {
            throw new DukeUnknownCommandException();
        }
        System.out.printf("\tDuke: Now you have %d tasks.\n", tasks.size());
        System.out.println("\tadded: " + tasks.get(tasks.size() - 1));
    }
}
