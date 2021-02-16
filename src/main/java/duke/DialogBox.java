package duke;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
//public class DialogBox extends HBox {
//    @FXML
//    private Label dialog;
//    @FXML
//    private ImageView displayPicture;
//
//    private DialogBox(String text, Image img) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
//            fxmlLoader.setController(this);
//            fxmlLoader.setRoot(this);
//            fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        dialog.setText(text);
//        displayPicture.setImage(img);
//    }
//
//    /**
//     * Flips the dialog box such that the ImageView is on the left and text on the right.
//     */
//    private void flip() {
//        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//        Collections.reverse(tmp);
//        getChildren().setAll(tmp);
//        setAlignment(Pos.TOP_LEFT);
//    }
//
//    public static DialogBox getUserDialogue(String text, Image img) {
//        return new DialogBox(text, img);
//    }
//
//    public static DialogBox getDukeDialogue(String text, Image img) {
//        var db = new DialogBox(text, img);
//        db.flip();
//        return db;
//    }
//}