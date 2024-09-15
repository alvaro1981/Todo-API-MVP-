package com.example.todoapimvp.model.repository.remote

import com.example.todoapimvp.model.Todo

interface RemoteDataSource {
    suspend fun getRemoteData(): List<Todo>
}