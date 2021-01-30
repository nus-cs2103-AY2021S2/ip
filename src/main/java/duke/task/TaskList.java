package duke.task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<ListItem> dukeList;

    public TaskList(){
        this.dukeList = new ArrayList<>();
    }

//    public lists(String task){
//        this.dukeList = new ArrayList<listItem>();
//        dukeList.add(new listItem(task));
//    }

    public TaskList(List<ListItem> inputList){
        this.dukeList = new ArrayList<ListItem>(inputList);
    }

    public TaskList addCommand(ListItem task){
        List<ListItem> tempList = new ArrayList<>(this.dukeList);
        tempList.add(task);
        return new TaskList(tempList);
    }

    public void addCommandMutable(ListItem task){
        this.dukeList.add(task);
    }

    public List<ListItem> getDukeList(){
        return this.dukeList;
    }

    public void updateItemMutable(int index){
        int correctIndex = index - 1;
        ListItem tempItem = this.dukeList.get(correctIndex).markAsDone();
        this.dukeList.set(correctIndex, tempItem);
    }

    public void deleteCommandMutable(int index){
        int correctIndex = index - 1;
        this.dukeList.remove(correctIndex);
    }

    public TaskList findItem(String keyword){
        List<ListItem> tempList = new ArrayList<>();
        for(ListItem item: dukeList){
            if(item.getTask().contains(keyword)){
                tempList.add(item);
            }
        }
        return new TaskList(tempList);
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
