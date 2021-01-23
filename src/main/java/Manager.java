import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    public static String line = "------------------------------------------------------";
    private final List<Task> ls;
    private final File file;

    Manager() {
        this.ls = new ArrayList<>();
        this.file = new File("data/Duke.txt");
    }

    public void greeting(){
        System.out.println(line);
        System.out.println("I am Donald Trump, the Greatest American President ever\nWhat can I do for you?");
        System.out.println(line);
    }

    public void addTask(Task task) {
        String res = "\t" + line + "\n\tGot it. I've added this task:\n\t\t" + task.toString() + "\n";
        this.ls.add(task);
        int numOfTasks = ls.size();
        res += "\tNow you have " + numOfTasks + " tasks in the list\n\t" + line;
        System.out.println(res);
    }

    public void finishTask(int index)  {
        Task task = this.ls.get(index - 1);
        task.markAsDone();
        String res = "\t" + line + "\n\t" + "Nice! I've marked this task as done: \n\t\t" + task + "\n\t" + line;
        System.out.println(res);

    }

    public void deleteTask(int index){
        Task task = this.ls.get(index - 1);
        this.ls.remove(index-1);
        int len = this.ls.size();
        String res = "\t" + line + "\n\t" + " Noted. I've removed this task:\n\t\t" + task +
                "\n\tNow you have " + len +" tasks in the list.\n\t" + line;
        System.out.println(res);

    }

    public void  listTask() {
        String res = "";
        if(ls.isEmpty()){
            res = "\t" + line + "\n\tWell Done! All task has been completed\n";
        }
        else{
            res = "\t" + line + "\n\tHere are the tasks in your list:\n";
            for (int i = 0; i < ls.size(); i++) {
                res += "\t" + (i + 1) + "." + ls.get(i) + "\n";
            }
        }

        res += "\t" + line;
        System.out.println(res);
    }
}

