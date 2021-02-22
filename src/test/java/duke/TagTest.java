package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TagTest {

    @ParameterizedTest
    @ValueSource(strings = {"tagr delete", "tag  add", "tag  a", "tag           delete", "tag        deleterrrrrrr",
            "tag add  ", "tag delete   "})
    public void addTag_TwoWordsInInput_ThrowsDukeEx(String input) {
        try {
            Duke duke = new Duke();
            String result = duke.run(input);
            switch (input) {
            case "tagr delete":
                assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-()", result);
                break;
            default:
                assertEquals("OOPS! Either the Task Description was not wrapped in inverted commas, or"
                        + " tag is missing some arguments!", result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"tag add", "tag delete"})
    public void addTag_onlyTwoWordsButCorrect_ThrowsDukeEx(String input) {
        try {
            Duke duke = new Duke();
            String result = duke.run(input);
            assertEquals("OOPS! Either the Task Description was not wrapped in inverted commas, or"
                    + " tag is missing some arguments!", result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"tag add test1", "tag add  test1", "tag add test1    ", "tag  add test1", "tag  add  "
            + "test1"})
    public void addTag_onlyThreeWordsInInput_ThrowsDukeEx(String input) {
        try {
            Duke duke = new Duke();
            String result = duke.run(input);
            assertEquals("OOPS! Either the Task Description was not wrapped in inverted commas, or"
                    + " tag is missing some arguments!", result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"tag add \"CS2103T iP\" tag1", "tag delete \"my beloved event\" tag2"})
    public void addTag_taskDescMoreThanOneWordButTaskDoesntExist_ThrowsDukeEx(String input) {
        try {
            Duke duke = new Duke();
            String result = duke.run(input);
            assertEquals("OOPS!!! Cannot find the relevant task!", result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"tag add CS2103T iP wk5 tag1", "tag add CS2103T_iP_wk5 tag1"})
    public void addTag_existingTaskButTaskDescNotWrappedInInvCommasInInput_ThrowsDukeEx(String inputToAddTag) {
        try {
            Duke duke = new Duke();

            if (inputToAddTag.equals("tag add CS2103T iP wk5 tag1")) {
                duke.run("deadline CS2103T iP wk5 /by 2020-04-13");
                String inputToDeleteTask = "delete " + duke.getNumOfTasks();
                String result = duke.run(inputToAddTag);
                duke.run(inputToDeleteTask);
                assertEquals("OOPS! Either the Task Description was not wrapped in inverted commas, or"
                        + " tag is missing some arguments!", result);
            } else {
                duke.run("deadline CS2103T_iP_wk5 /by 2020-04-13");
                String inputToDeleteTask = "delete " + duke.getNumOfTasks();
                String result = duke.run(inputToAddTag);
                duke.run(inputToDeleteTask);
                assertEquals("OOPS! Either the Task Description was not wrapped in inverted commas, or"
                        + " tag is missing some arguments!", result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void addTag_existingTask_pass() {
        try {
            String inputToAddTag = "tag add \"CS2103T iP wk5\" tag1";
            Duke duke = new Duke();
            duke.run("deadline CS2103T iP wk5 /by 2020-04-13");
            String inputToDeleteTask = "delete " + duke.getNumOfTasks();
            String result = duke.run(inputToAddTag);
            duke.run(inputToDeleteTask);
            assertEquals("The tag #tag1 was added to the Task \"CS2103T iP wk5\"", result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void deleteTag_existingTask_pass() {
        try {
            String inputToAddTag = "tag add \"CS2103T iP wk5\" tag1";
            String inputToDeleteTag = "tag delete \"CS2103T iP wk5\" tag1";
            Duke duke = new Duke();
            duke.run("deadline CS2103T iP wk5 /by 2020-04-13");
            duke.run(inputToAddTag);
            String inputToDeleteTask = "delete " + duke.getNumOfTasks();
            String result = duke.run(inputToDeleteTag);
            assertEquals("The tag #tag1 was deleted from the Task \"CS2103T iP wk5\"", result);
            duke.run(inputToDeleteTask);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
