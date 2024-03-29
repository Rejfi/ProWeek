package com.revolshen.proweek.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TaskDao{

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM task_table ORDER BY priority DESC")
    fun getAllTask(): LiveData<List<Task>>

    @Query("DELETE FROM task_table")
    fun deleteAllTask()




}