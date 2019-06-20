package com.revolshen.proweek.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class TaskRepository(application: Application){

    private var taskDao: TaskDao

    private var allTasks: LiveData<List<Task>>

    init {
        val database: TaskDatabase = TaskDatabase.getInstance(
            application.applicationContext)!!
        taskDao = database.taskDao()
        allTasks = taskDao.getAllTask()
    }

    fun insert(task: Task){
        taskDao.insert(task)
    }

    fun update(task: Task){
        taskDao.update(task)
    }

    fun delete(task: Task){
        taskDao.delete(task)
    }

    fun getAllTasks(){
        taskDao.getAllTask()
    }

    companion object {
        private class InsertTaskAsync(val taskDao: TaskDao) : AsyncTask<Task, Unit, Unit>() {
            override fun doInBackground(vararg task: Task?) {
                taskDao.insert(task[0]!!)
            }
        }

        private class UpdateTaskAsync(val taskDao: TaskDao) : AsyncTask<Task, Unit, Unit>() {
            override fun doInBackground(vararg task: Task?) {
                taskDao.update(task[0]!!)
            }
        }

        private class DeleteTaskAsync(val taskDao: TaskDao) : AsyncTask<Task, Unit, Unit>() {
            override fun doInBackground(vararg task: Task?) {
                taskDao.delete(task[0]!!)
            }
        }

        
    }

}