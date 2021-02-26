package ui;

import common.Message;
import data.exception.DukeEmptyCommandException;
import data.task.Task;
import data.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class Ui {

    public static String greeting(){
        System.out.println(Message.GREETING.toString());
        return Message.GREETING.toString();
    }

    public static String bye(){
        System.out.println(Message.BYE.toString());
        return Message.BYE.toString();
    }

    public static String display(TaskList taskList){
        if (taskList.size() == 0) {
            System.out.print(Message.ERR_NO_TASK_IN_LIST.toString());
            return Message.ERR_NO_TASK_IN_LIST.toString();
        } else {
            StringBuilder sb = new StringBuilder(Message.LIST.toString());
            for (int i = 0; i < taskList.size(); i++) {
                sb.append(" ").append(i + 1).append(". ")
                        .append(taskList.get(i).toString()).append("\n");
            }
            System.out.print(sb.toString());
            return sb.toString();
        }
    }

    public static String display(TaskList taskList, int[] indexArr){
        if (indexArr.length == 0) {
            System.out.print(Message.ERR_NO_MATCHING_TASK.toString());
            return Message.ERR_NO_MATCHING_TASK.toString();
        } else {
            StringBuilder sb = new StringBuilder(
                    "Here are the task on you are looking for:\n");
            for (int index : indexArr) {
                sb.append(" ").append(index + 1).append(". ")
                        .append(taskList.get(index).toString()).append("\n");
            }
            System.out.print(sb.toString());
            return sb.toString();
        }
    }


}