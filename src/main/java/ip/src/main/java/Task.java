package ip.src.main.java;

/**
 * Task class that stores Task details and Task status.
 *
 */

public class Task {
    //int id;
    String content;
    public boolean done = false;
    public Task(String content) {
        //this.id = id;
        this.content = content;

    }

    /**
     * Updates the Task Status to completed.
     *
     */

    public void markDone(){
        this.done = true;

    }

    /**
     * Checks if Task content has matching keyword.
     *
     * @return Returns True if task content has keyword.
     */

    public boolean isMatch(String keyword){
        return this.content.contains(keyword);
    }

    public String toString(){
        return this.content;
    }


}


