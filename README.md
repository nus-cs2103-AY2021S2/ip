#Dude User Guide
Dude is a simple and minimalistic todo manager that helps you organize your tasks.
##Features
list  
todo  
event  
deadline  
done  
delete  
find  
bye

##List
Display all tasks recorded
#####Usage
```$xslt
list
```
Dude will display all tasks you have in detail(including event and deadline's timestamp, each task's done type) in a list form
#####Tip
This is one of the most powerful and useful command of Dude, it allows you to overview your task manager

##Todo
Add a simple task that is of Todo type, which has only task description
#####Usage
```$xslt
todo <task description>
```
#####Example
```$xslt
todo grab milk from fairprice
```  
Dude will add todo task named ```grab milk from fairprice``` and mark it as not yet done by default

##Event
Add an task that is of Event type, which contains task description and time for event
#####Usage
```$xslt
event <task description> /<keyword> <time>
```
#####Example
```$xslt
event alice's birthday /on 2021-03-13
```
Dude will add event task named ```alice's birthday on 2021-03-13``` and mark it as not done yet by default

##Deadline
Add an task that is of Deadline type, which contains task description and deadline for the task
#####Usage
```$xslt
deadline <task description> /<keyword> <deadline>
```
#####Example
```$xslt
deadline submit cs2101 homework /by 2021-03-24
```
Dude will add deadline task named ```submit cs2101 homework by 2021-03-24``` and mark it as not done yet by default

##Done
Mark a task as done
#####Usage
```$xslt
done <task id>
```
#####Example
```$xslt
done 3
```
Dude will mark the third task in list as done.
#####Tip
Done should be used in combination with list, list the tasks to view id first, then mark the particular task as done

##Delete
Delete a series of tasks
#####Usage
```$xslt
delete <task ids separated by space, at least one id>
```
#####Example
```$xslt
delete 3 5 8
```
Dude will delete tasks which ids are 3, 5, 8 respectively from the list.
#####Tip
Delete should be used in combination with list as well

##Find
Search through the tasks to find tasks containing certain keyword
#####Usage
```$xslt
find <keyword>
```
#####Example
```$xslt
find alice
```
Dude will return all tasks containing the string 'alice' in a list form
#####Tip
Watch out for capital letters

##Bye
Exit Dude
#####Usage
```$xslt
bye
```
or
```$xslt
BYE
```
Dude will close with all its memory stored for the next time usage.