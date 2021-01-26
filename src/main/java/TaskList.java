import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    // tasks in schedule
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<String> myTasks) {
        this.taskList = initialiseList(myTasks);
    }

    private ArrayList<Task> initialiseList(ArrayList<String> myTasks) {
        ArrayList<Task> taskList = new ArrayList<>();
//        if(myTasks.isEmpty()){
//            return taskList;
//        }

        for (String s : myTasks) {
            String[] parts = s.split(" | ", 2);
            String type = parts[0];
            if (type.equals("T")) {
                String description = parts[1];
                // addTodo
                Task newTask = new ToDo(description);
                taskList.add(newTask);
            }

            if (type.equals("D")) {
                String[] details = parts[1].split(" | ");
                String description = details[0];
                String time = details[1];

                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
                String by = dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-dd H:mm"));

                Task newTask = new Deadline(description, by);
                taskList.add(newTask);
            }

            if (type.equals("E")) {
                String[] details = parts[1].split(" | ");
                String description = details[0];
                String time = details[1];

                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
                String by = dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-dd H:mm"));

                Task newTask = new Event(description, by);
                taskList.add(newTask);
            }
        }
        return taskList;
    }


    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);
        return task;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int taskNumber) {
        Task curTask = this.taskList.remove(taskNumber - 1);
        return curTask;
    }

    public Task markTaskAsDone(int taskNumber) {
        Task curTask = this.taskList.get(taskNumber - 1);
        curTask.markAsDone();
        return curTask;
    }
}

//    public void executeTask(String operator, String taskDetails) throws DukeException {
//        ArrayList<Task> myList = getTaskList();
//        String[] description = taskDetails.split(" | ", 2);
//
//        if (!operatorList.contains(operator.toLowerCase())) {
//            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
//        }
//
//        if (operator.toLowerCase().equals("done")) {
//            try {
//                markTaskAsDone(description);
////                FileWriting.saveTaskList(myList);
//            } catch (DukeException e) {
//                ui.showErrorMessage(e);
//            }
//        }
//
//        if (operator.toLowerCase().equals("delete")) {
//            try {
//                deleteTask(parts, myList);
//                FileWriting.saveTaskList(myList);
//            } catch (DukeException e) {
//                ui.showErrorMessage(e);
//            }
//        }
//
//        if (operator.toLowerCase().equals("todo")) {
//            try {
//                addToDo(parts, myList);
//                FileWriting.saveTaskList(myList);
//            } catch (DukeException e) {
//                ui.showErrorMessage(e);
//            }
//        }
//
//        if (operator.toLowerCase().equals("deadline")) {
//            try {
//                addDeadline(parts, myList);
//                FileWriting.saveTaskList(myList);
//            } catch (DukeException e) {
//                ui.showErrorMessage(e);
//            }
//        }
//        if (operator.toLowerCase().equals("event")) {
//            try {
//                addEvent(parts, myList);
//                FileWriting.saveTaskList(myList);
//            } catch (DukeException e) {
//                ui.showErrorMessage(e);
//            }
//        }
//    }

