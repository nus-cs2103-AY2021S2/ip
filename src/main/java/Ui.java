/**
 * Contains interactions with user.
 */
public class Ui {

    public static void sayHello() {
        System.out.println("\nHello! I'm Flamingo. What's on your to-do list?\n");
    }

    public static void sayBye() {
        System.out.println("\nBye-o Flamingo!\n");
    }

    /**
     * Shows error if file with save data cannot be found.
     */
    public static void showLoadingError() {
        System.out.println("\nOh No Flamingo! The save data could not be loaded.\n");
    }
}
