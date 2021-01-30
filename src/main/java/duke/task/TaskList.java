package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<ListItem> listItems;

    public TaskList() {
        this.listItems = new ArrayList<>();
    }

    public TaskList(List<ListItem> inputList) {
        this.listItems = new ArrayList<ListItem>(inputList);
    }

    public TaskList addCommand(ListItem task) {
        List<ListItem> tempList = new ArrayList<>(this.listItems);
        tempList.add(task);
        return new TaskList(tempList);
    }

    public void addCommandMutable(ListItem task) {
        this.listItems.add(task);
    }

    public List<ListItem> getListItems() {
        return this.listItems;
    }

    public void updateItemMutable(int index) {
        int correctIndex = index - 1;
        ListItem tempItem = this.listItems.get(correctIndex).markAsDone();
        this.listItems.set(correctIndex, tempItem);
    }

    public void deleteCommandMutable(int index) {
        int correctIndex = index - 1;
        this.listItems.remove(correctIndex);
    }

    @Override
    public String toString() {
        String initStr = "";
        for (int i = 0; i < this.listItems.size(); i++) {
            ListItem tempItem = this.listItems.get(i);
            initStr = initStr + (tempItem.getClass().getName().charAt(0) + "|" + tempItem.getDone() + "|" + tempItem.getTask() + tempItem.getDate() + "\n");
        }
        return initStr;
    }
}
