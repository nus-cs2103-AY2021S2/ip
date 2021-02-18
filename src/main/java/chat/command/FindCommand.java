package chat.command;

import chat.ChatException;
import chat.TaskList;
import chat.Storage;
import chat.Ui;
import chat.task.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A type of command that finds tasks based on keywords given by user
 */
public class FindCommand extends Command {

    /**
     * Enumerator class to classify the types of keywords that the user can enter for the find command.
     */
    enum KeywordKey { 
        TASK_TYPE, NAME, IS_COMPLETED, DATES;
    }
    
    String inputStr;

    /**
     * Initialises FindCommand object with command that includes find keywords given by user. 
     * 
     * 
     * @param inputStr Inputted command string from user to Chat the Cat.
     */
    public FindCommand(String inputStr) {
        assert inputStr != "";
        this.inputStr = inputStr;
    }

    /**
     * Method that finds tasks from list of tasks from TaskList object.
     * 
     * @param taskList TaskList object that contains a list of tasks.
     * @param ui Ui object that gives responses to user.
     * @param storage Storage object that interacts with task data in hard disk.
     * @throws ChatException If no tasks match keyword given by user.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatException {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        
        TaskList foundTaskList = getFoundTaskList(taskList);
        if (foundTaskList.getTasks().isEmpty()) {
            throw new ChatException("Sorry! No results found.");
        } 
        ui.list(foundTaskList);
    }
    
    /**
     * Returns a TaskList object containing list of tasks that match the keywords given by user.
     * 
     * @param taskList TaskList object containing all tasks.
     * @return TaskList object containing list of tasks that fit the keywords.
     * @throws ChatException
     */
    public TaskList getFoundTaskList(TaskList taskList) throws ChatException {
        assert taskList != null;

        String formatStr = "Please utilise this format:\n" +
                "find t/[TASK_TYPE] n/[NAME] c/[IS_COMPLETED] d/[DATES]\n\n" +
                "TASK_TYPE: T, D or E\n" +
                "IS_COMPLETED: T or F\n" +
                "DATES: dates can be written as dd MMM or MMM YYYY\n\n" +
                "Examples:\n" +
                "* find t/T n/book c/T --> lists completed todo tasks of which name includes book\n" +
                "* find t/D c/F --> lists uncompleted deadline and event tasks\n" +
                "* find d/13 Mar, 14 Mar 2021 --> lists tasks that start on 13 Mar and end on 14 Mar 2021";

        try {
            HashMap<KeywordKey, String> keywordMap = getKeywordMap();
            TaskList foundTaskList = new TaskList(new ArrayList<Task>());
            for (Task task : taskList.getTasks()) {
                if (isFound(task, keywordMap)) {
                    foundTaskList.getTasks().add(task);
                }
            }
            return foundTaskList;
        } catch (ChatException e) {
            throw new ChatException(e.getMessage() + formatStr);
        }
    }

    /**
     * Returns a hashmap, where keywords have been categorised by their given key. (i.e. task type: t/, name: n/, etc.)
     * The string behind the keys have been taken as their respective values in the hashmap.
     * 
     * @return HashMap 
     * @throws ChatException 
     */
    private HashMap<KeywordKey, String> getKeywordMap() throws ChatException {
        try {
            HashMap<KeywordKey, String> keywordMap = new HashMap<>();
            String keywordStr = this.inputStr.replace("find", "").strip();
            
            if (keywordStr.equals("")) {
                throw new ChatException("There should be at least one keyword.\n");
            }
            
            String[] strings = keywordStr.split("/");
            String key = strings[0];
            for (int i = 1; i < strings.length - 1; i++) {
                keywordMap.put(getKeywordKey(key), strings[i].substring(0, strings[i].length() - 1).strip());
                key = Character.toString(strings[i].charAt(strings[i].length() - 1));
            }
            keywordMap.put(getKeywordKey(key), strings[strings.length - 1]);
            return keywordMap;
        } catch (ChatException e) {
            throw e;
        }
    }

    /**
     * Converts string to KeywordKey constant. 
     * 
     * @param s String to be converted to KeywordKey constant.
     * @return KeywordKey constant.
     * @throws ChatException ChatException is thrown, if string does not represent any of the possible keywords.
     */
    public KeywordKey getKeywordKey(String s) throws ChatException {
        KeywordKey keywordKey;
        switch (s) {
        case "t":
            keywordKey = KeywordKey.TASK_TYPE;
            break;
        case "n":
            keywordKey = KeywordKey.NAME;
            break;
        case "c":
            keywordKey = KeywordKey.IS_COMPLETED;
            break;
        case "d":
            keywordKey = KeywordKey.DATES;
            break;
        default:
            throw new ChatException("The keyword term " + s + "/ does not exist.\n");
        }
        return keywordKey;
    }

    /**
     * A method that checks if matching task to keywords is found.
     * 
     * @param task Task being checked.
     * @param keywordMap HashMap containing keywords with KeywordKey keys.
     * @return Boolean indicating if matching task is found.
     */
    private boolean isFound(Task task, HashMap<KeywordKey, String> keywordMap) {
        
        for (KeywordKey key : keywordMap.keySet()) {
            switch (key) {
            case TASK_TYPE:
                if (!isMatchingTaskType(task, keywordMap.get(key))) {
                    return false;
                }
                break;
            case NAME:
                if (!isMatchingName(task, keywordMap.get(key))) {
                    return false;
                }
                break;
            case IS_COMPLETED:
                if (!isMatchingDoneStatus(task, keywordMap.get(key))) {
                    return false;
                }
                break;
            case DATES:
                if (!isMatchingDates(task, keywordMap.get(key))) {
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    private static boolean isMatchingTaskType(Task task, String taskType) {
        if (!taskType.isEmpty()) {
            if (!Character.toString(task.allParameterStr().charAt(0)).equals(taskType)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isMatchingName(Task task, String name) {
        if (!task.getName().contains(name)) {
            return false;
        }
        return true;
    }
    
    private static boolean isMatchingDoneStatus(Task task, String doneStatusStr) {
        if (doneStatusStr != "") {
            if (doneStatusStr.equals("T") && !task.getIsDone()) {
                return false;
            } else if (doneStatusStr.equals("F") && task.getIsDone()) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isMatchingDates(Task task, String dateStr) {
        String[] dates = dateStr.split(" ");
        for (String date : dates) {
            if (!task.toString().contains(date)) {
                return false;
            }
        }
        return true;
    }
    
}
