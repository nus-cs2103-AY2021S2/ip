import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int listLength;

    /**
     * Creates a TaskList object.
     *
     * @param taskList ArrayList of Tasks of the user.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.listLength = taskList.size();
    }

    /**
     * Creates an empty TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.listLength = 0;
    }


    /**
     * Creates a Todo object.
     * Adds it to the TaskList afterwards.
     *
     * @param info String array containing information of the task.
     */
    void addTodo(String[] info) {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < info.length; i++) {
            sb.append(info[i]);
            if (i != info.length - 1) {
                sb.append(" ");
            }
        }
        Task task = new ToDo(sb.toString(), "T");
        taskList.add(task);
        listLength++;
    }

    /**
     * Creates and adds an Event object to the TaskList.
     *
     * @param info String array containing information about the Event.
     */
    void addEvent(String[] info) {
        StringBuffer description = new StringBuffer();
        StringBuffer dateAndTime = new StringBuffer();
        boolean isDescriptionDone = false;
        for (int i = 1; i < info.length; i++) {
            if (info[i].equals("/at")) {
                isDescriptionDone = true;
                i++;
            }
            if (isDescriptionDone) {
                dateAndTime.append(info[i]);
                if (i != info.length - 1) {
                    dateAndTime.append(" ");
                }
            } else {
                description.append(info[i]);
                if (i != info.length - 1) {
                    description.append(" ");
                }
            }
        }
        String[] details = dateAndTime.toString().split(" ");
        Date date = new Date(details[0]);
        Task task = new Event(description.toString(), date, details[1], "E");
        taskList.add(task);
        listLength++;
    }

    /**
     * Creates and adds a Deadline object to the TaskList.
     *
     * @param info String array containing information about the Deadline.
     */
    void addDeadline(String[] info) {
        StringBuffer description = new StringBuffer();
        String dateAsString = "";
        String time = "";
        boolean isDescriptionDone = false;
        for (int i = 1; i < info.length; i++) {
            if (info[i].equals("/by")) {
                isDescriptionDone = true;
                i++;
                dateAsString = info[i];
            } else if (isDescriptionDone) {
                System.out.println("adding time");
                time = time + info[i];
            } else {
                description.append(info[i]);
                if (i != info.length - 1) {
                    description.append(" ");
                }
            }
        }
        Date date = new Date(dateAsString);
        Task task;
        if (time.isEmpty()) {
            System.out.println("deadline without time");
            task = new Deadline(description.toString(), date, "D");
        } else {
            System.out.println("deadline with time");
            task = new Deadline(description.toString(), date, time, "D");
        }
        taskList.add(task);
        listLength++;
    }


    /**
     * Removes a task from the TaskList. The task is determined by the user input
     * found in the string array.
     *
     * @param info String array containing user input.
     * @return Task that has been removed.
     * @throws DukeException If the task number provided is less than 0.
     * or more than the number of tasks in the TaskList.
     */
    Task deleteTask(String[] info) throws DukeException {
        int taskIndex = Integer.parseInt(info[1]) - 1;
        if (taskIndex >= listLength || taskIndex < 0) {
            throw new DukeException("OOPS!!! There is no task in that line to delete");
        }
        Task task = taskList.remove(taskIndex);
        listLength--;
        return task;
    }


    /**
     * Changes the state of the task to completed. The task is determined by the user input
     * found in the string array.
     *
     * @param info String array containing user input.
     * @return Task that has been completed.
     * @throws DukeException If the task number provided is less than 0
     * or more than the number of tasks in the TaskList.
     */
    Task doneTask(String[] info) throws DukeException {
        int taskNumber = Integer.parseInt(info[1]);
        assert taskNumber <= 0 : "invalid number";
        if (taskNumber > listLength) {
            throw new DukeException("Invalid number! You only have " + listLength + " tasks!");
        }
        Task tobeDone = taskList.get(Integer.parseInt(info[1]) - 1);
        tobeDone.completed();
        return tobeDone;
    }

    /**
     * Filters the TaskList to return tasks that contains the keyword provided by the user.
     *
     * @param keyword String provided by the user
     * @return A TaskList object containing the filtered tasks.
     */
    TaskList find(String keyword) {
        String keywordLowerCased = keyword.toLowerCase();
        TaskList filteredList = new TaskList();
        for (Task task : taskList) {
            String description = task.description.toLowerCase();
            if (description.contains(keywordLowerCased)) {
                filteredList.taskList.add(task);
                filteredList.listLength++;
            }
        }
        return filteredList;
    }

    /**
     * Sets priority to a given task.
     *
     * @param info String array containing information about the task and priority.
     * @return Task that the priority has been added to.
     * @throws DukeException If the task number provided is less than 0
     * or more than the number of tasks in the TaskList.
     */
    Task addPriority(String[] info) throws DukeException {
        String priorityLevel = info[2].toUpperCase();
        int taskNumber = Integer.parseInt(info[1]) - 1;
        Task task = taskList.get(taskNumber);
        if (taskNumber >= listLength || taskNumber < 0) {
            throw new DukeException("OOPS! You only have " + listLength + " tasks in your list.");
        }
        if (priorityLevel.equals(Priority.LOW.toString())) {
            task.setPriority(Priority.LOW);
            taskList.set(taskNumber, task);
        } else if (priorityLevel.equals(Priority.MEDIUM.toString())) {
            task.setPriority(Priority.MEDIUM);
            taskList.set(taskNumber, task);
        } else if (priorityLevel.equals(Priority.HIGH.toString())) {
            task.setPriority(Priority.HIGH);
            taskList.set(taskNumber, task);
        } else {
            throw new DukeException("Incorrect priority given!");
        }
        return task;
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    public int getListLength() {
        return listLength;
    }

}
