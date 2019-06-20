package com.revolshen.proweek.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase(): RoomDatabase(){

    abstract fun taskDao(): TaskDao

    companion object{
        private var instanceOfTaskDatabase: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase?{
            if(instanceOfTaskDatabase ==null){
                synchronized(TaskDatabase::class){
                    instanceOfTaskDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "task_table")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instanceOfTaskDatabase
        }

        fun destroyInstace(){
            instanceOfTaskDatabase = null
        }

    }
}

