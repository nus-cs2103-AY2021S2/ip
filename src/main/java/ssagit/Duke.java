package ssagit;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class Duke {
    /**
     * Exception class for missing todoTask descriptor
     */
    static class MissingTodoDescriptorException extends Exception {
        public MissingTodoDescriptorException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Exception class for unknown input parameters
     */
    static class UnknownInputParamException extends Exception {
        public UnknownInputParamException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Duke speaks in chat boxes
     *
     * @param str input string within chat boxes
     */
    static void formatBox(String str) {
        System.out.println("------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------");
    }

    /**
     * Prints the tasks in the array of tasks
     * @param taskArr array of Task objects
     */
    static void list(Task[] taskArr) {
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("Here are the tasks in your list: ");
        System.out.println("TaskType | isDone | taskName | time (if any)");
        for (Task t : taskArr) {
            if (t == null) break;
            System.out.println(t.toString());
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++");
    }

    /**
     * Removes all content of file and writes the current Tasks available to file
     * @param taskArr array of Task objects
     * @param absolutePath absolute path of file location
     */
    static void recreateFile(Task[] taskArr, FileWriter fw, Path absolutePath) {
        try {
            new FileOutputStream(absolutePath.toString()).close(); // delete file contents
            for (Task t : taskArr) {
                if (t == null) break;
                fw.write(t.toString() + "\n");
                fw.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException has occurred");
            e.printStackTrace();
        }
    }

    /**
     * Reads a file into the program and parses each line into a Task and puts it into taskArr
     * @param taskArr array of Task objects
     * @param relativePath relative path of file location
     * @return
     */
    static int readTaskListToArray(Task[] taskArr, Path relativePath) {
        int taskIterator = 0;
        try {
            File taskList = new File("./ssagit/data/taskList.txt");
            taskList.createNewFile(); // creates the file if it doesn't exist

            List<String> list = Files.readAllLines(Paths.get(relativePath.toString()));
            String[] taskListStr = list.toArray(new String[list.size()]);

            for (String str : taskListStr) {
                String[] strArr = str.split(" \\| ");
                String taskType = strArr[0];
                String isDoneStr = strArr[1];
                String taskName = strArr[2];

                if (strArr.length == 3) {
                    taskArr[taskIterator] = new Task(taskName, isDoneStr.equals("done"));
                } else if (taskType.equals("event")) {
                    taskArr[taskIterator] = new EventTask(taskName, isDoneStr.equals("done"), strArr[3].trim());
                } else if (taskType.equals("deadline")) {
                    taskArr[taskIterator] = new DeadlineTask(taskName, isDoneStr.equals("done"), strArr[3].trim());
                }
                taskIterator++;
            }
            return taskIterator;
        } catch (IOException e) {
            System.out.println("IOException has occurred");
            e.printStackTrace();
        }

        return taskIterator;
    }

    public static void main(String[] args) {
        try {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            String introduction = "I'm Duke!\nWhat can I do for ya?\n";
            formatBox(introduction);

            // inits
            Scanner sc = new Scanner(System.in);
            Task[] taskArr = new Task[100];
            String input;
            Path relativePath = Paths.get("./ssagit/data/taskList.txt");
            Path absolutePath = relativePath.toAbsolutePath();
            int taskIterator = readTaskListToArray(taskArr, relativePath);
            FileWriter fw = new FileWriter(absolutePath.toString());

            while (true) {
                input = sc.nextLine();
                String inputArr[] = input.split(" ");
                if (inputArr[0].equals("bye"))
                    break;
                else if (inputArr[0].equals("list")) {
                    list(taskArr);
                } else if (inputArr[0].equals("done")) {
                    int taskNum = Integer.parseInt(inputArr[1]) - 1;
                    taskArr[taskNum].markDone();
                    formatBox("Nice! I've marked this task as done:\n" + taskArr[taskNum].toFormattedString());
                } else if (inputArr[0].equals("todo") || inputArr[0].equals("event") ||
                        inputArr[0].equals("deadline")) {
                    // add to list
                    String[] inputArrTasks = input.split("/");
                    String[] firstHalf = inputArrTasks[0].split(" ", 2);

                    if (inputArrTasks.length != 1) {
                        // create Deadline/Event
                        String[] secondHalf = inputArrTasks[1].split(" ", 2);
                        if (inputArr[0].equals("event")) {
                            taskArr[taskIterator] = new EventTask(firstHalf[1], false, secondHalf[1]);
                        } else if (inputArr[0].equals("deadline")) {
                            taskArr[taskIterator] = new DeadlineTask(firstHalf[1], false, secondHalf[1]);
                        }
                    } else {
                        // create todoTask
                        if (firstHalf.length == 1) {
                            throw new MissingTodoDescriptorException("------------------------------------\n" +
                                    ":( OOPS!!! The description of a todo cannot be empty\n" +
                                    "------------------------------------");
                        } else {
                            taskArr[taskIterator] = new Task(firstHalf[1], false);
                        }
                    }
                    taskIterator++; // increase task count in list
                    // print output
                    String formattedInput = "Got it. I've added this task:\n  ";
                    formattedInput = formattedInput.concat(taskArr[taskIterator - 1].toFormattedString()).concat("\n");
                    formattedInput = formattedInput.concat("Now you have " + Integer.toString(taskIterator) + " tasks in the list.");
                    formatBox(formattedInput);

                    // add new task to file
                    fw.append(taskArr[taskIterator - 1].toString() + "\n");
                    fw.flush();
                } else if (inputArr[0].equals("delete")) {
                    int removeIndex = Integer.parseInt(inputArr[1]);
                    taskIterator--; // reduce task count in list
                    String formattedInput = "Got it. I've removed this task:\n  ";
                    formattedInput = formattedInput.concat(taskArr[removeIndex - 1].toFormattedString()).concat("\n");
                    formattedInput = formattedInput.concat("Now you have " + Integer.toString(taskIterator) + " tasks in the list.");
                    formatBox(formattedInput);

                    // actually delete the task and move all other tasks forward
                    for (int i = removeIndex - 1; i < taskArr.length - 1; i++) {
                        taskArr[i] = taskArr[i + 1];
                    }
                    recreateFile(taskArr, fw, absolutePath);
                } else {
                    throw new UnknownInputParamException("------------------------------------\n" +
                            ":( OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "------------------------------------");
                }
            }

            fw.flush();
            fw.close();
            String bye = "Bye. Hope to see you again soon!";
            formatBox(bye);
        } catch (MissingTodoDescriptorException e) {
            System.out.println(e.getMessage());
        } catch (UnknownInputParamException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException has occurred");
            e.printStackTrace();
        }
    }
}
