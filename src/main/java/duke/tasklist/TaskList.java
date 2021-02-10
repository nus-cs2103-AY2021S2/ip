package duke.tasklist;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Handles the storing of the list of tasks as well as conversion of user's input Task objects.
 */
public class TaskList {
    private List<Task> list;
    private List<String> listOfTaskInString = new ArrayList<>();

    private static final String STRING_SEPARATOR = "\\s*---\\s*";

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Converts the user's input from a valid string form to a Task object.
     * The object is then added to a list of objects.
     *
     * @param data String of user's input.
     */
    public void dataInput(String data) {
        convertDataIntoStringsOfTask(data);
        updateListWithConvertedData();
    }

    private void convertDataIntoStringsOfTask(String data) {
        String[] ArrayOfTasksFromLoad = data.split("\n");
        listOfTaskInString = Arrays.asList(ArrayOfTasksFromLoad);
    }

    private void updateListWithConvertedData() {
        for (String taskInStringForm : listOfTaskInString) {
            String[] taskValues = separateTaskIntoIndividualValues(taskInStringForm);

            String typeOfTask = getTypeOfTask(taskValues);
            String taskDescription = getTaskDescription(taskValues);
            boolean taskIsDone = getTaskIsDoneState(taskValues);
            String taskDate = getTaskDate(taskValues);

            switch (typeOfTask) {
            case "T":
                addTodoToList(taskDescription, taskIsDone);
                break;
            case "D":
                addDeadlineToList(taskDescription, taskIsDone, taskDate);
                break;
            case "E":
                addEventToList(taskDescription, taskIsDone, taskDate);
                break;
            default:
                printErrorMessage();
            }
        }
    }

    private void printErrorMessage() {
        System.out.println("Error with the written file.");
    }

    private void addEventToList(String taskDescription, boolean taskIsDone, String taskDate) {
        Event e = new Event(taskDescription, taskIsDone, taskDate);
        list.add(e);
    }

    private void addDeadlineToList(String taskDescription, boolean taskIsDone, String taskDate) {
        Deadline d = new Deadline(taskDescription, taskIsDone, taskDate);
        list.add(d);
    }

    private void addTodoToList(String taskDescription, boolean taskIsDone) {
        Todo t = new Todo(taskDescription, taskIsDone);
        list.add(t);
    }

    private String[] separateTaskIntoIndividualValues(String taskInStringForm) {
        return taskInStringForm.split(STRING_SEPARATOR);
    }

    private String getTaskDate(String[] taskValues) {
        return taskValues[3];
    }

    private boolean getTaskIsDoneState(String[] taskValues) {
        return taskValues[1].equals("1");
    }

    private String getTypeOfTask(String[] taskValues) {
        return taskValues[0];
    }

    private String getTaskDescription(String[] taskValues) {
        return taskValues[2];
    }

    /**
     * Returns a list of Tasks.
     *
     * @return List of Tasks.
     */
    public List<Task> getList() {
        return list;
    }
}
