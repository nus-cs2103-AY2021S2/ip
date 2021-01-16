import java.util.ArrayList;

public class TaskManager {
    private ArrayList<String> list = new ArrayList<>(100);

    public void add(String task) {
        list.add(task);
        System.out.println("added: " + task + "\n");
    }

    public void printList(){
        for(int i = 0; i < list.size(); i++){
            System.out.println((i + 1) + ") " + list.get(i));
        }
        System.out.println("");
    }
}
