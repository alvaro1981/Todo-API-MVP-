package com.example.todoapimvp.model.repository.local

import com.example.todoapimvp.model.Todo

interface LocalDataSource {
    suspend fun getLocalData(): List<Todo>
    suspend fun insertLocalData(todos:List<Todo>):Boolean
}