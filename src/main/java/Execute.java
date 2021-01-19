//TODO consider using enum
public class Execute {
    public DukeBot bot;

    public Execute (DukeBot bot) {
        this.bot = bot;
    }

    public void executeCommand(String command) {
        String arr[] = command.split(" ", 2);
        String firstWord = arr[0];
        String time;
        switch (firstWord) {
            case "bye":
                bot.exit();
                break;
            case "list":
                bot.displayTasks();
                break;
            case "done":
                String num = arr[1];
                //TODO exception handling if num is not a number
                bot.markAsDone(Integer.valueOf(num));
                break;
            case "todo":
                String toDo = arr[1];
                bot.addTask(new ToDo(toDo));
                break;
            case "deadline":
                String deadline = arr[1];
                time = this.getDateTime(deadline, "/by");
                bot.addTask(new Deadline(getMsg(deadline, "/by"), getDateTime(deadline, "by")));
                break;
            case "event":
                String event = arr[1];
                time = this.getDateTime(event, "/at");
                bot.addTask(new Event(getMsg(event, "/at"), getDateTime(event, "at")));
                break;
            default:
                bot.complain();

        }
    }

    private String getDateTime(String text, String search) {
        //TODO handle case when text does not contain "/at" or "/by"
        int index = text.indexOf(search);
        return text.substring(index + 3);
    }

    private String getMsg(String text, String search) {
        //TODO handle case when text does not contain "/at" or "/by"
        int index = text.indexOf(search);
        return text.substring(0, index - 1);
    }
}
