package storage;

import exceptions.*;

import tasks.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void initialize() {
        try {
            File file = new File(filePath);
            File directory = new File(file.getParent());
            directory.mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error happened while trying to create save file");
            System.exit(1);
        }
    }

    public List<Task> load() {
        List<Task> taskList = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String taskString = reader.nextLine();
                if (taskString.length() < 9) {
                    throw new InvalidSaveFileFormatException();
                }

                Task task;
                char taskType = taskString.charAt(0);
                char taskCompletion = taskString.charAt(4);
                String taskDescription;
                String taskDateTime;

                if (taskCompletion != '1' && taskCompletion != '0') {
                    throw new InvalidSaveFileFormatException();
                }

                if (taskType == 'T') {
                    taskDescription = taskString.substring(8);
                    task = new TodoTask(taskDescription);
                } else if (taskType == 'D') {
                    int dateTimeIndex = taskString.substring(8).indexOf('|');
                    if (dateTimeIndex == -1) {
                        throw new InvalidSaveFileFormatException();
                    }

                    taskDescription = taskString.substring(8, dateTimeIndex + 8).trim();
                    taskDateTime = taskString.substring(dateTimeIndex + 9).trim();
                    if (taskDateTime.isEmpty()) {
                        throw new InvalidSaveFileFormatException();
                    }

                    int timeIndex = taskDateTime.indexOf('|');
                    if (timeIndex == -1) {
                        LocalDate taskDate = LocalDate.parse(taskDateTime);
                        task = new DeadlineTask(taskDescription, taskDate);
                    } else {
                        String taskDateString = taskDateTime.substring(0, timeIndex).trim();
                        LocalDate taskDate = LocalDate.parse(taskDateString);
                        String taskTimeString = taskDateTime.substring(timeIndex + 1).trim();
                        LocalTime taskTime = LocalTime.
                                parse(taskTimeString, DateTimeFormatter.ofPattern("HHmm"));
                        task = new DeadlineTask(taskDescription, taskDate, taskTime);
                    }
                } else if (taskType == 'E') {
                    int dateIndex = taskString.substring(8).indexOf('|');
                    if (dateIndex == -1) {
                        throw new InvalidSaveFileFormatException();
                    }

                    taskDescription = taskString.substring(8, dateIndex + 8).trim();
                    taskDateTime = taskString.substring(dateIndex + 9).trim();
                    if (taskDateTime.isEmpty()) {
                        throw new InvalidSaveFileFormatException();
                    }

                    int timeIndex = taskDateTime.indexOf('|');
                    if (timeIndex == -1) {
                        LocalDate taskDate = LocalDate.parse(taskDateTime);
                        task = new EventTask(taskDescription, taskDate);
                    } else {
                        String taskDateString = taskDateTime.substring(0, timeIndex).trim();
                        LocalDate taskDate = LocalDate.parse(taskDateString);
                        String taskTimeString = taskDateTime.substring(timeIndex + 1).trim();

                        int endTimeIndex = taskTimeString.indexOf('|');
                        if (endTimeIndex == -1) {
                            LocalTime taskStartTime = LocalTime.
                                    parse(taskTimeString, DateTimeFormatter.ofPattern("HHmm"));
                            task = new EventTask(taskDescription, taskDate, taskStartTime);
                        } else {
                            String taskStartTimeString = taskTimeString.substring(0, endTimeIndex).trim();
                            LocalTime taskStartTime = LocalTime.
                                    parse(taskStartTimeString, DateTimeFormatter.ofPattern("HHmm"));
                            String taskEndTimeString = taskTimeString.substring(endTimeIndex + 1).trim();
                            LocalTime taskEndTime = LocalTime.
                                    parse(taskEndTimeString, DateTimeFormatter.ofPattern("HHmm"));
                            task = new EventTask(taskDescription, taskDate, taskStartTime, taskEndTime);
                        }
                    }
                } else {
                    throw new InvalidSaveFileFormatException();
                }

                if (taskCompletion == '1') {
                    task.markAsDone();
                }

                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File does not exists.");
            System.exit(1);
        } catch (InvalidSaveFileFormatException e) {
            System.out.println("Error: Invalid content format in save file");
            System.exit(1);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date time format in save file or no date time stated");
            System.exit(1);
        }

        return taskList;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        List<Task> taskList = tasks.getTasks();
        for (Task task : taskList) {
            String saveLine = task.getSaveString() + '\n';
            fileWriter.write(saveLine);
        }
        fileWriter.close();
    }

}
