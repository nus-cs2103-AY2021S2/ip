package duke.system;

/**
 * Represents the object that is responsible for displaying context to the user
 * such as separating line or error
 */
public class Ui {
    public void showLoadingError(){
        System.out.println("Error in loading the list");
    }

    /**
     * Print the input in the console
     */
    public void showText(String input){
        System.out.println(input);
    }

    public void showError(String input){
        System.out.println("Error: " + input);
    }

    public void showLine(){
        System.out.println("____________________________________________________________\n");
    }
}
