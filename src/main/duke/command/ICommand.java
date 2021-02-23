package main.duke.command;
import main.duke.DukeList;
import main.duke.Ui;
import main.duke.DukeException;
import main.duke.io.Storage;
public interface ICommand {
    public boolean isBye();
    public String run(Ui ui, DukeList dukeList, Storage storage) throws DukeException;
}
