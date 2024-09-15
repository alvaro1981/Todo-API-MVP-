package com.example.todoapimvp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapimvp.databinding.ActivityMainBinding
import com.example.todoapimvp.model.MainInteractor
import com.example.todoapimvp.model.Todo
import com.example.todoapimvp.model.repository.local.LocalDataSourceImpl
import com.example.todoapimvp.model.repository.local.TodoDatabase
import com.example.todoapimvp.model.repository.remote.RemoteDataSourceImpl
import com.example.todoapimvp.model.repository.remote.RetrofitInstance
import com.example.todoapimvp.model.repository.repo_implement.RepositoryImpl
import com.example.todoapimvp.presenter.MainPresenter
private const val ARG_PARAM1 = "svg_number"
private const val ARG_PARAM2 = "title_string"

class MainActivity : AppCompatActivity(), MainView, Comunicator{
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainPresenter: MainPresenter
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var todoFragment: TodoFragment
    private lateinit var svgFragment: SvgFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoFragment = TodoFragment()
        svgFragment  = SvgFragment()
        val database:TodoDatabase = TodoDatabase.getDatabase(this)
        val remoteDataSource = RemoteDataSourceImpl(RetrofitInstance.api)
        val localDataSource  = LocalDataSourceImpl(database)
        val repository = RepositoryImpl(localDataSource,remoteDataSource)
        setRecyclerView()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.FragContainerViewTodo.id, todoFragment).commit()
        mainPresenter = MainPresenter(this, MainInteractor(repository))
        mainPresenter.getData()
    }

    private fun setRecyclerView()=binding.recyclerView.apply {
       todoAdapter = TodoAdapter { recyclerItem ->
           todoFragment.setData(recyclerItem)
       }
       adapter = todoAdapter
       layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun returnRandomTodo(arrUpdates :List<Todo>):Todo{
       return arrUpdates.random()
    }

    override fun showProgress() {
        binding.progressbar.isVisible = true
    }

    override fun hideProgress() {
        binding.progressbar.isVisible = false

    }

    override fun setData(arrUpdates: List<Todo>) {
        // show data on UI
        todoFragment.setData(returnRandomTodo(arrUpdates))
        todoAdapter.todos = arrUpdates
    }

    override fun setDataError(strError: String) {
        // show error on UI
        Log.i("todoMVP",strError)
    }

    override fun onDestroy() {
        //Destroy View
        mainPresenter.onDestroy()
        super.onDestroy()
    }

    override fun passDataCom(idUser: String, title: String) {
        val bundle = Bundle()
        bundle.putString(ARG_PARAM1, idUser)
        bundle.putString(ARG_PARAM2, title)
        val transaction = this.supportFragmentManager.beginTransaction()
        svgFragment.arguments = bundle
        transaction.replace(binding.FragContainerViewTodo.id,svgFragment)
        transaction.commit()
    }

    override fun backFragmentTitle(title: String) {
        val bundle = Bundle()
        bundle.putString(ARG_PARAM2,title)
        todoFragment.arguments = bundle
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(binding.FragContainerViewTodo.id,todoFragment)
        transaction.commit()
    }

}