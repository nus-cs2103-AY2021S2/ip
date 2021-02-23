package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;
import java.lang.StringBuffer;
import duke.tasktype.Task;

public class Ui {
    public String makeDone(String task){
        assert(task!= null);
        return String.format("Following task is marked done: \n" + task);
    }

    public String showTaskAdded(String task , List<Task> taskList){
        assert(task != null && taskList != null);
        return String.format( "Added the following task : \n" +
                "%s\n" + "You now have %d tasks in your list.\n", task, taskList.size());
    }

    public String showTaskDeleted(String task , List<Task> taskList){
        assert(task != null && taskList != null);
        return String.format("The following task has been removed:\n " +
                "%s\n" + " You now have %d tasks in your list.\n", task, taskList.size());
    }

    public String showList(List<Task> taskList){
        assert(taskList != null);
        int counter = 1;
        StringBuffer showList = new StringBuffer();
        showList.append(" These are the tasks in your list:\n");
        for (Task task : taskList){
            showList.append(" " + counter + ". " + task.getStatus() + "'n");
            counter++;
        }
        return showList.toString();
    }

    public String sayGoodbye(){
        return "Goodbye!";
    }

    public String showTasks(String input, List<Task> taskList) throws duke.DukeException{
        assert (input != null && taskList != null);
        if(input.length() <= "find ".length()){
            throw new duke.DukeException("Please add what you want to find.");
        }
        String find = input.substring("find ".length());

        if (find.trim().equals("")){
            throw new duke.DukeException("Search string is empty");
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

    public String showDue(String input, List<Task> taskList) throws duke.DukeException{
        assert (input != null && taskList != null);
        if(input.length() <= "due ".length()){
            throw new duke.DukeException("Please add what you want to find.");
        }
        String find = input.substring("due ".length());
        LocalDateTime dateTime = LocalDateTime.parse(find, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));

        if (find.trim().equals("")){
            throw new duke.DukeException("Search string is empty");
        }
        Predicate<LocalDateTime> stringPredicate = x -> x.equals(dateTime);
        StringBuffer tasks = new StringBuffer();
        tasks.append("Here are the tasks you asked for: \n");
        int counter =1;
        for(Task t : taskList){
            if(t.getDue() != null && stringPredicate.test(t.getDue())){
                tasks.append(counter + ". " + t.getStatus() + "\n");
                counter++;
            }
        }
        return tasks.toString();
    }


    
}
