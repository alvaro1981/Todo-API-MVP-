package com.example.todoapimvp.model

import android.app.Activity
import com.example.todoapimvp.model.repository.repo_implement.Repository
import com.example.todoapimvp.presenter.MainPresenter
import com.example.todoapimvp.view.MainActivity
import com.example.todoapimvp.view.MainView
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MainInteractorTest{

    lateinit var mainPresenter: MainInteractor.OnFinishedListener

    @RelaxedMockK
     lateinit var repository: Repository

    lateinit var mainInteractor: MainInteractor

//    lateinit var mainActivity: MainView

    @Before
    fun onBefore(){
        //inicializa la libreria de mockk
         MockKAnnotations.init(this)
         mainInteractor = MainInteractor(repository)
         mainPresenter = MainPresenter(null, mainInteractor)

    }

    @Test
    fun `when The Api Doesnt Return Anything Get Values From Database`()= runBlocking{
        //Given
        val myList = emptyList<Todo>()
        coEvery { repository.getDataFromApi() } returns myList

        //when
        mainInteractor.requestGetDataAPI(mainPresenter)
        //then
        coVerify (exactly = 0) { repository.insertDataToDB(myList) }
        coVerify (exactly = 1) {  repository.getDataFromDB() }
    }

    @Test
    fun `when The Api Return Something Then Get Values From Database`(): Unit = runBlocking {
        val myList = listOf(Todo(true,1,"hello world 1",1),
                            Todo(false,1,"hello world 2",5))
        //Given
        coEvery { repository.getDataFromApi() } returns myList
        // when
        mainInteractor.requestGetDataAPI(mainPresenter)
        //then
        coVerify (exactly = 1) { repository.insertDataToDB(myList) }

    }
}