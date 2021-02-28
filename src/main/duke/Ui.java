package main.duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;
import java.lang.StringBuffer;
import main.duke.tasktype.Task;

public class Ui {
    /**
     * Done command in UI
     * @param task the current task's status
     * @return string including task name
     */
    public String makeDone(String task){
        assert(task!= null);
        return String.format("Following task has been marked done: \n" + task);
    }

    /**
     * Show the added task
     * @param task Status of the task being operated on
     * @param taskList Collection of current list
     * @return string including the added task name and total task number
     */
    public String showTaskAdded(String task , List<Task> taskList){
        assert(task != null && taskList != null);
        return String.format( "Added the following task : \n" +
                "%s\n" + "You now have %d tasks in your list.\n", task, taskList.size());
    }

    /**
     * Show the deleted task
     * @param task Status of the task being operated on
     * @param taskList Collection of current list
     * @return string including the deleted task name and total task number
     */
    public String showTaskDeleted(String task , List<Task> taskList){
        assert(task != null && taskList != null);
        return String.format("The following task has been removed:\n " +
                "%s\n" + " You now have %d tasks in your list.\n", task, taskList.size());
    }

    /**
     *
     * @param taskList Collection of current list
     * @return All of tasks in the tasklist
     */
    public String showList(List<Task> taskList){
        assert(taskList != null);
        int counter = 1;
        StringBuffer showList = new StringBuffer();
        showList.append(" These are the tasks in your list:\n");
        for (Task task : taskList){
            showList.append(" " + counter + ". " + task.getStatus() + "\n");
            counter++;
        }
        return showList.toString();
    }

    /**
     * Goodbye command in UI
     *
     */
    public String sayGoodbye(){
        return "Goodbye!";
    }

    /**
     * Help command in UI
     *
     */
    public String giveHelp(){
        String helpString = "Try one of these:\n"
                + "list : prints you the list of store tasks\n"
                + "bye : terminates the program\n"
                + "todo [your task input] : adds a todo type task\n"
                + "event [your task input] /at [YYYY-MM-DD hhmm]: adds an event type task\n"
                + "deadline [your task input] /by [YYYY-MM-DD hhmm]: adds a deadline type task\n"
                + "delete[number] : deletes the selected task\n"
                + "done[number] : mark the selected task as done(notation will be 0 for done and X for not done)\n"
                + "find [search string] : finds tasks that matches the string description\n"
                + "\nExamples\n"
                + "todo sleep : adds a todo type task of description sleep\n"
                + "event toSleep /at 2020-01-01 0000 : adds an event type task of description toSleep " +
                "with dateTime 2020-01-01 0000\n"
                + "deadline wakeup /by 2020-01-01 0001 : adds a deadline type task of description wakeup " +
                "with dateTime 2020-01-01 0001\n"
                + "delete[1] : deletes the task with index 1 (will only work if the task with your index exists\n"
                + "done[1] : marks the task with index 1 to done\n"
                + "find[sleep] : finds task descriptions that starts with sleep\n"
                + "[IMPORTANT] Note that the number inputted for done and delete should be equals to or " +
                "less than the total number of tasks in your list!";
        return helpString;
    }

    /**
     * Show list of tasks with given keyword
     * @param input Keyword used for find command
     * @param taskList Collection of current list
     * @throws DukeException Throws exception if search string is empty
     */
    public String showTasks(String input, List<Task> taskList) throws DukeException{
        assert (input != null && taskList != null);
        if(input.length() <= "find ".length()){
            throw new DukeException("Please add what you want to find.");
        }
        String find = input.substring("find ".length());

        if (find.trim().equals("")){
            throw new DukeException("Search string is empty");
        }
        Predicate<String> stringPredicate = x -> x.contains(find);
        StringBuffer tasks = new StringBuffer();
        tasks.append("Here are the tasks you asked for: \n");
        int counter =1;
        for(Task t : taskList){
            if(t.getName() != null && stringPredicate.test(t.getName())){
                tasks.append(counter + ". " + t.getStatus() + "\n");
                counter++;
            }
        }
        return tasks.toString();
    }

}
