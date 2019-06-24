package com.revolshen.proweek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.revolshen.proweek.R
import com.revolshen.proweek.data.Task

class RecyclerAdapter : ListAdapter<Task, RecyclerAdapter.TaskViewHolder>(DIFF_CALLBACK) {

    private lateinit var onClickItemListener: AdapterView.OnItemClickListener

    companion object{
        val DIFF_CALLBACK = object : ItemCallback<Task>(){
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return  oldItem.text == newItem.text &&
                        oldItem.description == newItem.description &&
                        oldItem.startDate == newItem.startDate &&
                        oldItem.finishDate == newItem.finishDate &&
                        oldItem.priority == newItem.priority
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(layoutInflater.inflate(R.layout.task_row, parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val task = getItem(holder.adapterPosition)
        holder.taskText.text = task.text
        holder.taskPriority.text = task.priority.toString()
        holder.taskDate.setText("Data")


        TODO("Zaimplementować onClick na poszczególnej notatce tak aby przenosiła wszystkie informacje do editTaskFragment")
    }


    inner class TaskViewHolder(view: View): RecyclerView.ViewHolder(view){


        val taskText: TextView = view.findViewById(R.id.taskText)!!
        val taskCheckBox: TextView = view.findViewById(R.id.taskCheckBox)!!
        val taskDate: TextView = view.findViewById(R.id.taskFinishDate)!!
        val taskPriority: TextView = view.findViewById(R.id.taskPriority)!!



    }


}


