import java.util.Scanner;

public class Ui {
    //deals with interactions with the user
    
    private static String greetingCat = "(=^. .^=)";
    private static String goodByeCat = "(=^. .^=*)";
    private static String goCat = "(=^. .^=)~~";
    private static String gdJobCat = "\\(=^> <^=)/";
    private static String errorCat = "(=^> <^=)'''";
    
    private static Scanner sc = new Scanner(System.in);

    public void lines() {
        System.out.println("__________________________" +
                "__________________________________");
    }
    
    public void greet() {
        System.out.println(greetingCat);
        System.out.println("Mew! I'm Chat the Cat");
        System.out.println("What can I do for you?");
    }
    
    public void goodbye() {
        System.out.println("*** Goodbye *** " + goodByeCat);
    }

    public void printAddedSuccess(Task task, int size) { 
        System.out.println(goCat);
        System.out.println("Mew! I've added this task:");
        System.out.println(task);
        System.out.println(String.format("\n** Now you have %d tasks in the list **", size));
    }
    
    public void printDeleteSuccess(Task task, int size) {
        System.out.println(goCat);
        System.out.println("Mew! I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("\n** Now you have %d tasks in the list **", size));
    }
    
    public void printWellDone(Task task) {
        System.out.println(gdJobCat);
        System.out.println("Mew! I've marked this task as done:");
        System.out.println(task);
        System.out.println("\n* Good job, you deserve a kit-kat *");
    }
    
    public String readCommand() { 
        return sc.nextLine();
    }
    
    public void showLoadingError() { 
        System.out.println(new ChatException("Error loading file"));
    }
    
    public void showError(ChatException e) { 
        System.out.println(errorCat + "\n" + e);
    }

    public void list(TaskList tasks) {
        int i = 0;
        System.out.println(" * list *");
        while (i < tasks.getTaskList().size()) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks.getTaskList().get(i));
            i++;
        }
    }
    
}
