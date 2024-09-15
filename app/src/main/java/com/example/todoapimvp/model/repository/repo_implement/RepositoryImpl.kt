package com.example.todoapimvp.model.repository.repo_implement

import android.util.Log
import com.example.todoapimvp.R
import com.example.todoapimvp.model.Todo
import com.example.todoapimvp.model.repository.local.LocalDataSource
import com.example.todoapimvp.model.repository.remote.RemoteDataSource
const val TAG = "todoMVP"
class RepositoryImpl(private val localDataSource: LocalDataSource,
                     private val remoteDataSource: RemoteDataSource
):Repository {

    override suspend fun getDataFromApi(): List<Todo> {
        val dataList: List<Todo> = try{
            remoteDataSource.getRemoteData()
        }catch (e:Exception){
//            Log.i(TAG,"Error al ejecutar en RepositoryImpl remoteDataSource.getRemoteData() : $e ")
            emptyList()
        }
        return dataList
    }

    override suspend fun getDataFromDB(): List<Todo> {
        return try {
            localDataSource.getLocalData()
        }catch (e:Exception){
//            Log.i(TAG,"Error al querer recuperar los  datos de la DataBase con getLocalData" )
            emptyList<Todo>()
        }
    }

    override suspend fun insertDataToDB(todos:List<Todo>):Boolean {
        return localDataSource.insertLocalData(todos)
    }

    companion object{
        val svgArray = arrayOf(
            R.drawable.agriculture,
            R.drawable.beach_access,
            R.drawable.celebration,
            R.drawable.coffee,
            R.drawable.cloudy_snowing,
            R.drawable.coffee_maker,
            R.drawable.cruelty_free,
            R.drawable.egg,
            R.drawable.emoji_nature,
            R.drawable.laptop_mac,
        )
    }
}