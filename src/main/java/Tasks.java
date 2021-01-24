import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.FileWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Store all the tasks in a list DS
 */
public class Tasks {
    private static final String DATA_PATH = "data/lihua.json";
    private List<Task> tasks;

    /**
     * Initialize the task list with an empty ArrayList
     */
    public Tasks() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Add the given task into the list, with necessary information printed
     * @param userInput User input is assumed to be in the format of adding new tasks
     */
    public void addTask(String userInput) {
        try {
            Task task = null;

            // It is guaranteed that either one of the three blocks will be entered
            // therefore the final value for task can never be null
            if (FormatChecker.likeAddingToDo(userInput)) {
                // may throw exception
                String content = InputInformationExtractor.getToDoContent(userInput);
                task = new ToDo(content);
            } else if (FormatChecker.likeAddingDeadline(userInput)) {
                String content = InputInformationExtractor.getDeadlineContent(userInput);
                LocalDate by = InputInformationExtractor.getDeadlineTime(userInput);
                task = new Deadline(content, by);
            } else if (FormatChecker.likeAddingEvent(userInput)) {
                String content = InputInformationExtractor.getEventContent(userInput);
                LocalDate time = InputInformationExtractor.getEventTime(userInput);
                task = new Event(content, time);
            }
            tasks.add(task);

            System.out.println("Got it. I have added this task to your list:");
            System.out.print("---- ");
            System.out.println(task);
            reportTotalNumberOfTasks();
        } catch (ToDoException e) {
            System.out.println("Sorry, the format for adding a todo task is \'todo [task name]\'. " +
                    "Please retry :')");
        } catch (DeadlineException e) {
            System.out.println("Sorry, the format for adding a deadline task is \'deadline [task name] /by time\'. " +
                    "Please retry :')");
        } catch (EventException e) {
            System.out.println("Sorry, the format for adding an event task is \'event [task name] /at time\'. " +
                    "Please retry :')");
        }
    }

    /**
     * Get the given task done, print out the result for reference
     * @param index 1-based index number referring to the specific task in the list
     */
    private void getTaskDone(int index) {
        if (tasks.size() == 0) {
            reportNoTaskRightNow();
            return;
        }
        try {
            tasks.get(index - 1).setDone(true);
            System.out.println("Nice! I have marked this task as done:");
            System.out.print("---- ");
            System.out.println(tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            reportInvalidIndexNumber();
        }
    }

    /**
     * Get the given task done, represented by the user input string
     * @param userInput Assumed to be of the format of trying to get tasks done
     */
    public void getTaskDone(String userInput) {
        try {
            int index = InputInformationExtractor.getIndexArgument(userInput);
            getTaskDone(index);
        } catch (Exception e) {
            // index argument not found
            System.out.println("Sorry, the format for getting task done is \'done [a valid task number]\'. " +
                    "Please retry :')");
        }
    }

    /**
     * Delete the given task, print out the result for reference
     * @param index 1-based index number referring to the specific task in the list
     */
    private void deleteTask(int index) {
        if (tasks.size() == 0) {
            reportNoTaskRightNow();
            return;
        }
        try {
            Task task = tasks.remove(index - 1);
            System.out.println("Noted! The task has successfully been removed.");
            System.out.print("---- ");
            System.out.println(task);
            reportTotalNumberOfTasks();
        } catch (IndexOutOfBoundsException e) {
            reportInvalidIndexNumber();
        }
    }

    /**
     * Get the given task deleted, represented by the user input string
     * @param userInput Assumed to be of the format of trying to delete a task
     */
    public void deleteTask(String userInput) {
        try {
            int index = InputInformationExtractor.getIndexArgument(userInput);
            deleteTask(index);
        } catch (Exception e) {
            // index argument not found
            System.out.println("Sorry, the format for deleting a task is \'delete [a valid task number]\'. " +
                    "Please retry :')");
        }
    }

    /**
     * Print out the list in a user-friendly format
     */
    public void printList() {
        if (tasks.size() == 0) {
            reportNoTaskRightNow();
            return;
        }
        System.out.println("These are the tasks in your list so far:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i, tasks.get(i - 1)));
        }
    }

    /**
     * Get the total number of tasks now
     * @return The total number of tasks now, including done and undone tasks
     */
    public int getTotalNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Print the report for total number of tasks, including done and undone tasks
     */
    public void reportTotalNumberOfTasks() {
        String noun = tasks.size() <= 1? "task": "tasks";
        System.out.println(String.format("Now you have %d %s in total. Good Luck.", tasks.size(), noun));
    }

    /**
     * Load all tasks in the current list to hard disk as Json objects
     */
    public void loadTasks() {
        try {
            // will create a file in the path, in case the file does not exist
            File fileChecker = new File(DATA_PATH);
            if (!fileChecker.exists()) {
                fileChecker.getParentFile().mkdir();
                fileChecker.createNewFile();
                return;
            }

            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(DATA_PATH);
            JSONArray jsonArray = (JSONArray)parser.parse(reader);

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String type = (String)jsonObject.get("type");
                String description = (String)jsonObject.get("description");
                boolean isDone = (boolean)jsonObject.get("isDone");
                Task t = null;
                // either one of the cases will be entered
                // <-> t will not be null
                if (type.equals("todo")) {
                    t = new ToDo(description);
                } else {
                    LocalDate time = LocalDate.parse((String)jsonObject.get("time"));
                    if (type.equals("deadline")) {
                        t = new Deadline(description, time);
                    } else if (type.equals("event")) {
                        t = new Event(description, time);
                    }
                }
                t.setDone(isDone);
                tasks.add(t);
            }
            // System.out.println("Successfully loaded task data :D");
        } catch (IOException e) {
            System.out.println("Something bad happens, cannot load data. :')");
        } catch (ParseException e) {
            System.out.println("Something bad happens, cannot load data. :')");
        }
    }

    /**
     * Save all tasks in the current list to hard disk as Json objects
     */
    public void saveTasks() {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < tasks.size(); i++) {
            jsonArray.add(tasks.get(i).toJsonObject());
        }
        try {
            File fileToCreate = new File(DATA_PATH);
            fileToCreate.getParentFile().mkdir();
            fileToCreate.createNewFile();
            FileWriter fileWriter = new FileWriter(DATA_PATH);
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Sorry, this task cannot be saved right now. :')");
        }
    }
    /**
     * @param userInput Second argument of userInput is assumed to be of 'yyyy-mm-dd'
     */
    public void printTasksOnDate(String userInput) {
        LocalDate date = LocalDate.parse(userInput.split(" ")[1]);
        boolean hasTask = false;
        for (Task t : tasks) {
            if (t instanceof ToDo) {
                continue;
            }
            // t is either deadline or event
            if (date.equals(t.getDate())) {
                if (!hasTask) {
                    hasTask = true;
                    System.out.println(String.format("You have the following tasks on %s.",
                            date.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))));
                }
                System.out.println(t);
            }
        }
        if (!hasTask) {
            System.out.println(String.format("You do not have any task on %s. :')",
                    date.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))));
        }
    }

    /**
     * Print the report for error message: invalid index number of the list
     */
    private void reportInvalidIndexNumber() {
        System.out.println("Sorry, the task number you specified is not valid.\n" +
                "Try enter \'list\' to see the range of task numbers you can enter.");
    }

    /**
     * Print the report for error message:
     * no task right now, no delete/get done operation can be performed
     */
    private void reportNoTaskRightNow() {
        System.out.println("Sorry, there is no task right now, please add one first :')");
    }
}