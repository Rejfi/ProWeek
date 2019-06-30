package com.revolshen.proweek.fragments

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.revolshen.proweek.R
import com.revolshen.proweek.data.Task
import com.revolshen.proweek.data.TaskData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_task_fragment.*
import java.lang.Exception
import android.widget.Toast


class EditTaskFragment : Fragment() {

    private var editTask: Task? = null

     fun receivedTaskData(task: Task){

         editTitleTask.setText(task.text)
         editDescriptionTask.setText(task.description)
         editRatingBar.rating = task.priority.toFloat()

         val editTask = Task(task.text,
             task.description,
             null,
             null,
             task.priority)
         editTask.id = task.id
         this.editTask = editTask
     }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_task_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        editTask = null

        setNewTask.setOnClickListener {
            val task = Task(
                editTitleTask.text.toString(),
                editDescriptionTask.text.toString(),
                0,
                0,
                editRatingBar.rating.toInt())

            MyTaskFragment.taskViewModel.insert(task)
            activity?.viewPager?.currentItem = 0

            editTitleTask.setText("")
            editDescriptionTask.setText("")
            editRatingBar.rating = 0.0f
            editFloatMenu.close(true)
        }

        editCurrentTask.setOnClickListener {
            editTask?.text = editTitleTask.text.toString()
            editTask?.description = editDescriptionTask.text.toString()
            editTask?.priority = editRatingBar.rating.toInt()

            if(editTask != null){
                MyTaskFragment.taskViewModel.update(editTask!!)
                editTask = null
                activity?.viewPager?.currentItem = 0
                editTitleTask.setText("")
                editDescriptionTask.setText("")
                editRatingBar.rating = 0.0f
                editFloatMenu.close(true)

            }else{
                Toast.makeText(requireContext(),
                    "Nie można edytować nieistniejącej notatki",
                    Toast.LENGTH_SHORT).show()
            }

        TODO("Naprawić bugi związane z edytacją notatki. Można edytować nieistniejące notatki i " +
                "przesuwać okna aby edytować niestniejącą notatkę")
        }

        clearAllDetails.setOnClickListener {
            editTitleTask.setText("")
            editDescriptionTask.setText("")
            editRatingBar.rating = 0.0f
            editFloatMenu.close(true)
        }

    }

}