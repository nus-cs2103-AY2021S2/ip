import java.util.*;

public class Task {
    //store tasks in a 2D arrayList
    ArrayList<ArrayList<String>> taskList = new ArrayList<>();
    public Task() {
    }

    //method task into the taskList
    public void addTask(String input) {
        ArrayList<String> taskPair = new ArrayList<>();
        taskPair.add("Not Done");
        taskPair.add(input);
        taskList.add(taskPair);
    }

    //when task is done change the tag to "Done"
    public void taskDone(int index) {
        taskList.get(index).set(0, "Done");
    }

    //get method to get the taskPair at specific index
    public ArrayList<String> get(int index) {
        return this.taskList.get(index);
    }

    //return the length of taskList
    public int size() {
        return taskList.size();
    }

    //format how task is printed out
    public String toString() {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).get(0).equals("Done")) {
                return (i+1) + ".[X] " + (taskList.get(i).get(1));
            } else {
                return (i+1) + ".[ ] " + (taskList.get(i).get(1));
            }
        }
        return null;
    }

}
