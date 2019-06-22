package com.revolshen.proweek.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Task::class], version = 4)
abstract class TaskDatabase: RoomDatabase(){

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
                        //.addCallback(roomCallback)
                        .build()
                }
            }
            return instanceOfTaskDatabase
        }

        fun destroyInstace(){
            instanceOfTaskDatabase = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instanceOfTaskDatabase).execute()
            }
        }

    }

}
class PopulateDbAsyncTask(db: TaskDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val noteDao = db?.taskDao()

    override fun doInBackground(vararg p0: Unit?) {
        noteDao?.insert (Task("Test1", "description 1", 1, 2, 1))
        noteDao?.insert (Task("Test2", "description 2", 1, 2, 1))
        noteDao?.insert (Task("Test3", "description 3", 1, 2, 2))
    }
}
