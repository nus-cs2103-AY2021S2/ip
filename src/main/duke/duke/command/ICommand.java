package duke.command;
import duke.DukeList;
import duke.Ui;
import duke.DukeException;
import duke.io.Storage;
public interface ICommand {
    public boolean isBye();
    public String run(Ui ui, DukeList dukeList, Storage storage) throws DukeException;
}
