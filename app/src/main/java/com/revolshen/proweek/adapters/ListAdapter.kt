package com.revolshen.proweek.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.*
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.revolshen.proweek.R
import com.revolshen.proweek.data.Task

class RecyclerAdapter : ListAdapter<Task, TaskViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(layoutInflater.inflate(R.layout.task_row, parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskText.text = getItem(holder.adapterPosition).text

    }

    companion object{
        val DIFF_CALLBACK = object : ItemCallback<Task>(){
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }

}


class TaskViewHolder(view: View): RecyclerView.ViewHolder(view){

    val taskText = view.findViewById<TextView>(R.id.taskText)
    val taskCheckBox = view.findViewById<TextView>(R.id.taskCheckBox)
    val taskDate = view.findViewById<TextView>(R.id.taskFinishDate)



}