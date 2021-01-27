public class Ui {
    private String greet; 
    private String bye;

    public Ui() {
        greet = "Hello! I'm Duke \n What can I do for you?";
        bye = "Bye. Hope to see you again soon!";
    }

    public void welcomeUser() {
        System.out.println(greet);
    }

    public void userLeaving() {
        System.out.println(bye);
    }

    public void userDoneTask(String task) {
        System.out.println("Nice! I've marked this task as done:\n " + task + ")");
    }

    public void userAddTask(TaskList list) {
        System.out.println("Got it. I've added this task: \n  " 
                            + list.getLastAddedTask().toString() 
                            + "\nNow you have " + String.valueOf(list.size()) + " tasks in the list.");
    }

    public void userDeleteTask(Task task, TaskList list) {
        System.out.println("Noted. I've removed this task: \n " + task + "\nNow you have " + list.size() + " tasks in the list.");
    }

    
}