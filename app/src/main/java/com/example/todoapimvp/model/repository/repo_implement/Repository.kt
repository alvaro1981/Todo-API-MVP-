package com.example.todoapimvp.model.repository.repo_implement

import com.example.todoapimvp.model.Todo

interface Repository {
    suspend fun getDataFromApi():List<Todo>
    suspend fun getDataFromDB():List<Todo>
    suspend fun insertDataToDB(todos:List<Todo>):Boolean
}