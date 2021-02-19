# User Guide

## Features 
Monique the monitor lizard is a desktop java GUI client that makes managing tasks easier. You interact
with it through a command line interface, where you type commands
. It can manage three different types of tasks, todo tasks *(
which can serve as a sort of bullet reminder about outstanding things)*, deadline tasks *(tasks that need 
to be completed by a certain date)* and lastly event tasks *(events occurring on a certain date, like promos
or meetings).

### Feature 1 

## Usage

### `event` - Add Event task to the list

One event task must be associated with one date, that is the date this event occurs. The event must be entered into the CLI with the following format  
 `event (String description) /at (String containing date)`. The date must be in the following format  *"yyyy-mm-dd"* and it must be present in the
 string after the /at keyword, else the event cannot be parsed. The date can be anywhere so long as it comes after /at. If there are multiple different 
 dates following the format specified earlier, only the earliest date will be the one parsed and associated with this event. After the adding the event
 to the list. When the task is displayed by the GUI, all occurrences of the associated date will be converted to a more readable format *'MMM-dd-yyyy'* .  After entering the command,
 an event task will be added to the list, and the chatbot will output a message to notify you if the command was successful. 
 
  Example of usage: 1.`event hang out with friends /at Cathay Cineplex 2021-03-04`

Expected outcome:
`Got it! I have added this task: \n\t [E][X] hang out with friends (at: Cathay Cineplex Mar 4 2021) \n Now you have __ number of tasks in the
list.`


### Feature 2 



## Usage

### `Deadline` - Add Deadline task to list

A deadline task must be associated with one date, that is the date by which the task must be completed. The command must be entered into the
CLI with the following format `deadline (String description) /by (String containing date`). Similar to adding an event task from earlier, 
the date must be in the format "yyyy-mm", and it must be present after that /by keyword. It can be at any position in the string so long as 
it comes after /by. For multiple date clashes, **see 'event' feature ** for how it will be resolved, as it is similar. After entering the command,
a deadline task will be added to the list, and the chatbot will output a message to notify you if the command was succesful.

Example of usage: 

`deadline I need to do my homework /by 2021-04-05 6pm`

Expected outcome:

`Got it! I have added this task: 
\n\t [D][X] I need to do my homework (by: Apr 5 2021 6pm) \n Now you have __ number of tasks in the
 list.`

### Feature 3 

## Usage

### `todo` - Add ToDo task to list

A todo task is not specified with any date. The command must be entered into the CLI with the following format `todo (String description)`.
After which a todo task will be added into the list.After entering the command,
a deadline task will be added to the list, and the chatbot will output a message to notify you if the command was successful.                                                    

Example of usage: 

`todo read a good book`

Expected outcome:

`Got it! I have added this task: \n\t [T][X] read a good book \n Now you have __ number of tasks in the list.`

### Feature 4

## Usage

### `find` - find task with matching substring

The format for the command is `find (String regex)`. It will then search through the list to filter off the tasks whose string 
representation contains a substring that matches the regex. It will then display the list of matching tasks to the user.
Note : The string representation of a task is the string which is displayed by the GUI to represent the task. If unsure,
you can call a list Command to see the format. So for example in the earlier todo task example, the string representation is 
`[T][X] read a good book`. Note that you can also search out all deadline tasks using the unique task identifier, for example 
`find [D]`. Additionally if you want to search by date, you have to search the 'MMM-dd-yyyy' format string, so for example 
`find Mar 3 2021`;

Example of usage: 

`find CS3230`

Expected outcome:


lists out all tasks whose display string contains the string 'CS3230' as a substring, for example this task gets filtered  
`[E][X] CS3230 midterm (:at Mar 2 2021)`. If no tasks match, GUI will display message `You have nothing on currently`

### Feature 5

## Usage

### `Delete` - Delete task at index

Command must be of the format `delete (index)`. The program will then delete the task in the list at that index. GUI displays a message
to notify you if the command was successful.

Example of usage: 

`delete 3`

Expected outcome:

the third numbered task should be deleted from the list and GUI displays the task that is deleted, within a confirmation message.

### Feature 6

## Usage

### `Done` - Mark Task as done.

Command must be of the format `done (index)`. Command will then find the task in the list at the index and mark it as done. GUI displays a
to notify you if the command was successful. 
If an already marked done task wis located at the index, it will still proceed to "mark" the task, even if in the end the 
task's state will not change.
 

Example of usage: 

`done 3`

Expected outcome:

The task at 3 will be marked as done. The next time the task is displayed, in the string 
 representation, the task will be ticked instead of marked with a cross. For example the 
 following  to do task is marked as done 
 
 `[T][✓] Get out of the house`
  

### Feature 7

## Usage

### `list` - Display all the tasks

Command should just be of the format `list`. The GUI will then display out all the tasks in a numbered list.

Example of usage: 

`list`

Expected outcome:

GUI displays list of tasks, numbered.

### Feature 8

## Usage

### Automatic sorting by date

The program sorts tasks by its date. Events or deadlines that are happening sooner
are higher up the list. Since ToDo tasks do not have any date associated with them, they will be pushed back to the end
of the list. If the tasks have the same date then the more recently addded tasks will come later in the list.

Example of usage: 

The program automatically maintains the sorted propoerty whenever you add an event to the list.
No input needed.

Expected outcome:

List of tasks displayed by the GUI will be sorted by most recent date if they are date-based tasks like Deadline or Event.
ToDo Tasks will be pushed to the back. 

### Feature 9

## Usage

### `bye` - Exit the program


Example of usage: 

`bye`

Expected outcome:

GUI window closes and program exits.

##Acknowledgements

Special thanks to the two authors for the images. Do check out their work!.

@author Frieda Bredesen
Image DaUser.png
https://unsplash.com/photos/IxlY2KB4Krs?utm_source=unsplash&utm_medium=referral&utm_content=creditShareLink

@author Mark Stoop
 Image DaUser.png
 
 https://unsplash.com/photos/IxlY2KB4Krs?utm_source=unsplash&utm_medium=referral&utm_content=creditShareLink
 
 