package com.revolshen.proweek.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.revolshen.proweek.data.Task
import com.revolshen.proweek.data.TaskRepository

class TaskViewModel(application: Application) : AndroidViewModel(application){

    private val taskRepository = TaskRepository(application)
    private var allTasks: LiveData<List<Task>> = taskRepository.getAllTasks()

    fun insert(task: Task){
        taskRepository.insert(task)
    }

    fun update(task: Task){
        taskRepository.update(task)
    }

    fun delete(task: Task){
        taskRepository.delete(task)
    }

    fun getAllTasks(): LiveData<List<Task>>{
        return allTasks
    }

    fun deleteAllTask(){
        taskRepository.deleteAllTask()
    }

}