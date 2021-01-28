import java.util.ArrayList;

public class TaskList {

    public TaskList() {

    }
    public void addToList(ArrayList<Task> arrayList, Task task) {
        task.index = arrayList.size() + 1;
        arrayList.add(task);
        System.out.println(task);
    }

    public void deleteFromList(ArrayList<Task> arrayList, int deletedNumber) {
        int moved = deletedNumber - 1;
        System.out.println(arrayList.get(moved));
        arrayList.remove(arrayList.get(moved));
        for (int i = moved; i < arrayList.size(); i++) {
            arrayList.set(i, arrayList.get(i).decreaseIndex());
        }
    }
}
