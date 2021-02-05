import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int listLength;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.listLength = taskList.size();
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.listLength = 0;
    }


    /**
     * creates a Task object and adds it to the TaskList object
     *
     * @param info String array containing details of the user input
     * @throws DukeException if the user input is not specified properly
     */
    void add(String[] info) throws DukeException {
        int length = info.length;
        Task task = null;
        if (info[0].equals("todo")) {
            if (length == 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty");
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i < info.length; i++) {
                sb.append(info[i]);
                if (i != info.length - 1) {
                    sb.append(" ");
                }
            }
            task = new ToDo(sb.toString(), "T");
        } else if (info[0].equals("event") || info[0].equals("deadline")) {
            if (length == 1) {
                if (info[0].equals("event")) {
                    throw new DukeException("OOPS! Specifics are needed for this event");
                } else {
                    throw new DukeException("OOPS! Specifics are needed for this deadline");
                }
            }
            StringBuffer description = new StringBuffer();
            StringBuffer dateAndTime = new StringBuffer();
            boolean isDescriptionDone = false;
            for (int i = 1; i < info.length; i++) {
                if (info[i].equals("/at") || info[i].equals("/by")) {
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
            if (info[0].equals("event")) {
                String[] details = dateAndTime.toString().split(" ");
                Date date = new Date(details[0]);
                task = new Event(description.toString(), date, details[1], "E");
            } else if (info[0].equals("deadline")) {
                Date date = new Date(dateAndTime.toString());
                task = new Deadline(description.toString(), date, "D");
            }
        }
        assert task != null : "no task given";
        taskList.add(task);
        listLength++;

    }


    /**
     * removes a task from the TaskList. The task is determined by the user input
     * found in the string array.
     *
     * @param info String array containing user input
     * @return Task that has been removed
     * @throws DukeException if the task number provided is less than 0 or more than the number of tasks in the TaskList
     */
    Task delete(String[] info) throws DukeException {
        int taskIndex = Integer.parseInt(info[1]) - 1;
        if (taskIndex > listLength || taskIndex < 0) {
            throw new DukeException("OOPS!!! There is no task in that line to delete");
        }
        Task task = taskList.remove(taskIndex);
        listLength--;
        return task;
    }


    /**
     * changes the state of the task to completed. The task is determined by the user input
     * found in the string array.
     *
     * @param info String array containing user input
     * @return Task that has been completed
     * @throws DukeException if the task number provided is less than 0 or more than the number of tasks in the TaskList
     */
    Task done(String[] info) throws DukeException {
        assert info.length == 2 : "invalid command";
        if (info.length == 1) {
            throw new DukeException("OOPS! Task completed is not specified");
        }
        if (Integer.parseInt(info[1]) > listLength || Integer.parseInt(info[1]) <= 0) {
            throw new DukeException("OOPS! There is no such specified task");
        }
        Task tobeDone = taskList.get(Integer.parseInt(info[1]) - 1);
        tobeDone.completed();
        return tobeDone;
    }

    TaskList find(String keyword) throws DukeException {
        if (keyword.length() == 0) {
            throw new DukeException("OOPS! No keyword is given!");
        }
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

    public ArrayList<Task> getList() {
        return taskList;
    }

    public int getListLength() {
        return listLength;
    }

}
