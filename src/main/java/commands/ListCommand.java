package commands;

import common.Message;
import data.IDuke;
import data.task.TaskList;
import ui.Ui;

import java.util.stream.IntStream;

public class ListCommand extends Command {

    private final String date;

    private ListCommand(String date, IDuke duke) {
        super(-1, duke);
        this.date = date;
    }


    public static ListCommand getListCommand(String date) {
        return new ListCommand(date, null);
    }

    @Override
    public String execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        if (date == null) {
            return handleDisplay();
        } else {
            return handleDisplay(date);
        }
    }

    private String handleDisplay() {
       // assert duke != null : Message.ERR_DUKE_NOT_INIT.toString();
        TaskList list = duke.getTasks();
        return Ui.display(list);
    }

    private String handleDisplay(String date) {
        //assert duke != null : Message.ERR_DUKE_NOT_INIT.toString();
        TaskList list = duke.getTasks();
        int[] indexes = IntStream
                .range(0, list.size())
                .filter(x -> list.get(x).isSameTime(date))
                .toArray();
        return Ui.display(list, indexes);
    }

    @Override
    public Command setDuke(IDuke duke) {
        return new ListCommand(date, duke);
    }
}

