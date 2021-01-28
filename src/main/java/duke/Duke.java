package duke;

import java.util.Scanner;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.exceptions.MissingArgumentException;
import duke.exceptions.NoKeywordException;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    public static final String DIRECTORY = System.getProperty("user.dir");
    public ArrayList<Task> taskList;

    public Duke() {
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        String name = "Alfred";
        String input;

        String logo = "     __      _     ____                  _\n"
                + "    /  \\    | |   |  __| _____  ____    | |\n"
                + "   / /\\ \\   | | __| |__ |  ___|/ __ \\   | |\n"
                + "  / /__\\ \\  | ||__   __|| |   / ____/ __| |\n"
                + " / ______ \\ | |   | |   | |   \\ \\___ / _  |\n"
                + "/_/      \\_\\|_|   |_|   |_|    \\____|\\____|\n";

        System.out.println(logo);
        System.out.println("Hello Master. Nice to meet you, my name is " + name + ".");
        System.out.println("How may I be of service, Master?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            input = sc.nextLine();

            while (!input.equals("bye")) {
                try {
                    String[] inputWords = input.split(" ");
                    String action = inputWords[0];

                    if (input.equals("list")) {
                        System.out.println("Here is a list of your tasks, Master:");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println(i + 1 + ". " + taskList.get(i).toString());
                        }
                        input = sc.nextLine();
                    } else if (action.equals("done")) {
                        if (inputWords.length < 2) {
                            throw new MissingArgumentException("Wrong number of arguments");
                        }
                        int idx = Integer.parseInt(inputWords[1]);
                        System.out.println(taskList.get(idx - 1).markCompleted());
                        input = sc.nextLine();
                    } else if (action.equals("todo") || action.equals("deadline") || action.equals("event")){
                        if (inputWords.length < 2) {
                            throw new MissingArgumentException("Wrong number of arguments");
                        }
                        System.out.println("Master, I've added this task as requested:");
                        StringBuilder sb = new StringBuilder();
                        String taskItem;

                        if (action.equals("todo")) {
                            for (int i = 1; i < inputWords.length; i++) {
                                sb.append(" ");
                                sb.append(inputWords[i]);
                            }
                            taskItem = sb.toString();
                            ToDo toDoItem = new ToDo(taskItem);
                            taskList.add(toDoItem);
                            System.out.println(toDoItem.toString());
                        } else {
                            int slashIdx = 0;

                            for (int i = 0; i < inputWords.length; i++) {
                                if (inputWords[i].contains(Character.toString('/'))) {
                                    slashIdx = i;
                                }
                            }
                            for (int j = 0; j < slashIdx; j++) {
                                sb.append(" ");
                                sb.append(inputWords[j]);
                            }
                            taskItem = sb.toString();
                            StringBuilder sbSlash = new StringBuilder();

                            for (int k = slashIdx + 1; k < inputWords.length; k++) {
                                sbSlash.append(" ");
                                sbSlash.append(inputWords[k]);
                            }

                            if (action.equals("deadline")) {
                                Deadline deadlineItem = new Deadline(taskItem, sbSlash.toString());
                                taskList.add(deadlineItem);
                                System.out.println(deadlineItem.toString());
                            } else {
                                Event eventItem = new Event(taskItem, sbSlash.toString());
                                taskList.add(eventItem);
                                System.out.println(eventItem.toString());
                            }
                        }
                        if (taskList.size() == 1) {
                            System.out.println("\nYou have " + taskList.size() + " task in the list now, Master.");
                        } else {
                            System.out.println("\nYou have " + taskList.size() + " tasks in the list now, Master.");
                        }
                        input = sc.nextLine();
                    } else if (action.equals("delete")) {
                        if (inputWords.length < 2) {
                            throw new MissingArgumentException("Wrong number of arguments");
                        }
                        int deleteIdx = Integer.parseInt(inputWords[1]);
                        System.out.println("Understood Master. I've removed this task from the list:");
                        System.out.println(taskList.get(deleteIdx - 1));
                        taskList.remove(deleteIdx);
                        if (taskList.size() == 1) {
                            System.out.println("\nNow you have "
                                                + taskList.size()
                                                + " task in the list, Master.");
                        } else {
                            System.out.println("\nNow you have "
                                                + taskList.size()
                                                + " tasks in the list, Master.");
                        }

                        input = sc.nextLine();
                    } else {
                        throw new NoKeywordException("No such action.");
                    }
                } catch (MissingArgumentException error) {
                    System.out.println("Master, I'm afraid you're missing the task number.");
                    input = sc.nextLine();
                } catch (NoKeywordException error) {
                    System.out.println("Sorry Master but I don't understand what you mean.");
                    input = sc.nextLine();
                }
            }
            System.out.println("Have a good day, Master. Take care.");
            saveTaskList(taskList);
            break;
        }
        System.exit(1);
    }

    /** Saves the list of Tasks in the ArrayList object
     * into a .txt file.
     *
     * @param taskList
     */

    public void saveTaskList(ArrayList<Task> taskList) {
        Path path = Paths.get(this.DIRECTORY, "Data");
        try {
            /* Check if file path exists.
             * If it doesn't, create a new directory.
             */
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path filePath = Paths.get(this.DIRECTORY, "Data", "taskList.txt");
            File taskFile = filePath.toFile();

            /* Check if the file exists.
             * If it doesn't, create a new file
             */

            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(taskFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            Task task;
            for (int i = 0; i < taskList.size(); i++) {
                task = taskList.get(i);
                bufferedWriter.write(task.toSave());
                bufferedWriter.write("~");
                if(task.getDoneStatus()) {
                    bufferedWriter.write("1");
                } else {
                    bufferedWriter.write("0");
                }
                bufferedWriter.write("++");
            }
            bufferedWriter.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    /**
     * Loads the list of Tasks in the .txt file into the TaskList object
     *
     * @param taskList
     */

    public void loadTasks(ArrayList<Task> taskList) {
        Path filePath = Paths.get(this.DIRECTORY, "Data", "taskList.txt");

        if (filePath.toFile().exists()) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(filePath);
                String data = bufferedReader.readLine();

                if (data != null) {
                    String[] tasks = data.split("\\+\\+");

                    for (int i = 0; i < tasks.length; i++) {
                        String[] doneList = tasks[i].split("~", 2);
                        String[] nameList = doneList[0].split(" ", 2);

                        if (nameList[0].equals("deadline")) {
                            String[] deadlineItem = nameList[1].split("/by", 2);
                            Task taskItem = new Deadline(deadlineItem[0],
                                    deadlineItem[1],
                                    checkDone(deadlineItem[1]));
                            taskList.add(taskItem);
                        } else if (nameList[0].equals("event")) {
                            String[] eventItem = nameList[1].split("/at", 2);
                            Task taskItem = new Event(eventItem[0],
                                    eventItem[1],
                                    checkDone(eventItem[1]));
                            taskList.add(taskItem);
                        } else if (nameList[0].equals("todo")) {
                            Task taskItem = new ToDo(nameList[1], checkDone(doneList[1]));
                            taskList.add(taskItem);
                        }
                    }
                }
            } catch (IOException error) {
                System.out.println(error.getMessage());
            }
        } else {
            File createFile = new File("/Data/duke.txt");
        }
    }

    private static boolean checkDone (String item) {
        return item.equals("1");
    }
}
