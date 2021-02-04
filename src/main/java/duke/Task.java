package duke;

public class Task {
    public String description;
    public String typeBox;
    public String checkBox;

    public Task(String description, String typeBox) {
        this.description = description;
        this.typeBox = typeBox;
        this.checkBox = "[]";
    }

    /*
     * Marks the task as complete by marking its checkbox with an X.
     */
    public void completeTask(){
        this.checkBox = "[X]";
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        } else if (!(o instanceof Task)) {
            return false;
        } else {
            Task t = (Task) o;
            return (t.typeBox.equals(this.typeBox) && t.description.equals(this.description));
        }
    }
}