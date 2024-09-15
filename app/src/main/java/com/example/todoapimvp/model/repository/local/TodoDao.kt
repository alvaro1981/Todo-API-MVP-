package com.example.todoapimvp.model.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapimvp.model.Todo

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo:Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM todos")
    suspend fun getAllTodos():List<Todo>

}