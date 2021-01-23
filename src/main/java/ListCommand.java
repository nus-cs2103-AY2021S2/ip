public class ListCommand extends Command {
    TaskList list;

    ListCommand(TaskList list) {
        this.list = list;
    }

    @Override
    void executeAndPrint(TaskList list, int length) throws DukeException {
        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i < list.getSize(); i++) {
            resultStr.append(StringParser.newLiner((i + 1) + "."
                    + list.getJob(i).toString(), length));
        }
        if (list.getSize() == 0) {
            System.out.print("List is empty\n");
        } else {
            System.out.print(resultStr.toString());
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
