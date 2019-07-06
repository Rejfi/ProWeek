package com.revolshen.proweek.fragments

import android.os.Bundle
import android.util.Log
import android.util.LogPrinter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.revolshen.proweek.R
import com.revolshen.proweek.data.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_task_fragment.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.revolshen.proweek.viewmodels.TaskViewModel


class EditTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_task_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        taskViewModel =  MyTaskFragment.taskViewModel// ViewModelProviders.of(this).get(TaskViewModel::class.java)
        taskViewModel.getEditTask().observe(this, Observer {

            editTitleTask.setText(it.text)
            editDescriptionTask.setText(it.description)
            editRatingBar.rating = it.priority.toFloat()
        })

        setNewTask.setOnClickListener {
            val task = Task(
                editTitleTask.text.toString(),
                editDescriptionTask.text.toString(),
                0,
                0,
                editRatingBar.rating.toInt())

            taskViewModel.insert(task)
            activity?.viewPager?.currentItem = 0
            clearTextViews()
        }

        editCurrentTask.setOnClickListener {
            val editTask = Task(
                editTitleTask.text.toString(),
                editDescriptionTask.text.toString(),
                0,
                0,
                editRatingBar.rating.toInt())
            editTask.id = taskViewModel.getEditTask().value!!.id

            taskViewModel.update(editTask)
            activity?.viewPager?.currentItem = 0
        }

        clearAllDetails.setOnClickListener { clearTextViews() }

    }

    override fun onResume() {
        super.onResume()

        Log.d("RESUME", taskViewModel.getEditTask().value?.text + "::" + taskViewModel.getEditTask().value?.description)
    }

    fun clearTextViews(){
        editTitleTask.setText("")
        editDescriptionTask.setText("")
        editRatingBar.rating = 0.0f
        editFloatMenu.close(true)
    }

}