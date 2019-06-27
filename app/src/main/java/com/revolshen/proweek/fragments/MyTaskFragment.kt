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
import com.revolshen.proweek.fragments.MyTaskFragment.SendTask




class MyTaskFragment : Fragment(){

    interface SendTask {
        fun sendTaskData(task: Task)
    }

    private var sm: SendTask? = null

    companion object {
        lateinit var taskViewModel: TaskViewModel
        const val myPreferences = "MyPrefs"
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

        deleteTask.setOnClickListener {
            taskViewModel.deleteAllTask()
        }

        adapter.setOnItemClickListener(object : RecyclerAdapter.OnItemClickListener {
            override fun onItemClick(task: Task, fromPosition: Int) {
                sm?.sendTaskData(task)
                activity?.viewPager?.currentItem = 1
            }

        })

        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                taskViewModel.delete(adapter.getTask(viewHolder.adapterPosition))
            }


        }).attachToRecyclerView(recyclerView)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            sm = activity as SendTask?
        } catch (e: ClassCastException) {
            throw ClassCastException("Error in retrieving data. Please try again")
        }


    }

    override fun onStart() {
        super.onStart()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView.setHasFixedSize(true)

    }

}
