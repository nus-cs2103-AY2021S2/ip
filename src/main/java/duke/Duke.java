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

    public Duke() {
        Storage storage = new Storage();
        this.commandList = new CommandList(storage);
    }
    public void run() {
        Ui ui = new Ui();
        Parser parser = new Parser(commandList);

        ui.printGreeting();
        parser.parseAll();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}