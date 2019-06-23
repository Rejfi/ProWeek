package com.revolshen.proweek.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.revolshen.proweek.R
import com.revolshen.proweek.adapters.RecyclerAdapter
import com.revolshen.proweek.data.Task
import com.revolshen.proweek.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.my_tasks_fragment.*


class MyTaskFragment : Fragment() {

    companion object{
        lateinit var taskViewModel: TaskViewModel
    }
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_tasks_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = RecyclerAdapter()
        taskViewModel = ViewModelProviders.of(this)[TaskViewModel::class.java]
        taskViewModel.getAllTasks().observe(this, Observer<List<Task>> {
            //Update recyclerView adapter to show current notes
            adapter.submitList(it)
        })

        newTask.setOnClickListener {
            //taskViewModel.insert(Task("1","1", null, null, 2))
            activity?.viewPager?.currentItem = 1

        }

        deleteTask.setOnClickListener {
            taskViewModel.deleteAllTask()
        }
    }

    override fun onStart() {
        super.onStart()

        recyclerView = view!!.findViewById(R.id.recyclerViewFragment)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView.setHasFixedSize(true)

    }

}
