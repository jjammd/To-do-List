package com.example.jamjam

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.jamjam.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var taskList: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // ✅ Initialize ListView
        listView = findViewById(R.id.listView)
        taskList = mutableListOf()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        listView.adapter = adapter

        // ✅ Make FAB functional
        binding.fab.setOnClickListener {
            val input = android.widget.EditText(this)
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Add New Task")
                .setView(input)
                .setPositiveButton("Add") { _, _ ->
                    val task = input.text.toString()
                    if (task.isNotEmpty()) {
                        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
                        val taskFragment = navHost?.childFragmentManager?.fragments?.find { it is TaskFragment } as? TaskFragment
                        taskFragment?.addNewTask(task)
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }


        // Navigation setup
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_task),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun addNewTask() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add New Task")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("Add") { _, _ ->
            val task = input.text.toString()
            if (task.isNotEmpty()) {
                taskList.add(task)
                adapter.notifyDataSetChanged()
            }
        }

        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
