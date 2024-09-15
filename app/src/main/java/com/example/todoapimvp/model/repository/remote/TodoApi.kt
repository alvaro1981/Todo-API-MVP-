package com.example.todoapimvp.model.repository.remote

import com.example.todoapimvp.model.Todo
import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {
    @GET("/todos")
    suspend fun getTodos():Response<List<Todo>>
}