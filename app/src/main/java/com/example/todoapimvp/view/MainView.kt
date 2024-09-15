package com.example.todoapimvp.view

import com.example.todoapimvp.model.Todo

interface MainView {
    fun showProgress()
    fun hideProgress()
    fun setData(arrUpdates:List<Todo>)
    fun setDataError(strError:String)
}