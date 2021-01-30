package duke.task;
import java.util.ArrayList;
import java.util.List;

/**
 * The list that contains and holds all the tasks imported/added by the user
 * handles operation to the list including add, update and delete
 */
public class TaskList {
    List<ListItem> dukeList;

    public TaskList(){
        this.dukeList = new ArrayList<>();
    }

    public TaskList(List<ListItem> inputList){
        this.dukeList = new ArrayList<ListItem>(inputList);
    }

    /**
     * Immutable way to add the new task by returning a new list
     * @param task the task to be added to the list
     */
    public TaskList addCommand(ListItem task){
        List<ListItem> tempList = new ArrayList<>(this.dukeList);
        tempList.add(task);
        return new TaskList(tempList);
    }

    /**
     * Mutable way to add the new task by modifying the list
     * @param task the task to be added to the list
     */
    public void addCommandMutable(ListItem task){
        this.dukeList.add(task);
    }

    public List<ListItem> getDukeList(){
        return this.dukeList;
    }

    /**
     * Mutable way to mark the task as done by directly changing item in the list
     * @param index the index of the item to be changed
     */
    public void updateItemMutable(int index){
        int correctIndex = index - 1;
        ListItem tempItem = this.dukeList.get(correctIndex).markAsDone();
        this.dukeList.set(correctIndex, tempItem);
    }

    /**
     * Mutable way to delete the task by directly removing item in the list
     * @param index the index of the item to be changed
     */
    public void deleteCommandMutable(int index){
        int correctIndex = index - 1;
        this.dukeList.remove(correctIndex);
    }

    @Override
    public String toString(){
        String initStr = "";
        for (int i = 0; i < this.dukeList.size(); i++) {
            ListItem tempItem = this.dukeList.get(i);
            initStr = initStr + (tempItem.getClass().getName().charAt(0) + "|" + tempItem.getDoneStatus() + "|" + tempItem.getTask() + tempItem.getDate() + "\n");
        }
        return initStr;
    }
}
