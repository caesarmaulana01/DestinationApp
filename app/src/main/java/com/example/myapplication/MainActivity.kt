package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvDestinations: RecyclerView
    private val list = ArrayList<Destination>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDestinations = findViewById(R.id.rv_destinations)
        rvDestinations.setHasFixedSize(true)

        list.addAll(getListDestinations())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                // Beralih ke AboutActivity
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListDestinations(): ArrayList<Destination> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataLocation = resources.getStringArray(R.array.data_location)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listDestination = ArrayList<Destination>()
        for (i in dataName.indices) {
            val destination = Destination(
                name = dataName[i],
                location = dataLocation[i],
                description = dataDescription[i],
                photo = dataPhoto.getResourceId(i, -1)
            )
            listDestination.add(destination)
        }
        dataPhoto.recycle()
        return listDestination
    }

    private fun showRecyclerList() {
        rvDestinations.layoutManager = LinearLayoutManager(this)
        val listDestinationAdapter = ListDestinationAdapter(list)
        rvDestinations.adapter = listDestinationAdapter

        listDestinationAdapter.setOnItemClickCallback(object : ListDestinationAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Destination) {
                // Beralih ke DetailDestinationActivity
                val intent = Intent(this@MainActivity, DetailDestinationActivity::class.java).apply {
                    putExtra("EXTRA_NAME", data.name)
                    putExtra("EXTRA_LOCATION", data.location)
                    putExtra("EXTRA_DESCRIPTION", data.description)
                    putExtra("EXTRA_PHOTO", data.photo)
                }
                startActivity(intent)
            }
        })
    }
}
