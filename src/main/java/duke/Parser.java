package duke;

import java.io.IOException;

/**
 * Contains the main logic for responses by Duke.
 */
public class Parser {
    /**
     * Executes operations on the task list based on given input and
     * returns the response by the program.
     *
     * @param input Input from user.
     * @param tasks List of tasks.
     * @return Program response as a String.
     * @throws DukeException If input is not recognised.
     */
    public static String process(String input, TaskList tasks) throws DukeException {
        if (input.equals("bye")) {
            return null;
        }

        StringBuilder output = new StringBuilder();
        if (input.equals("list")) {
            output.append("  Tasks in list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append("  ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
        } else if (input.split(" ")[0].equals("done")) {
            int inputNumber;
            try {
                inputNumber = Integer.parseInt(input.split(" ")[1]);
            } catch (Exception ex) {
                throw new DukeException("  Please provide an integer.");
            }

            try {
                tasks.markAsDone(inputNumber - 1);
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("  That number is invalid.");
            } catch (IOException ex) {
                throw new DukeException("  Unable to save tasks.");
            }

            output.append("  Successfully marked as done:\n");
            output.append("  ").append(tasks.get(inputNumber - 1)).append("\n");
        } else if (input.split(" ")[0].equals("delete")) {
            int inputNumber;
            try {
                inputNumber = Integer.parseInt(input.split(" ")[1]);
            } catch (Exception ex) {
                throw new DukeException("  Please provide an integer.");
            }

            Task taskToRemove;
            try {
                taskToRemove = tasks.remove(inputNumber - 1);
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("  That number is invalid.");
            } catch (IOException ex) {
                throw new DukeException("  Unable to save tasks.");
            }

            output.append("  Successfully removed:\n");
            output.append("    ").append(taskToRemove).append("\n");
            output.append("  Total tasks in list: ").append(tasks.size()).append("\n");
        } else if (input.split(" ")[0].equals("find")) {
            String inputString;
            try {
                inputString = input.split(" ")[1].toLowerCase();
            } catch (Exception ex) {
                throw new DukeException("  Please provide a keyword to search for.");
            }

            output.append("  Matching tasks in list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                for (String word : tasks.get(i).toString().split(" ")) {
                    if (inputString.equals(word.toLowerCase())) {
                        output.append("  ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
                    }
                }
            }
        } else {
            Task task;
            switch (input.split(" ")[0]) {
            case "todo":
                if (input.split(" ").length <= 1) {
                    throw new DukeException("  Please describe the task.");
                }
                task = new Todo(input.substring(5));
                break;
            case "deadline": {
                if (input.split(" ").length <= 1) {
                    throw new DukeException("  Please describe the task.");
                }
                String[] inputs = input.substring(9).split("/by");
                String name = inputs[0] + "(by:" + inputs[1] + ")";
                task = new Deadline(name);
                break;
            }
            case "event": {
                if (input.split(" ").length <= 1) {
                    throw new DukeException("  Please describe the task.");
                }
                String[] inputs = input.substring(6).split("/at");
                String name = inputs[0] + "(at:" + inputs[1] + ")";
                task = new Event(name);
                break;
            }
            default:
                throw new DukeException("  That command is invalid.");
            }

            try {
                tasks.add(task);
            } catch (IOException ex) {
                throw new DukeException("  Unable to save tasks.");
            }

            output.append("  Task added:\n");
            output.append("    ").append(task).append("\n");
            output.append("  Total tasks in list: ").append(tasks.size()).append("\n");
        }
        return output.toString();
    }
}
