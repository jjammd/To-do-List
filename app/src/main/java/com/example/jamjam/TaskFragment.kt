package com.example.jamjam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class TaskFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var taskList: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment layout (fragment_task.xml)
        val root = inflater.inflate(R.layout.fragment_mytask, container, false)

        // Initialize ListView
        listView = root.findViewById(R.id.listView)
        taskList = mutableListOf()
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, taskList)
        listView.adapter = adapter

        return root
    }

    // Function to let MainActivity add tasks
    fun addNewTask(task: String) {
        taskList.add(task)
        adapter.notifyDataSetChanged()
    }
}
