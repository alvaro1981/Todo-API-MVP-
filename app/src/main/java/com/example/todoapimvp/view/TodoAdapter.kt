package com.example.todoapimvp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapimvp.databinding.TodoItemBinding
import com.example.todoapimvp.model.Todo

class TodoAdapter(val setFragmentTitle:(Todo)->Unit)
    :RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    inner class TodoViewHolder( val binding:TodoItemBinding):RecyclerView.ViewHolder(binding.root)

    private val diffCallback= object: DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var todos:List<Todo>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }
    override fun getItemCount(): Int {
        return todos.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,
            false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            tvUserId.text = todo.userId.toString()
            IdText.text = todo.id.toString()
            cbDone.isChecked = todo.completed
            root.setOnClickListener{
                setFragmentTitle(todo)
            }
        }
    }
}