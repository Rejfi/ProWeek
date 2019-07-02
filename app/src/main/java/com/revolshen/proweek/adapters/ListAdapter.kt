package com.revolshen.proweek.adapters

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.DragStartHelper
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.revolshen.proweek.R
import com.revolshen.proweek.data.Task
import kotlinx.android.synthetic.main.activity_main.view.*

class RecyclerAdapter : ListAdapter<Task, RecyclerAdapter.TaskViewHolder>(DIFF_CALLBACK) {


    private var listener: OnItemClickListener? = null

    companion object {
        val DIFF_CALLBACK = object : ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.text == newItem.text &&
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
        holder.taskCheckBox.isChecked = false


        holder.taskCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
             when(isChecked){
                 true -> {
                     holder.taskText.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                     notifyItemMoved(holder.adapterPosition, itemCount-1)
                 }
                 else -> {
                     holder.taskText.paintFlags = 0
                     notifyItemMoved(holder.adapterPosition, 0)
                 }
             }
        }

    }

    fun getTask(position: Int): Task {
        return getItem(position)
    }

    fun updateUI(){

    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(adapterPosition), adapterPosition)
                }
            }
        }

        val taskCheckBox: CheckBox = view.findViewById(R.id.taskCheckBox)!!
        val taskText: TextView = view.findViewById(R.id.taskText)!!
        val taskDate: TextView = view.findViewById(R.id.taskFinishDate)!!
        val taskPriority: TextView = view.findViewById(R.id.taskPriority)!!

    }

    interface OnItemClickListener {
        fun onItemClick(task: Task, fromPosition: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun deleteOnItemClickListener() {
        this.listener = null
    }


}


