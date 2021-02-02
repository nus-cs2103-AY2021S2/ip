package duke.task;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import duke.Duke;
import duke.datetime.DateTimeConverter;
import duke.error.ErrorChecker;
import duke.file.FileSaver;

public class TaskManager {
    protected ArrayList<Task> tasks;
    protected FileSaver fileSaver;

    protected final String HELP_RESPONSE = "list: list all tasks\ndone {i}: mark task at position {i} as done\n" +
            "delete {i}: delete task at position {i}\nfind {keyword}: find and list all tasks containing {keyword}\n" +
            "todo {description}: creates a new todo\ndeadline {description} /by {date}: creates a new deadline\n" +
            "event {description} /on {date} /from {time} /to {time}: creates a new event";

    public TaskManager() {
        tasks = new ArrayList<>();
        fileSaver = new FileSaver();
    }

    public String takeEvent(String input, ArrayList<Task> tasks) {
        this.tasks = tasks;
        ErrorChecker e = new ErrorChecker(input, tasks);

        if (input.equals("help")) {
            return HELP_RESPONSE;
        } else if (input.equals("list")) {
            return listEvents();
        } else if (e.isValid()) {
            if (input.startsWith("done")) {
                return markDone(input);
            } else if (input.startsWith("delete")) {
                return deleteTask(input);
            } else if (input.startsWith("find")) {
                return findTasks(input);
            } else {
                return addNewTask(input);
            }
        } else {
            return e.getMessage();
        }

    }

    public String markDone(String input) {
        Task task = tasks.get(Integer.parseInt(input.substring(5)) - 1);
        task.markAsDone();
        return "Good job! You got " + task.description + " done!";
    }

    public String addNewTask(String input) {
        Task newTask;
        if (input.startsWith("todo")) {
            newTask = new TodoTask(input.substring(5));
        } else if (input.startsWith("deadline")) {
            String[] inputSplit = input.split("/");
            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
            newTask = new DeadlineTask(inputSplit[0].substring(9, inputSplit[0].length() - 1),
                    dateTimeConverter.convertDate());
        } else {
            String[] inputSplit = input.split("/");
            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
            newTask = new EventTask(inputSplit[0].substring(6, inputSplit[0].length() - 1),
                    dateTimeConverter.convertDate(), dateTimeConverter.convertTime("from"),
                    dateTimeConverter.convertTime("to"));
        }
//        return "fine up till here";
        tasks.add(newTask);

//        try {
//            fileSaver.saveToFile("DukeList.txt", tasks);
//        } catch (IOException ex) {
////            System.out.println("File path not found: " + ex.getMessage());
//            return "File path not found: " + ex.getMessage();
//        }

        return "Added: " + newTask.toString() ;
    }

    public String deleteTask(String input) {
        Task task = tasks.get(Integer.parseInt(input.substring(7)) - 1);
        tasks.remove(Integer.parseInt(input.substring(7)) - 1);

//        try {
//            fileSaver.saveToFile("DukeList.txt", tasks);
//        } catch (IOException ex) {
////            System.out.println("File path not found: " + ex.getMessage());
//            return "File path not found: " + ex.getMessage();
//        }

        return "Deleted: " + task.toString();
    }

    public String findTasks(String input) {
        String description = input.substring(5);
        String output = "Here is a list of your tasks that contain " + description +":";

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(description)) {
                output = output + "\n" + (i + 1) + ". " + tasks.get(i).toString();
            }
        }

//        System.out.println(Duke.LINE);
//        output = output + "\n" + Duke.LINE;
        return output;
    }

    public String listEvents() {
        String output = "Here is a list of your tasks:";

        for (int i = 0; i < tasks.size(); i++) {
            output = output + "\n" + (i + 1) + ". " + tasks.get(i).toString();
        }

//        output = output + "\n" + Duke.LINE;
        return output;
    }



//    public void takeEvent(String input, ArrayList<Task> tasks) {
//        this.tasks = tasks;
//        ErrorChecker e = new ErrorChecker(input, tasks);
//
//        if (input.equals("list")) {
//            listEvents();
//            return;
//        } else if (e.isValid()) {
//            if (input.startsWith("done")) {
//                markDone(input);
//            } else if (input.startsWith("delete")) {
//                deleteTask(input);
//            } else if (input.startsWith("find")) {
//                findTasks(input);
//            } else {
//                addNewTask(input);
//            }
//        }
//
//    }

//    public void markDone(String input) {
//        Task task = tasks.get(Integer.parseInt(input.substring(5)) - 1);
//        task.markAsDone();
//        System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9 + "Good job! You got " + task.description
//                + " done!\n" + Duke.LINE);
//    }
//
//    public void addNewTask(String input) {
//        Task newTask;
//        if (input.startsWith("todo")) {
//            newTask = new TodoTask(input.substring(5));
//        } else if (input.startsWith("deadline")) {
//            String[] inputSplit = input.split("/");
//            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
//            newTask = new DeadlineTask(inputSplit[0].substring(9, inputSplit[0].length() - 1),
//                    dateTimeConverter.convertDate());
//        } else {
//            String[] inputSplit = input.split("/");
//            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
//            newTask = new EventTask(inputSplit[0].substring(6, inputSplit[0].length() - 1),
//                    dateTimeConverter.convertDate(), dateTimeConverter.convertTime("from"),
//                    dateTimeConverter.convertTime("to"));
//        }
//        tasks.add(newTask);
//
//        try {
//            fileSaver.saveToFile("DukeList.txt", tasks);
//        } catch (IOException ex) {
//            System.out.println("File path not found: " + ex.getMessage());
//        }
//
//        System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9 + "Added: " + newTask.toString() + "\n" + Duke.LINE);
//    }
//
//    public void deleteTask(String input) {
//        Task task = tasks.get(Integer.parseInt(input.substring(7)) - 1);
//        tasks.remove(Integer.parseInt(input.substring(7)) - 1);
//
//        try {
//            fileSaver.saveToFile("DukeList.txt", tasks);
//        } catch (IOException ex) {
//            System.out.println("File path not found: " + ex.getMessage());
//        }
//
//        System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9 + "Deleted: " + task.toString() + "\n" + Duke.LINE);
//    }
//
//    public void findTasks(String input) {
//        String description = input.substring(5);
//
//        System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9 + "Here is a list of your tasks:");
//
//        for (int i = 0; i < tasks.size(); i++) {
//            if (tasks.get(i).description.contains(description)) {
//                System.out.println("" + (char) 9 + (char) 9 + (char) 9 + (i + 1) + ". " + tasks.get(i).toString());
//            }
//        }
//
//        System.out.println(Duke.LINE);
//    }
//
//    public void listEvents() {
//        System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9 + "Here is a list of your tasks:");
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println("" + (char) 9 + (char) 9 + (char) 9 + (i + 1) + ". " + tasks.get(i).toString());
//        }
//        System.out.println(Duke.LINE);
//    }
}
