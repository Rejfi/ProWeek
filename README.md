# ProWeek

Application to manage your tasks and increase your productivity. 
One task includes: 
1. Title 
2. (Optional) Description 
3. Priority (Higher priority means that task will be on the top of the list) 

I have used the **MVVM pattern** and the recommend **Android ROOM**

**Application Architecture**:
1. MainActivity --> host for the ViewPager 

2a. MyTaskFragment --> Fragment displays the list of the tasks

2b. EditTaskFragment --> Fragment displays details of a given task or let to create a new task.

3. TaskViewModel --> ViewModel for Fragments to manage the data (preparing and editing) include the LiveData objects. 

4. TaskRepository --> an object which has to receive data from SQLite DataBase or (in the future) WebServices. 

**Data Flow**:

Fragments <--> TaskViewModel <---> TaskRepository <-->SQLiteDatabase
