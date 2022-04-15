import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import pason.commands.CommandResultType;

/**
 * Ui class for managing outputs and inputs.
 */
public class Ui {
    private Pane pane;

    private Image meImage = new Image(this.getClass().getResourceAsStream("/images/me.png"));
    private Image pasonImage = new Image(this.getClass().getResourceAsStream("/images/pason.png"));

    /**
     * Initialises the Ui object and the scanner class.
     *
     * @param pane  Target pane
     */
    public Ui(Pane pane) {
        this.pane = pane;
    }

    /**
     * Displays the greeting message.
     */
    public void showGreeting() {
        this.showPasonChat("Hello! Welcome back! I'm feeling bored again... "
                + "Give me something to do!", CommandResultType.CHAT_PASON);
    }

    /**
     * Displays the greeting message.
     */
    public void showGoodbye() {
        this.showPasonChat("Bye! I shall go rest now. PAge me when you need me!", CommandResultType.CHAT_PASON);
    }

    /**
     * Prints message with formatted divider.
     *
     * @param message  String to be printed.
     */
    public void showUserChat(String message) {
        this.pane.getChildren().add(
                DialogBox.getUserDialog("You", message, meImage)
        );
    }

    /**
     * Prints message with formatted divider.
     *
     * @param message  String to be printed.
     */
    public void showPasonChat(String message, CommandResultType responseType) {
        switch (responseType) {
        case ERROR:
            this.pane.getChildren().add(
                    ErrorDialogBox.getDialog("PAson", message, pasonImage)
            );
            break;
        case CHAT_PASON:
        case BYE:
            this.pane.getChildren().add(
                    DialogBox.getPasonDialog("PAson", message, pasonImage)
            );
            break;
        default:
        }
    }
}
