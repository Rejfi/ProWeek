package com.revolshen.proweek.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import java.lang.Exception

class TaskRepository(application: Application) {

    private var taskDao: TaskDao

    private var allTasks: LiveData<List<Task>>

    init {
        val database: TaskDatabase = TaskDatabase.getInstance(
            application.applicationContext
        )!!
        taskDao = database.taskDao()
        allTasks = taskDao.getAllTask()
    }

    fun insert(task: Task) {
        InsertTaskAsync(taskDao).execute(task)

    }

    fun update(task: Task) {
        UpdateTaskAsync(taskDao).execute(task)
    }

    fun delete(task: Task) {
        DeleteTaskAsync(taskDao).execute(task)
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return allTasks
    }

    fun deleteAllTask() {
        DeleteAllTaskAsync(taskDao).execute()
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

        private class DeleteAllTaskAsync(val taskDao: TaskDao) : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                taskDao.deleteAllTask()
            }

        }

    }

}