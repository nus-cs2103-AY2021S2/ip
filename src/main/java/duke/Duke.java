package duke;

/*
  Doge Duke implements a virtual pet application that
  returns different commands passed by owner (user).

  @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-01-31
 */


public class Duke {

    final CommandList commandList;
    Storage storage;
    Ui ui;
    Parser parser;


    public Duke() {
        this.storage = new Storage();
        this.commandList = new CommandList(storage);
        this.ui = new Ui();
        this.parser = new Parser(commandList);

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String result = "";
        try {
            result = parser.parseAll(input);
        } catch (Exception e) {
            result = parser.errorHandling(input);
        }
        return result;
    }


}