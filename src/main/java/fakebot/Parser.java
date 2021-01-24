package fakebot;

import fakebot.task.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Parser {


    private static final String SPLIT_REGEX = "-'@,-@,1'-";
    private enum TaskType { TODO, EVENT, DEADLINE }

    public static String convertStringsToString(List<String> stringList) {
        StringBuilder builder = new StringBuilder();
        for (String s : stringList) {
            builder.append(s);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    public static String convertTaskTOString(Task currentTask) {
        StringBuilder currentString = new StringBuilder();
        if (currentTask instanceof ToDos) {
            currentString.append(TaskType.TODO.name());
            currentString.append(SPLIT_REGEX);
            currentString.append(currentTask.isComplete());
            currentString.append(SPLIT_REGEX);
            currentString.append(currentTask.getTaskName());
        }else if (currentTask instanceof Events) {
            Events events = (Events) currentTask;
            currentString.append(TaskType.EVENT.name());
            currentString.append(SPLIT_REGEX);
            currentString.append(currentTask.isComplete());
            currentString.append(SPLIT_REGEX);
            currentString.append(events.getTaskName());
            currentString.append(SPLIT_REGEX);
            currentString.append(events.getStartDate());
            currentString.append(SPLIT_REGEX);
            currentString.append(events.getStartTime());
            currentString.append(SPLIT_REGEX);
            currentString.append(events.getEndDate());
            currentString.append(SPLIT_REGEX);
            currentString.append(events.getEndTime());
        }else if (currentTask instanceof Deadlines) {
            Deadlines deadlines = (Deadlines) currentTask;
            currentString.append(TaskType.DEADLINE.name());
            currentString.append(SPLIT_REGEX);
            currentString.append(currentTask.isComplete());
            currentString.append(SPLIT_REGEX);
            currentString.append(deadlines.getTaskName());
            currentString.append(SPLIT_REGEX);
            currentString.append(deadlines.getDeadlineDate());
            currentString.append(SPLIT_REGEX);
            currentString.append(deadlines.getDeadlineTime());
        }
        return currentString.toString();
    }
    public static List<String> convertTasksToStrings(TaskList taskList) {
        List<String> strings = new ArrayList<String>();

        for(int i = 0; i < taskList.getSize() ; i++) {
            Task currentTask = taskList.getTask(i);
            strings.add(convertTaskTOString(currentTask));
        }
        return strings;
    }

    public static Task parseStringToTask(String string) {
        String[] parts = string.split(SPLIT_REGEX);
        Task currentTask = null;
        switch (TaskType.valueOf(parts[0])) {
        case TODO:
            currentTask = new ToDos(parts[2]);
            break;
        case EVENT:
            currentTask = new Events(parts[2], LocalDate.parse(parts[3]) , LocalTime.parse(parts[4])
                    , LocalDate.parse(parts[5]) , LocalTime.parse(parts[6]));
            break;
        case DEADLINE:
            currentTask = new Deadlines(parts[2], LocalDate.parse(parts[3]) , LocalTime.parse(parts[4]) );
            break;
        }

        if(Boolean.parseBoolean(parts[1]))
            currentTask.markComplete();

        return currentTask;
    }
    public static List<Task> parseStringsToTasks(List<String> stringList) {
        List<Task> tasks = new ArrayList<Task>();

        for (String line : stringList) {
            tasks.add(parseStringToTask(line));
        }
        return tasks;
    }
}
