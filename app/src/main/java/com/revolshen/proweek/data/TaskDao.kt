package com.revolshen.proweek.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.revolshen.proweek.data.Task


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



}