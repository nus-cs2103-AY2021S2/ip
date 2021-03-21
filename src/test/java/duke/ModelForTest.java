package duke;

import java.util.Arrays;
import java.util.Optional;

import duke.controller.ListController;
import duke.controller.Parser;
import duke.controller.UiController;
import duke.model.task.Deadline;
import duke.model.task.ListItem;
import duke.model.task.TaskList;
import duke.model.task.Todo;

public class ModelForTest {
    static final Duke DUKE_FOR_TEST = new Duke();
    static final TaskList TASKLIST_EMPTY = new TaskList();
    static final ListItem TASK_FOR_TEST = new Todo("test tag command");
    static final ListItem[] TASK_ARRAY_FOR_TEST = {TASK_FOR_TEST};
    static final TaskList TASKLIST_NOT_EMPTY = new TaskList(Arrays.asList(TASK_ARRAY_FOR_TEST));
    static final UiController UI_CONTROLLER_FOR_TEST = new UiController(DUKE_FOR_TEST, TASKLIST_NOT_EMPTY);
    static final ListController LIST_CONTROLLER_FOR_TEST = new ListController(DUKE_FOR_TEST, TASKLIST_NOT_EMPTY);

    static final Parser PARSER_DEADLINE =
            Parser.createParser("deadline return book /by Sunday", TASKLIST_NOT_EMPTY);
    static final Parser PARSER_DONE = Parser.createParser("done 1", TASKLIST_NOT_EMPTY);
    static final Parser PARSER_EVENT = Parser.createParser("event team meeting /at 2:00pm", TASKLIST_NOT_EMPTY);
    static final Parser PARSER_DELETE = Parser.createParser("delete 1", TASKLIST_NOT_EMPTY);
    static final Parser PARSER_ERROR = Parser.createParser("unknown", TASKLIST_EMPTY);
    static final Parser PARSER_LIST = Parser.createParser("list", TASKLIST_EMPTY);
    static final Parser PARSER_TAG = Parser.createParser("tag 1 nice", TASKLIST_NOT_EMPTY);
    static final Parser PARSER_TODO = Parser.createParser("todo submit ip jar", TASKLIST_EMPTY);
    static final Parser PARSER_WRONG_DEADLINE =
            Parser.createParser("deadline return book /at Sunday", TASKLIST_EMPTY);
    static final Parser PARSER_WRONG_EVENT =
            Parser.createParser("event return book /by Sunday", TASKLIST_EMPTY);

    static final Optional<? extends ListItem> PARSER_DEADLINE_TASK =
            Optional.of(new Deadline("return book", "Sunday"));
    static final Optional<? extends ListItem> PARSER_DELETE_TASK =
            Optional.of(new Todo("test tag command"));
    static final Optional<? extends ListItem> PARSER_DONE_TASK =
            Optional.of(new Deadline("return book", "Sunday", true));
    static final Optional<? extends ListItem> PARSER_ERROR_TASK =
            Optional.empty();
    static final Optional<? extends ListItem> PARSER_LIST_TASK =
            Optional.empty();
    static final Optional<? extends ListItem> PARSER_TODO_TASK =
            Optional.of(new Todo("submit ip jar"));
}
