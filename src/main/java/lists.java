import java.util.ArrayList;
import java.util.List;

public class lists {
    List<listItem> dukeList;

    public lists(){
        this.dukeList = new ArrayList<>();
    }

    public lists(String task){
        this.dukeList = new ArrayList<listItem>();
        dukeList.add(new listItem(task));
    }

    public lists(List<listItem> inputList){
        this.dukeList = new ArrayList<listItem>(inputList);
    }

    public lists addCommand(listItem task){
        List<listItem> tempList = new ArrayList<>(this.dukeList);
        tempList.add(task);
        return new lists(tempList);
    }

    public void addCommandMutable(listItem task){
        this.dukeList.add(task);
    }

    public List<listItem> getDukeList(){
        return this.dukeList;
    }

    public void updateItemMutable(int index){
        int correctIndex = index - 1;
        listItem tempItem = this.dukeList.get(correctIndex).markAsDone();
        this.dukeList.set(correctIndex, tempItem);
    }
}
