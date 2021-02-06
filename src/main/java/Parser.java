import java.util.List;
import java.util.ArrayList;

/*
 * Deal with making sense of users' commands.
 */
public class Parser {
    private String command;
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private boolean isTerminated;

    public Parser(TaskList tasklist, Ui ui, Storage storage) {
        command = "";
        this.taskList = tasklist;
        this.ui = ui;
        this.storage = storage;
        isTerminated = false;
    }

    /*
     * Add a new command to be dealt with.
     *
     * @param command New command.
     */
    public void insertCommand(String command) {
        this.command = command;
    }

    /*
     * Check if Parser is done dealing with all commands.
     *
     * @return Done status.
     */
    public boolean hasTerminated() {
        return isTerminated;
    }

    /*
     * Deal with the current command.
     * Pick the appropriate response.
     * Pass the response to the Ui to handle.
     * In the case of a bad command, a relevant response is given.
     */
    public String process() {
        if (command.equals("list")) {
            String list = "";
            for (int i = 0; i < taskList.numOfTasks(); i++) {
                Integer taskNum = i + 1;
                list += taskNum.toString() + "." + taskList.getTask(i).toString() + "\n";
            }
            return ui.giveList(list);
        } else if (command.contains("todo")) {
            String[] line = command.split(" ");
            int length = line.length;
            if (length == 1) {
                return ui.noDescReply();
            }
            String description = "";
            for (int i = 1; i < length; i++) {
                if (i + 1 == length) {
                    description += line[i];
                } else {
                    description += line[i] + " ";
                }
            }
            Todo todo = new Todo(description);
            taskList.addTask(todo);
            Integer numOfTasks = taskList.numOfTasks();
            storage.writeToFile(taskList);
            return ui.addTaskReply(todo.toString(), numOfTasks.toString());
        } else if (command.contains("deadline")) {
            String[] line = command.split(" ");
            int length = line.length;
            if (length == 1) {
                return ui.noDescReply();
            }
            String description = "";
            String date = "";
            String time = "";
            boolean isDesc = true;
            boolean isDate = true;
            for (int i = 1; i < length; i++) {
                if (line[i].equals("/by")) {
                    isDesc = false;
                } else if (isDesc) {
                    if (i + 1 == length) {
                        description += line[i];
                    } else {
                        description += line[i] + " ";
                    }
                } else if (isDate) {
                    date += line[i];
                    isDate = false;
                } else {
                    time += line[i];
                }
            }
            if (date.equals("")) {
                return ui.noDateReply();
            } else if (time.equals("")) {
                return ui.noTimeReply();
            } else {
                Deadline deadline = new Deadline(description, date, time);
                deadline.formatDate();
                deadline.formatTime();
                taskList.addTask(deadline);
                Integer numOfTasks = taskList.numOfTasks();
                storage.writeToFile(taskList);
                return ui.addTaskReply(deadline.toString(), numOfTasks.toString());
            }
        } else if (command.contains("event")) {
            String[] line = command.split(" ");
            int length = line.length;
            if (length == 1) {
                return ui.noDescReply();
            }
            String description = "";
            String date = "";
            String time = "";
            boolean isDesc = true;
            boolean isDate = true;
            for (int i = 1; i < length; i++) {
                if (line[i].equals("/at")) {
                    isDesc = false;
                } else if (isDesc) {
                    if (i + 1 == length) {
                        description += line[i];
                    } else {
                        description += line[i] + " ";
                    }
                } else if (isDate) {
                    date += line[i];
                    isDate = false;
                } else {
                    time += line[i];
                }
            }
            if (date.equals("")) {
                return ui.noDateReply();
            } else if (time.equals("")) {
                return ui.noTimeReply();
            } else {
                Event event = new Event(description, date, time);
                event.formatDate();
                event.formatTime();
                taskList.addTask(event);
                Integer numOfTasks = taskList.numOfTasks();
                storage.writeToFile(taskList);
                return ui.addTaskReply(event.toString(), numOfTasks.toString());
            }
        } else if (command.contains("done")) {
            String[] line = command.split(" ");
            int length = line.length;
            if (length == 1) {
                return ui.noLineReply();
            }
            int number = Integer.parseInt(line[1]);
            if (number > taskList.numOfTasks()) {
                return ui.wrongLineReply();
            }
            int index = number - 1;
            Task task = taskList.getTask(index);
            task.markAsDone();
            taskList.replaceTask(index, task);
            storage.writeToFile(taskList);
            return ui.doneReply(task.toString());
        } else if (command.contains("delete")) {
            String[] line = command.split(" ");
            int length = line.length;
            if (length == 1) {
                return ui.noLineReply();
            }
            int number = Integer.parseInt(line[1]);
            if (number > taskList.numOfTasks()) {
                return ui.wrongLineReply();
            }
            int index =  number - 1;
            Task deletedTask = taskList.deleteTask(index);
            Integer numOfTasks = taskList.numOfTasks();
            storage.writeToFile(taskList);
            return ui.deleteReply(deletedTask.toString(), numOfTasks.toString());
        } else if (command.contains("find")) {
            String[] line = command.split(" ");
            int length = line.length;
            if (length == 1) {
                return ui.noDescReply();
            }
            String word = line[1];
            List<String> matches = new ArrayList<>();
            Integer num = 1;
            boolean isFound = false;
            for (int i = 0; i < taskList.numOfTasks(); i++) {
                Task task = taskList.getTask(i);
                String description = task.toString();
                if (description.contains(word)) {
                    matches.add(num.toString() + ". " + description);
                    num++;
                    isFound = true;
                }
            }
            if (isFound) {
                String tasks = "";
                for (int i = 0; i < matches.size(); i++) {
                    tasks += matches.get(i) + "\n";
                }
                return ui.foundReply(tasks);
            } else {
                return ui.notFoundReply();
            }
        } else if (command.equals("bye")) {
            isTerminated = true;
            return ui.bidFarewell();
        } else {
            return ui.confusedReply();
        }
    }
}
