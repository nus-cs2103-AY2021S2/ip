package duck.operation;

import java.util.ArrayList;

import duck.task.TaskList;

public class Gui {

    private static String logo =
            " ____           _        \n"
                    + "|  _   \\ _   __|  | _____ \n"
                    + "|  | |  |  |  |  |   |/  / _ \\\n"
                    + "|  |_|  |  |_ |  |    <  __/\n"
                    + "|____/\\___,_|__|\\_\\___|\n";

    /**
     * show a welcome logo and ask for command
     */
    public String showWelcome() {
        return "Hello from\n" + logo + "\nHello! I'm duck.Duke Y(^_^)Y\n"
                + "What can I do for you?\n";
    }

    /**
     * show the reply for command including hello,bye,list and so on
     *
     * @param command different kind of command
     * @param tasks
     */
    public String getCommandReply(String command, TaskList tasks) {
        assert (!command.isBlank());
        switch (command) {
        case "hello":
            return "Hello! I'm duck.Duke Y(^_^)Y\n"
                    + "What can I do for you?\n";
        case "bye":
            return "Bye. (>_<) Hope to see you again soon! ";
        case "list":
            StringBuffer stringList = new StringBuffer();
            stringList.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.getSizeOfTasks(); i++) {
                stringList.append((i + 1) + "." + tasks.getTask(i).getTaskInfo() + "\n");
            }
            return stringList.toString();
        default:
            return "Got it. I've added this task:\n"
                    + tasks.getTask(tasks.getSizeOfTasks() - 1).getTaskInfo() + "\n"
                    + "Now you have " + tasks.getSizeOfTasks() + " tasks in the list.\n";
        }

    }

    /**
     * show the reply for error
     *
     * @param reply the kind of error
     */
    public String getErrorReply(String reply) {

        switch (reply) {
        case "error_no_meaning":
            return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        case "error_done_empty":
        case "error_delete_empty":
        case "error_date_empty":
            return "OOPS!!! The number cannot be empty :-(\n";
        case "error_done_no_meaning":
        case "error_delete_no_meaning":
        case "error_date_no_meaning":
            return "OOPS!!! Please input the number of the duck.task.Task :-(\n";
        case "error_done_non_existed_task":
        case "error_delete_non_existed_task":
        case "error_date_non_existed_task":
            return "OOPS!!! the duck.task.Task you choosing isn't existed :-(\n";
        case "error_todo_empty":
        case "error_deadline_empty":
        case "error_event_empty":
            return "OOPS!!! The description of a task cannot be empty. :-(\n";
        case "error_deadline_by":
            return "OOPS!!! The deadline of a deadline task should be meaningful. :-(\n"
                    + "Please enter according to the format eg.description /by YYYY-MM-DD\n";
        case "error_event_at":
            return "OOPS!!! The period of a event task should be meaningful. :-(\n"
                    + "Please enter according to the format eg.description /at YYYY-MM-DD\n";
        case "find_empty":
            return "OOPS!!! Please enter the keyword of task. :-(\n";
        case "error_IO":
            return "OOPS!!! Something wrong happens in IO. :-(\n";
        default:
            return "error";
        }
    }

    /**
     * show the reply for done command
     *
     * @param number the number of task in task list
     * @param tasks  task list
     */
    public String getDoneReply(int number, TaskList tasks) {
        return "Nice! I've marked this task as done:\n"
                + (number + 1) + "." + tasks.getTask(number).getTaskInfo() + "\n";

    }

    /**
     * show the reply for delete command
     *
     * @param number the number of task in task list
     * @param tasks  task list
     */
    public String getDeleteReply(int number, TaskList tasks) {

        return "Noted. I've removed this task:\n "
                + (number + 1) + "." + tasks.getTask(number).getTaskInfo() + "\n"
                + "Now you have " + (tasks.getSizeOfTasks() - 1) + " tasks in the list.\n";

    }

    /**
     * show the reply for date command
     *
     * @param number the number of task in task list
     * @param tasks  task list
     */
    public String getDateReply(int number, TaskList tasks) {

        return tasks.getTask(number).getPeriodDays();

    }

    /**
     * show the reply for find command
     *
     * @param result the finding result string array
     */
    public String getFindReply(String[] result) {

        if (result[0] == null) {
            return "Sorry, I don't find the task\n";
        }
        StringBuffer stringList = new StringBuffer();
        stringList.append("Here are the tasks in your list:\n");
        for (int i = 0; result[i] != null; i++) {
            stringList.append(result[i] + "\n");
        }
        return stringList.toString();
    }

    /**
     * show the reply for schedule command
     *
     * @param scheduleTask use tasks to form schedule
     * @return schedule string
     */
    public String getScheduleReply(
            ArrayList<ArrayList<String>> scheduleTask) {
        String scheduleString = "Here are your schedule of tasks\n";
        scheduleString += "have not determined date:\n";
        for (int i = 1; i < scheduleTask.get(0).size(); i++) {
            scheduleString += scheduleTask.get(0).get(i) + "\n";
        }
        scheduleString += "\n";

        for (int i = 1; i < scheduleTask.size(); i++) {
            scheduleString += scheduleTask.get(i).get(0) + "\n";
            for (int j = 1; j < scheduleTask.get(i).size(); j++) {
                scheduleString += scheduleTask.get(i).get(j) + "\n";
            }
            scheduleString += "\n";
        }
        return scheduleString;
    }
}
