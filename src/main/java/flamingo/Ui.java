package flamingo;

/**
 * Contains interactions with user.
 */
public class Ui {

    public static String sayHello() {
        return "Hello! I'm Flamingo. What's on your to-do list?";
    }

    public static String sayBye() {
        return "Bye-o Flamingo!";
    }

    /**
     * Shows error if file with save data cannot be found.
     */
    public static String showLoadingError() {
        return "Oh No Flamingo! The save data could not be loaded.";
    }
}
