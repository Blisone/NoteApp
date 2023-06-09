package com.extnds.props.tasks.data.repository

import androidx.lifecycle.LiveData
import com.extnds.props.tasks.data.TasksDao
import com.extnds.props.tasks.data.models.Task

class TasksRepository(private val tasksDao: TasksDao) {
    val getAllData: LiveData<List<Task>> = tasksDao.getAllTasks()
    val sortByHighPriority: LiveData<List<Task>> = tasksDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<Task>> = tasksDao.sortByLowPriority()

    suspend fun insertData(toDoData: Task){
        tasksDao.insertTask(toDoData)
    }

    suspend fun updateData(toDoData: Task){
        tasksDao.updateTask(toDoData)
    }

    suspend fun deleteItem(toDoData: Task){
        tasksDao.deleteItemTask(toDoData)
    }

    suspend fun deleteAll(){
        tasksDao.deleteAllTasks()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Task>> {
        return tasksDao.searchTasksDatabase(searchQuery)
    }
}