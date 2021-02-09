package duke.command;

import duke.DukeException;
import duke.TaskList;

public class SortCommand extends Command {

    /**
     * Instantiates a new SortCommand object.
     * @param commandSplit user input split by spaces.
     */
    public SortCommand(String[] commandSplit) {
        super(commandSplit);
        assert commandSplit[0].equals("sort") : "Must have sort keyword and sort parameter";
    }


    /**
     * Sorts the TaskList based on date or task description in ascending or descending order.
     * @param list the TaskList to be sorted.
     * @return response that the list has been sorted.
     * @throws DukeException if parameter provided by user is incorrect.
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        //What parameter to sort by, e.g. date, task (description)
        String sortParameter = commandSplit[1];
        //Ascending or descending
        String sortDirection = commandSplit[2];

        if (sortParameter.equals("date")) {

            if (sortDirection.equals("asc") || sortDirection.equals("desc")) {
                boolean isAscending = sortDirection.equals("asc");
                return list.sortByDate(isAscending);
            }

            //Neither asc nor desc
            throw new DukeException("Please enter a valid sort direction \"asc\" or \"desc\".");
        }

        if (sortParameter.equals("task")) {

            if (sortDirection.equals("asc") || sortDirection.equals("desc")) {
                boolean isAscending = sortDirection.equals("asc");
                return list.sortByDescription(isAscending);
            }

            //Neither asc nor desc
            throw new DukeException("Please enter a valid sort direction \"asc\" or \"desc\".");
        }

        //Neither date nor task
        throw new DukeException("Please enter a valid sort parameter \"date\" or \"task\".");

    }
}
