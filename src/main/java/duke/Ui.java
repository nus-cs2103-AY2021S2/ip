package duke;

import java.util.List;

import duke.task.Task;

/**
 * Encapsulates the Ui component of Duke.
 *
 * @author Aaron Saw Min Sern
 */
public class Ui {
    /**
     * Returns the message when tasklist capacity is exceeded.
     *
     * @return      the message when tasklist capacity is exceeded
     */
    public String getfullDatabaseMessage() {
        return "\tSorry. The database is full!\n";
    }

    /**
     * Returns the message when task is added successfully.
     *
     * @param   taskDescription the description of the task added
     * @param   numberOfTasks   the number of tasks including the added task
     * @return                  the message when task is added successfully
     */
    public String getAddTaskSuccessfulMessage(String taskDescription, int numberOfTasks) {
        String headerText = "\tGot it. I've added this task: \n";
        String addedTaskText = String.format("\tTask added: %s\n", taskDescription);
        String currentTasksText = String.format("\tNow you have %d task%s in the list.\n",
                numberOfTasks, numberOfTasks == 1 ? "" : "s");
        return headerText + addedTaskText + currentTasksText;
    }

    /**
     * Returns the message when task is marked as done.
     *
     * @param   taskDescription the description of the task marked as done
     * @return      the message with the description of the task marked as done
     */
    public String getMarkTaskAsDoneMessage(String taskDescription, int numberOfCompletedTaskLastWeek) {
        String headerText = "\tNice! I've marked this task as done:\n";
        String taskMarkedText = String.format("\t%s\n", taskDescription);
        String completedTasksText = String.format("\tWell done! You have completed %d task(s) last week.\n",
                numberOfCompletedTaskLastWeek);
        return headerText + taskMarkedText + completedTasksText;
    }

    /**
     * Returns the message when index is out of bound.
     *
     * @return      the message when index is out of bound
     */
    public String getIndexOutOfBoundMessage() {
        return "\tOops! The index is out of bound.\n";
    }

    /**
     * Returns the message when all tasks are listed.
     *
     * @param   taskList    the list of task descriptions
     * @param   hasTask     whether there is at least one task
     * @return              the message with all the tasks listed
     */
    public String getListTaskMessage(String taskList, boolean hasTask) {
        String headerText;
        if (!hasTask) {
            headerText = "\tHmm... You do not have any tasks!\n";
        } else {
            headerText = "\tHere are the tasks in your list:\n";
        }
        return headerText + taskList;
    }

    /**
     * Returns the message during exit.
     *
     * @return      the message during exit
     */
    public String getExitMessage() {
        return "\tBye. Hope to see you again soon!\n";
    }

    /**
     * Returns the message when task is deleted successfully.
     *
     * @param   deletedTask     the description of the deleted task
     * @param   numberOfTasks   the number of tasks left after deletion
     * @return                  the message when task is deleted successfully
     */
    public String getDeleteTaskMessage(Task deletedTask, int numberOfTasks) {
        String headerText = "\tNoted. I've removed this task: \n";
        String taskRemovedText = String.format("\t%s\n", deletedTask);
        String currentTasksText = String.format("\tNow you have %d task%s in the list.\n",
                numberOfTasks, numberOfTasks == 1 ? "" : "s");
        return headerText + taskRemovedText + currentTasksText;
    }

    /**
     * Returns the message when no tasks exist.
     *
     * @return      the message when no tasks exist
     */
    public String getNoTaskExistsMessage() {
        return "\tHmm... You do not have any tasks!\n";
    }

    /**
     * Returns the message when tasklist is filtered with some keyword.
     *
     * @param   matchingTasks   the filtered tasklist
     * @return                  the message with the filtered tasklist
     */
    public String getFindTaskMessage(List<String> matchingTasks) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : matchingTasks) {
            sb.append(String.format("\t%d. %s\n", ++i, str));
        }

        if (matchingTasks.size() == 0) {
            return "\tHmm... You do not have any matching tasks!\n";
        }

        String headerText = "\tHere are the matching tasks in your list:\n";
        return headerText + sb.toString();
    }
}
