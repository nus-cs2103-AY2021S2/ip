# User Guide
Duke is a program build to record tasks.<br>
Input your wishes and they will be saved.<br>
ENJOY! <br>

## Features 

### Adding tasks
Add a Todo, Event or Event

#### * Todo
Format : `todo/description`<br>
Input : `todo/buy book` <br>
Output : 
<br>`Got it.I 've added this task:`<br>
`[T][ ] buy book`<br>
`Now you have 1 tasks in the list.`

#### * Deadline
Format: `deadline/description/dd-mm-yyyy hh:mm`<br>
Input : `deadline/return book/06-08-2021 12:00`<br>
Output :
<br>`Got it.I 've added this task:`<br>
`[D][ ] return book(by:06 Aug 2021 12:00PM)`<br>
`Now you have 1 tasks in the list.`

#### * Event
Format : `event/description/dd-mm-yyyy hh:mm`<br>
Input : `event/project meeting/01-04-2021 23:59`<br>
Output : 
<br>`[E][] project meeting(at:01 Apr 2021 11.59PM)`<br>
`Now you have 1 tasks in the list.`

### List
Lists the current tasks in your list. <br>
Format : `list`<br>
Input : list<br>
Output : 
<br>`Here are the tasks in your list:`<br>
`1.[D][] buy book (by:01 Feb 2023 10.34PM)`<br>
`2.[D][] buy book (by:01 Feb 2023 10.34PM)`

### Done
Mark a Task as done by its index.<br />
Format : `done/index` <br>
Input : `done/3` <br>
Output : 
<br>`Nice! I 've marked this task as done`<br>
`3.[D][DONE] buy book (by:01 Feb 2023 10.34PM)`

### Delete
Delete a Task by its index <br>
Format : `delete/index`<br>
Input : `delete/5` <br>
Output : 
<br>`Noted.I'have removed this task:`<br>
`[T][] burn book`<br>
`Now you have 4 tasks in the list.`

### Exit
Duke says bye and thank you.<br>
Format : `bye`<br>
Input : `bye`<br>
Output : `Bye.Hope to see you again soon!`

### Find
Find specific tasks with a keyword. Duke will return matched tasks. <br>
Format: `find/keyword`<br>
Input : `find/book`<br>
Output : `Here are the tasks in your list:`<br>
`1.[D][] burn book (by:01 Feb 2023 10.34PM)`<br>
`2.[T][DONE] borrow book`