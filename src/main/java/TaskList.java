import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list; 

    TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public void addTodo(String task) {
        this.list.add(new Todo(task));
    }

    public void addDeadline(String task, LocalDateTime date){
        this.list.add(new Deadline(task, date));
    }

    public void addEvent(String task, LocalDateTime date) {
        this.list.add(new Event(task, date));
    }

    public Task deleteTaskAtIndex(int index) {
        Task toDelete = this.list.get(index);
        this.list.remove(index);
        return toDelete;
    }

    public int size() {
        return this.list.size();
    }

    public String write(int index) {
        return this.list.get(index).toCommand();
    }

    public void markDone(int index) {
        this.list.get(index).isCompleted();
    }

    public Task getTaskAtIndex(int index){
        return this.list.get(index);
    }

    public Task getLastAddedTask() {
        return this.list.get(this.list.size() - 1);
    }

    public void listAllTasks() {
        if (list.size() == 0) {
            System.out.print("You have 0 tasks in your list. ");
        } else {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < list.size(); i++) {
                Task current = list.get(i);
                System.out.println(i+1 + ". " + current);
            }
        }
    }

    public TaskList filterFind(String keyword) {
        TaskList filteredList = new TaskList();
        for (int i = 0; i < list.size(); i++) { 
            Task current = list.get(i); 
            if (current.taskMatch(keyword)) {
                filteredList.add(current); 
            }
        }
        return filteredList;
    }


}
