package duke;

/*
  Doge Duke implements a virtual pet application that
  returns different commands passed by owner (user).

  @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-01-31
 */

public class Duke {
    public static void main(String[] args) {

        Ui ui = new Ui();
        Storage storage = new Storage();
        CommandList commandList = new CommandList(storage);
        Parser parser = new Parser(commandList);

        ui.printGreeting();
        parser.parseAll();
    }
}