package com.example.todoapimvp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoapimvp.R
@Entity(tableName = "todos")
data class Todo(
    val completed:Boolean,
    @PrimaryKey
    val id:Int,
    val title:String,
    val userId:Int
)

