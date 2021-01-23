import tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;

import tasks.TodoTask;
import tasks.DeadlineTask;
import tasks.EventTask;

import exceptions.ChatBotException;
import exceptions.MissingDescriptionException;
import exceptions.UnknownInputException;

public class ChatBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        //Greeting the user
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();

        //Create a taskList data file if it does not exist
        File f = new File("src/main/java/data/taskData.txt");
        f.getParentFile().mkdir();
        label:
        while (sc.hasNext()) {
            //split the input into an array
            String[] inputWords = sc.nextLine().split(" ");
            //input type
            String input = inputWords[0];

            try {
                switch (input) {
                    case "bye":
                        //inputs is bye, terminate the chat bot
                        System.out.println("Bye. Hope to see you again soon!");
                        break label;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        //input is to read the list
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println((i + 1) + ". " + (taskList.get(i)));
                        }
                        System.out.println();
                        break;
                    case "done": {
                        //input is to finish a task
                        int index = Integer.parseInt(inputWords[1]) - 1;
                        taskList.get(index).taskDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(taskList.get(index));
                        System.out.println();
                        ChatBot.writeToFile(f.getAbsolutePath(), ChatBot.taskListString(taskList));
                        break;
                    }
                    case "todo": {
                        if (inputWords.length <= 1) {
                            throw new MissingDescriptionException(input);
                        }
                        //input is to add a todoTask
                        System.out.println("Got it. I've added this task:");
                        String taskName = "";
                        for (int i = 1; i < inputWords.length; i++) {
                            taskName = taskName + inputWords[i] + " ";
                        }
                        taskList.add(new TodoTask(taskName.stripTrailing()));
                        int len = taskList.size();
                        System.out.println(taskList.get(len - 1));
                        System.out.println("Now you have " + len + " tasks in the list.");
                        System.out.println();
                        ChatBot.writeToFile(f.getAbsolutePath(), ChatBot.taskListString(taskList));
                        break;
                    }
                    case "deadline": {
                        if (inputWords.length <= 1) {
                            throw new MissingDescriptionException(input);
                        }
                        //input is to add a deadlineTask
                        System.out.println("Got it. I've added this task:");
                        String taskName = "";
                        String date = "";
                        int dateIndex = 0;
                        //get the taskName
                        for (int i = 1; i < inputWords.length; i++) {
                            if (inputWords[i].charAt(0) == '/') {
                                dateIndex = i;
                                break;
                            } else {
                                taskName = taskName + inputWords[i] + " ";
                            }
                        }
                        //get the date
                        date = date + inputWords[dateIndex].substring(1) + ": ";
                        for (int i = dateIndex + 1; i < inputWords.length; i++) {
                            if (i == inputWords.length - 1) {
                                date = date + inputWords[i];
                            } else {
                                date = date + inputWords[i] + " ";
                            }
                        }
                        taskList.add(new DeadlineTask(taskName.stripTrailing(), date));
                        int len = taskList.size();
                        System.out.println(taskList.get(len - 1));
                        System.out.println("Now you have " + len + " tasks in the list.");
                        System.out.println();
                        ChatBot.writeToFile(f.getAbsolutePath(), ChatBot.taskListString(taskList));
                        break;
                    }
                    case "event": {
                        if (inputWords.length <= 1) {
                            throw new MissingDescriptionException(input);
                        }
                        //input is to add a eventTask
                        System.out.println("Got it. I've added this task:");
                        String taskName = "";
                        String time = "";
                        int timeIndex = 0;
                        //get the taskName
                        for (int i = 1; i < inputWords.length; i++) {
                            if (inputWords[i].charAt(0) == '/') {
                                timeIndex = i;
                                break;
                            } else {
                                taskName = taskName + inputWords[i] + " ";
                            }
                        }
                        //get the time
                        time = time + inputWords[timeIndex].substring(1) + ": ";
                        for (int i = timeIndex + 1; i < inputWords.length; i++) {
                            if (i == inputWords.length - 1) {
                                time = time + inputWords[i];
                            } else {
                                time = time + inputWords[i] + " ";
                            }
                        }
                        taskList.add(new EventTask(taskName.stripTrailing(), time));
                        int len = taskList.size();
                        System.out.println(taskList.get(len - 1));
                        System.out.println("Now you have " + len + " tasks in the list.");
                        System.out.println();
                        ChatBot.writeToFile(f.getAbsolutePath(), ChatBot.taskListString(taskList));
                        break;
                    }
                    case "delete": {
                        //input is to delete a task
                        int index = Integer.parseInt(inputWords[1]) - 1;
                        Task removedTask = taskList.remove(index);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(removedTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println();
                        ChatBot.writeToFile(f.getAbsolutePath(), ChatBot.taskListString(taskList));
                        break;
                    }
                    default:
                        throw new UnknownInputException();
                }
            } catch (ChatBotException | IOException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }

    }
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
    public static String taskListString(ArrayList<Task> taskList) {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result = result + taskList.get(i) + "\n";
        }
        return result;
    }
}
