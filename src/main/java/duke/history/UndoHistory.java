package duke.history;

import duke.task.Task;
import duke.task.TaskManager;

public class UndoHistory extends History {
    public UndoHistory() {
        super();
    }

    public String undo() {
        return pop();
    }

//    public void push(String input, TaskManager.CommandType type) {
////        BYE, HELP, LIST, DONE, UNDONE, DELETE, RESTORE, FIND, UNDO,
////                REDO, TODO, DEADLINE, EVENT, INVALID };
//        HistoryBlock newBlock;
//
//        switch (type) {
//            case DONE:
//                newBlock = new HistoryBlock(input, TaskManager.CommandType.UNDONE);
//            case TODO:
//                newBlock = new HistoryBlock()
//        }
//    }
//
//    public void push(Task task, TaskManager.CommandType type) {
////        BYE, HELP, LIST, DONE, UNDONE, DELETE, RESTORE, FIND, UNDO,
////                REDO, TODO, DEADLINE, EVENT, INVALID };
//        HistoryBlock newBlock;
//
//        switch (type) {
//            case DELETE:
//                newBlock = new HistoryBlock(task, type);
//        }
//    }
}
