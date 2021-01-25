package duke;

public class Task {
    protected String title;
    protected boolean status;

    public Task(){

    }

    public Task(String title){
        this.title = title;
        this.status = false;
    }

    public Task(String title, boolean b){
        this.title = title;
         this.status = b;
    }

    public void setCompleted(){
        this.status = true;
    }

    public String isCompleted(){
        return (this.status ? "\u2718" : " ");
    }

    public String getTaskName(){
        return this.title;
    }

    public String changeFormat(){
        return ":" +  this.status + ":" + this.getTaskName();
    }

    public Task changeToTaskFormat(String string_task) {

        if(string_task.charAt(0) == 'T'){
            String[] tasks = string_task.split(":");
            return new ToDos(tasks[2], Boolean.parseBoolean(tasks[1]));

        } else if(string_task.charAt(0) == 'D'){
            String[] tasks = string_task.split(":");
            return new Deadlines(tasks[2], Boolean.parseBoolean(tasks[1]), tasks[3]);

        }else if(string_task.charAt(0) == 'E'){
            String[] tasks = string_task.split(":");
            return new Events(tasks[2], Boolean.parseBoolean(tasks[1]), tasks[3]);
        }
        else{
            return new Task();
        }
    }

    @Override
    public String toString() {
        return "[" + this.isCompleted() + "] " + this.getTaskName();
    }
}
