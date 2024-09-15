package com.example.todoapimvp.model.repository.local

import android.util.Log
import com.example.todoapimvp.model.Todo

const val TAG = "todoMVP"

class LocalDataSourceImpl(private val todoDatabase: TodoDatabase): LocalDataSource {
    override suspend fun getLocalData(): List<Todo> {
        return todoDatabase.todoDao().getAllTodos()
    }

    override suspend fun insertLocalData(todos: List<Todo>):Boolean {
        var allTodoInsert = true
        todos.forEach {
            try{
                 todoDatabase.todoDao().insert(it)
                 Log.i(TAG,"Se inserto en la base de datos $it ")
            }catch (e: Exception) {
                 Log.i(TAG, " Esta es la excepcion que mando $e al insertar el registro ${it.id} ")
                allTodoInsert = false
            }
        }
        return allTodoInsert
    }
}

