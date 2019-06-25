package com.revolshen.proweek.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.revolshen.proweek.R
import com.revolshen.proweek.data.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_task_fragment.*

class EditTaskFragment : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_task_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setNewTask.setOnClickListener {
            val task: Task = Task(
                editTitleTask.text.toString(),
                editDescriptionTask.text.toString(),
                0,
                0,
                editRatingBar.rating.toInt()
            )

            MyTaskFragment.taskViewModel.insert(task)
            activity?.viewPager?.currentItem = 0

            editTitleTask.setText("")
            editDescriptionTask.setText("")
            editRatingBar.rating = 0.0f
            editFloatMenu.close(true)
        }

        clearAllDetails.setOnClickListener {
            editTitleTask.setText("")
            editDescriptionTask.setText("")
            editRatingBar.rating = 0.0f
            editFloatMenu.close(true)
        }

    }

}