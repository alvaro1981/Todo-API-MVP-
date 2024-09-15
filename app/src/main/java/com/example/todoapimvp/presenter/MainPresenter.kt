package com.example.todoapimvp.presenter

import com.example.todoapimvp.model.MainInteractor
import com.example.todoapimvp.model.Todo
import com.example.todoapimvp.view.MainView
import kotlinx.coroutines.*

class  MainPresenter(private var mainView: MainView?,
                     private val mainInteractor: MainInteractor)
    :MainInteractor.OnFinishedListener{

     fun getData(){
        mainView?.showProgress()
         CoroutineScope(Dispatchers.Main).launch {
             mainInteractor.requestGetDataAPI(this@MainPresenter)

         }
    }

    override fun onResultSuccess(arrUpdate: List<Todo>) {
        mainView?.hideProgress()
        mainView?.setData(arrUpdate)
    }

    override fun onResultFail(strError: String) {
        mainView?.hideProgress()
        mainView?.setDataError(strError)
    }

    // Destroy View when Activity destroyed
    fun onDestroy(){
        mainView = null
    }
}