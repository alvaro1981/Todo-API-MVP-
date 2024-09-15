package com.example.todoapimvp.view

interface Comunicator {
    fun passDataCom(idUser:String, title:String)
    fun backFragmentTitle(title:String)
}