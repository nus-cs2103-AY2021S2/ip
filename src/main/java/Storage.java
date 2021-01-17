import java.util.List;
import java.util.ArrayList;

class Storage {
    private final List<Task> list;

    Storage(){
        list = new ArrayList<>();
    }

    void add(Task task){
        list.add(task);
        System.out.println("added: " + task.getDescription());
    }

    void listOut(){
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for(Task t: list){
            System.out.println(index + "." + t);
            index++;
        }
    }

    void markTaskAsDone(String num){
        int index = Integer.valueOf(num) - 1;
        Task targetTask = list.get(index);
        targetTask.markAsDone();
        System.out.println("Nice! I've mark this task as done");
        System.out.println(targetTask);
    }
}
