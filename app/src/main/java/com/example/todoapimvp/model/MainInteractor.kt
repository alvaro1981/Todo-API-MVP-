package com.example.todoapimvp.model

import android.util.Log
import com.example.todoapimvp.model.repository.repo_implement.Repository

class MainInteractor(val repository: Repository) {

    interface OnFinishedListener {
        fun onResultSuccess(arrUpdate:List<Todo>)
        fun onResultFail(strError:String)
    }

    suspend fun requestGetDataAPI(onFinishedListener: OnFinishedListener) {

        var todos = repository.getDataFromApi()

        if (todos.isNotEmpty()) {
            repository.insertDataToDB(todos)
            onFinishedListener.onResultSuccess(todos)
        } else {
            todos = repository.getDataFromDB()
            if (todos.isNotEmpty()) {
                onFinishedListener.onResultSuccess(todos)
            } else {
                val errorRepository = "No se pudo obtener datos desde la API ni desde la DB "
                onFinishedListener.onResultFail(errorRepository)
            }
        }
    }

}