package Duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    TaskList(ArrayList<String> contents) throws DukeException {
        this.taskList = new ArrayList<>();

        for (String task: contents) {
            
            // End when there is no task to work with
            if (task.isEmpty()) {
                break;
            }

            char taskType = task.charAt(1);
            char isDone = task.charAt(4);
            String description;

            // Create task based on event type
            if (taskType == 'T') {
                // ToDo Task
                description = task.substring(7);
                taskList.add(new ToDo(description, isDone == 'X'));
            } else if (taskType == 'D') {
                // Get the description and date from the user's input (Deadline Task)
                StringDatePair output = new Parser().parse(task, Parser.commandType.FILE_DEADLINE);
                
                // Add the task to the task list
                taskList.add(new Deadline(output.getString(), output.getDate(), isDone == 'X'));
            } else if (taskType == 'E') {
                // Get the description and date from the user's input (Event Task)
                StringDatePair output = new Parser().parse(task, Parser.commandType.FILE_EVENT);
                
                // Add the task to the task list
                taskList.add(new Event(output.getString(), output.getDate(), isDone == 'X'));
            } else {
                throw new DukeException();
            }
        }
    }

    // Add task to the task list
    public void add(Task newTask) {
        taskList.add(newTask);
    }

    // Remove task from the task list based on the index
    public void remove(int index) {
        taskList.remove(index);
    }

    // Return the Task based on the index given
    public Task get(int index) {
        return taskList.get(index);
    }

    // Return the size of the task list
    public int size() {
        return taskList.size();
    }
}
