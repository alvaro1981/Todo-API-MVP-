package com.example.todoapimvp.model.repository.remote

import com.example.todoapimvp.model.Todo

class RemoteDataSourceImpl(private val api: TodoApi): RemoteDataSource {
    override suspend fun getRemoteData(): List<Todo> {
        val response = api.getTodos()
        return response.body()!!
    }
}