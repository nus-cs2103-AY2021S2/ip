public class Statistics {

    private int done;
    private int delete;
    private int todo;
    private int deadline;
    private int event;
    private int error;
    private int undone;
    private int size;

    /**
     * Constructor to create a new statistics.
     */
    public Statistics() {
        done = 0;
        delete = 0;
        todo = 0;
        deadline = 0;
        event = 0;
        undone = 0;
        size = 0;
        error = 0;
    }

    /**
     * Update the variable in the statistics.
     *
     * @param change plus or minus one
     * @param variable string
     */
    public void changeStat(int change, String variable) {
        switch (variable) {
        case "todo":
            todo = todo + change;
            size = size + change;
            break;
        case "deadline":
            deadline = deadline + change;
            size = size + change;
            break;
        case "event":
            event = event + change;
            size = size + change;
            break;
        case "done":
            done = done + change;
            undone = size - done;
            break;
        case "delete":
            delete = delete + change;
            break;
        default:
            error = error++;
        }
    }

    /**
     * Show the variable in the statistics.
     *
     * @param variable string
     * @return stat
     */
    public int showStat(String variable) {
        int stat;
        switch (variable) {
        case "todo":
            stat = todo;
            break;
        case "deadline":
            stat = deadline;
            break;
        case "event":
            stat = event;
            break;
        case "done":
            stat = done;
            break;
        case "undone":
            stat = undone;
            break;
        case "delete":
            stat = delete;
            break;
        case "error":
            stat = error;
            break;
        default:
            error++;
            stat = 0;
        }
        return stat;
    }

}
