package com.extnds.props.tasks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.extnds.props.tasks.data.models.Task

@Database(entities = [Task::class], version = 3, exportSchema = false)
@TypeConverters(Converter::class)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    companion object {
        @Volatile
        private var INSTANCE: TasksDatabase? = null

        fun getDatabase(context: Context): TasksDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TasksDatabase::class.java, "tasks_database"
            )
                .fallbackToDestructiveMigration()
                .build()
    }

}