# User Guide

Welcome to Donald! Donald is a task list application which helps you to keep track of `todo`, `deadline` and `event`
items!

## Feature List

Commands | Usage
--------|-------
`help` | Lists all possible commands
`list` | Lists all tasks currently in the application
`todo <taskName>` | Adds a `todo` item to the task list
`deadline <taskName> /by dd/MM/yyyy HHmm` | Adds a `deadline` item to the task list
`event    <taskName> /at dd/MM/yyyy HHmm` | Adds an `event` item to the task list
`done <taskNumber>` | Marks the task at `taskNumber` as done
`done <startTaskNumber>-<endTaskNumber>` | Marks tasks from `startTaskNumber` to `endTaskNumber` as done
`delete <taskNumber>` | Deletes task at `taskNumber`
`delete <startTaskNumber>-<endTaskNumber>` | Deletes tasks from `startTaskNumber` to `endTaskNumber`
`find <keyword>` | Finds and lists tasks containing `keyword`
`bye` | Exits the application

### 1. Add a `todo`

Adds a `todo` to your task list.

Example:
`todo do homework`

Expected Outcome:

```
Cool! I've added the following item to your order list. 
    [T][ ] do homework
You now have 1 order(s)!
```

### 2. Add a `deadline`

Adds a `deadline` to your task list.

Example: `deadline assignemnt submission /by 14/02/2021 2359`

Expected Outcome:

```
Cool! I've added the following item to your order list. 
    [D][ ] assignment submission (by: 14 Feb 2021, 23:59)
You now have 2 order(s)!
```

### 3. Add an `event`

Adds an `event` to your task list.

Example: `event birthday celebration /at 01/01/2022 0000`

Expected Outcome:

```
Cool! I've added the following item to your order list. 
    [E][ ] birthday celebration (at: 1 Jan 2022, 00:00)
You now have 3 order(s)!
```

### 3. Mark tasks as `done`

Marks a task/multiple tasks in the list as `done`.

Example: `done 1`

Expected Outcome:

```
Your order(s) have been served!
[T][✓] do homework
```

Example: `done 1-3`

Expected Outcome:

```
Your order(s) have been served!
[T][✓] do homework
[D][✓] assignment submission (by: 14 Feb 2021, 23:59)
[E][✓] birthday celebration (at: 1 Jan 2022, 00:00)
```

### 4. `delete` tasks

Deletes a task/multiple tasks in the list.

Example: `delete 1`

Expected Outcome:

```
Here you go, I've removed these item(s) from your order list!
[T][✓] do homework
You have 2 order(s) left!
```

Example: `delete 1-2`

Expected Outcome:

```
Here you go, I've removed these item(s) from your order list!
[D][✓] assignment submission (by: 14 Feb 2021, 23:59)
[E][✓] birthday celebration (at: 1 Jan 2022, 00:00)
You have 0 order(s) left!
```

### 5. `find` tasks

Finds a task/multiple tasks matching a certain keyword and displays them.

Example: `find homework`

Expected Outcome:

```
Here are the orders that match!
1. [T][✓] do homework
```

### 6. `list` all tasks

Lists all existing tasks.

Example: `list`

Expected Outcome (with multiple existing items):

```
Here's what you've ordered so far:
1. [T][✓] do homework
2. [D][✓] assignment submission (by: 14 Feb 2021, 23:59)
3. [E][✓] birthday celebration (at: 1 Jan 2022, 00:00)
```

Expected Outcome (with no items):

```
You don't have anything on your menu at the moment!
```

### 7. `bye`

Exits the application.

Example: `bye`

Expected Outcome:

```
Thanks for coming, we hope to see you again!
We will be closing shortly... 
```

## Credits

__________

* The GUI of Donald was created with the help
  of [this JavaFX tutorial](https://se-education.org/guides/tutorials/javaFx.html)
* The McDonald's logo was retrieved from [this link](https://www.logo.wine/logo/McDonald%27s)
* Ronald's Picture was made with photoshop combining [this McSpicy](https://www.mcdonalds.com.sg/food-menu/mcspicy/)
  and [this McDonald's Hat](https://www.wish.com/product/5e7822415bbf733480e89dc2?hide_login_modal=true&from_ad=goog_shopping&_display_country_code=SG&_force_currency_code=SGD&pid=googleadwords_int&c=%7BcampaignId%7D&ad_cid=5e7822415bbf733480e89dc2&ad_cc=SG&ad_lang=EN&ad_curr=SGD&ad_price=15.00&campaign_id=10118096826&exclude_install=true&gclid=CjwKCAiA65iBBhB-EiwAW253W60hmFDo4QDwdKuccEkyYV4xO6F4SLroaZYYezpZroAAbHWUbVy8uRoCH_kQAvD_BwE&share=web)