package com.revolshen.proweek.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
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

    //private lateinit var taskViewModel: TaskViewModel
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

        recyclerView = view!!.findViewById(R.id.recyclerViewFragment)
        adapter = RecyclerAdapter()

        /*
        taskViewModel = ViewModelProviders.of(this)[TaskViewModel::class.java]
        */
        taskViewModel = activity!!.run {
            ViewModelProviders.of(this).get(TaskViewModel::class.java)
        }

        taskViewModel.getAllTasks().observe(this, Observer<List<Task>> {
            //Update recyclerView adapter to show current notes
            adapter.submitList(it)
        })
        //Float button menu and his buttons' actions
        newTask.setOnClickListener {
            val fragment = activity?.supportFragmentManager!!.fragments[1] as EditTaskFragment
            fragment.clearTextViews()
            activity?.viewPager?.currentItem = 1
            taskFloatMenu.close(true)
        }

        deleteTask.setOnClickListener {
            taskViewModel.deleteAllTask()
        }

        //Send data to other fragment after click task
        adapter.setOnItemClickListener(object : RecyclerAdapter.OnItemClickListener {
            override fun onItemClick(task: Task, fromPosition: Int) {
                taskViewModel.setEditTask(task)
                activity?.viewPager?.currentItem = 1
            }
        })

        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(0,swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                taskViewModel.delete(adapter.getTask(viewHolder.adapterPosition))
            }

        }).attachToRecyclerView(recyclerView)
    }

    override fun onStart() {
        super.onStart()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView.setHasFixedSize(true)
    }

}
