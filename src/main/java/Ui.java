package duke;

public class Ui {
    public String makeDone(String task){
        assert(task!= null);
        return String.format("Following task is marked done: \n" + task);
    }

    public String showTaskAdded(String task, List<Task> taskList){
        assert(task != null && taskList != null);
        return String.format( "Added the following task : \n" +  "%s\n" + "You now have %d tasks in your list.\n", task, taskList.size());
    }

    public String showTaskDeleted(String task, List<Task> taskList){
        assert(task != null && taskList != null);
        return String.format("The following task has been removed:\n " + "%s\n" + " You now have %d tasks in your list.\n", task, taskList.size()); 
    }

    public String showList(List<task> taskList){
        assert(taskList != null);
        int counter = 1;
        Stringbuffer showList = new Stringbuffer();
        showList.append(" These are the tasks in your list:\n");
        for (Task task : taskList){
            showList.append(" " + counter + ". " + task.getStatus() + "'n");
            counter++;
        }
        return showList.toString;
    }

    public String sayGoodbye(){
        return "Goodbye!";
    }
    
}
