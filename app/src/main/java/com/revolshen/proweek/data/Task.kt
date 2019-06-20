package com.revolshen.proweek.data


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class Task(
    var text: String,
    var description: String?,
    var startDate: Long?,
    var finishDate: Long?,
    var priority: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}