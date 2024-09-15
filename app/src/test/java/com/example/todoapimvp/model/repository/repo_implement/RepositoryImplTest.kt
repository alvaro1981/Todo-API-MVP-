package com.example.todoapimvp.model.repository.repo_implement

import com.example.todoapimvp.model.Todo
import com.example.todoapimvp.model.repository.local.LocalDataSource
import com.example.todoapimvp.model.repository.remote.RemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RepositoryImplTest{
    @RelaxedMockK
    lateinit var localDataSource: LocalDataSource
    @RelaxedMockK
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var repositoryImpl: RepositoryImpl

    val myList = listOf(Todo(true,1,"testing 1",1),
        Todo(false,1,"testing 2",5))


    @Before
    fun OnBefore(){
        MockKAnnotations.init(this)
        repositoryImpl = RepositoryImpl(localDataSource,remoteDataSource)

    }

    @Test
    fun `When I can't connect to an API`()= runBlocking {
        //Given
        coEvery { remoteDataSource.getRemoteData() } throws Exception("Error conection")
        //When
        val response = repositoryImpl.getDataFromApi()
        //then
        assert(response == emptyList<Todo>())
    }

    @Test
    fun `When I can connect to an API`() = runBlocking {

        //Given
        coEvery { remoteDataSource.getRemoteData() } returns myList
        //When
        val response = repositoryImpl.getDataFromApi()
        //then
        assert(response == myList)
    }
    @Test
    fun `When I can't connect to DataBase`()= runBlocking{
        //Given
        coEvery { localDataSource.getLocalData() } throws Exception()
        //When
        val response = repositoryImpl.getDataFromDB()
        //Then
        assert(response == emptyList<Todo>())
    }
    @Test
    fun `When I can connect to DataBase`() = runBlocking {
        //Given
        coEvery{localDataSource.getLocalData()} returns myList
        //When
        val response = repositoryImpl.getDataFromDB()
        //Then
        assert(response == myList)
    }

    @Test
    fun `Insert Data Into Database Successful`() = runBlocking {
         //Given
        coEvery { localDataSource.insertLocalData(myList) } returns true
         //When
          val response = repositoryImpl.insertDataToDB(myList)
         //Then
        assert(response)

    }

    @Test
    fun `Insert Data Into Database Wrong`() = runBlocking {
        //Given
        coEvery { localDataSource.insertLocalData(myList) } returns false
        //When
        val response = repositoryImpl.insertDataToDB(myList)
        //Then
        assert(!response)
    }

}