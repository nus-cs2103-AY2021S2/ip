public class Ui {

    public void displayWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void displayClosingMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void displayListMessage(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.getList());
    }

    public void displayTaskAdded(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.taskStatus());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list");
    }

    public void displayTaskCompleted(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.taskStatus());
    }

    public void displayTaskDeleted(Task task, TaskList taskList) {
        System.out.println("Noted. I have removed this task:");
        System.out.println(task.taskStatus());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list");
    }

    public void displayLoadingError() {
        System.out.println("List of Tasks not found, created new Task List.");
    }
}
