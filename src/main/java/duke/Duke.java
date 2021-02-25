package duke;

/*
  Doge Duke implements a virtual pet application that
  returns different commands passed by owner (user).

  @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-01-31
 */


import javafx.scene.control.Label;

public class Duke {

    final CommandList commandList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor of Duke class
     */
    public Duke() {
        this.storage = new Storage();
        this.commandList = new CommandList(storage);
        this.ui = new Ui();
        this.parser = new Parser(commandList);
    }

    /**
     * Generate a response to user input for GUI
     *
     * @param input raw user input from GUI
     */
    public Label getResponse(String input) {
        String result = "";
        Label response = null;
        try {
            result = parser.parseAll(input);

        } catch (Exception e) {
            result = parser.errorHandling(input);
        }
        response = new Label(result);
        return response;
    }

}
