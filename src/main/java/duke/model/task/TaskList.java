package duke.model.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The list that contains and holds all the tasks imported/added by the user
 * handles operation to the list including add, update and delete
 */
public class TaskList {
    private List<ListItem> listItems;

    /**
     * Constructor that initiates a new empty list
     */
    public TaskList() {
        this.listItems = new ArrayList<>();
    }

    /**
     * Overloaded Constructor that takes in a list
     */
    public TaskList(List<ListItem> inputList) {
        this.listItems = new ArrayList<ListItem>(inputList);
    }

    /**
     * Immutable way to add the new task by returning a new list
     * @param task the task to be added to the list
     */
    public TaskList addCommand(ListItem task) {
        List<ListItem> tempList = new ArrayList<>(this.listItems);
        tempList.add(task);
        return new TaskList(tempList);
    }

    /**
     * Mutable way to add the new task by modifying the list
     * @param task the task to be added to the list
     */
    public void addCommandMutable(ListItem task) {
        this.listItems.add(task);
    }

    public List<ListItem> getListItems() {
        return this.listItems;
    }

    /**
     * Mutable way to mark the task as done by directly changing item in the list
     *
     * @param index the index of the item to be changed
     */
    public void markItemAsDone(int index) {
        int correctIndex = index - 1;
        assertIndexInRange(correctIndex);
        ListItem tempItem = this.listItems.get(correctIndex).markAsDone();
        assert tempItem.isDone(); // check the updated item's done status is true
        this.listItems.set(correctIndex, tempItem);
    }

    /**
     * Update the task with a new tag by add the string tag to the listItem's tagList
     * @param index the index of the task the user wish to update
     * @param tag the tag name that the user wish to assign the task to
     */
    public void updateItemTag(int index, String tag) {
        int correctIndex = index - 1;
        this.listItems.get(correctIndex).addNewTagMutable(tag);
    }

    /**
     * Mutable way to delete the task by directly removing item in the list
     * @param index the index of the item to be changed
     */
    public void deleteCommandMutable(int index) {
        int correctIndex = index - 1;
        assertIndexInRange(correctIndex);
        this.listItems.remove(correctIndex);
    }

    /**
     * Look for all items that contains the <code>keyword</code> and return the list
     * @param keyword the keyword to be searched through the TaskList, in SQL LIKE syntax
     */
    public TaskList findMatchingItems(String keyword) {
        if (keyword.contains("#")) {
            System.out.println("#");
            return new TaskList(listItems.stream().filter(
                x -> x.containTag(keyword.replace("#", ""))).collect(Collectors.toList()));
        } else {
            return new TaskList(listItems.stream().filter(
                x -> x.getTask().contains(keyword)).collect(Collectors.toList()));
        }
    }

    @Override
    public String toString() {
        String initStr = "";
        for (int i = 0; i < this.listItems.size(); i++) {
            ListItem tempItem = this.listItems.get(i);
            System.out.println(tempItem.getClass().getSimpleName());
            initStr = initStr + (tempItem.getClass().getSimpleName().charAt(0)
                    + "|" + tempItem.isDone() + "|" + tempItem.getTask() + tempItem.getDate() + "|"
                    + tempItem.printTagsForIO() + "\n");
        }
        return initStr;
    }

    /**
     * a method with an assertion that checks the index is >= 0 so it can be used for indexing with the List
     */
    public void assertIndexInRange(int index) {
        assert index >= 0;
    }
}
