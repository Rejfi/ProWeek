package com.revolshen.proweek.fragments


import android.media.MediaRouter
import android.media.browse.MediaBrowser
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        taskViewModel = ViewModelProviders.of(this)[TaskViewModel::class.java]
        taskViewModel.getAllTasks().observe(this, Observer<List<Task>> {
            //Update recyclerView adapter to show current notes
            adapter.submitList(it)
        })

        //Float button menu and his buttons' actions
        newTask.setOnClickListener {
            activity?.viewPager?.currentItem = 1
            taskFloatMenu.close(true)
        }

        deleteTask.setOnClickListener { taskViewModel.deleteAllTask() }


        adapter.setOnItemClickListener(object : RecyclerAdapter.OnItemClickListener{
            override fun onItemClick(task: Task, fromPosition: Int) {
              //  adapter.notifyItemMoved(fromPosition, adapter.itemCount-1)

            }

        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
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
