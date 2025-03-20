package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailDestinationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_destination)

        // View bindings
        val imgDetailPhoto: ImageView = findViewById(R.id.img_detail_photo)
        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val btnShare: FloatingActionButton = findViewById(R.id.action_share)

        // Retrieve intent data
        val name = intent.getStringExtra("EXTRA_NAME") ?: "Unknown"
        val location = intent.getStringExtra("EXTRA_LOCATION") ?: "Unknown Location"
        val description = intent.getStringExtra("EXTRA_DESCRIPTION") ?: "No description available."
        val photo = intent.getIntExtra("EXTRA_PHOTO", -1)

        // Set data to views
        if (photo != -1) {
            imgDetailPhoto.setImageResource(photo)
        } else {
            imgDetailPhoto.setImageResource(R.drawable.circle_background) // Fallback image
        }
        tvDetailName.text = name
        tvDetailDescription.text = description

        // Menambahkan tombol kembali di Action Bar
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = name // Menampilkan nama destinasi di header
        }

        // Share functionality
        btnShare.setOnClickListener {
            val shareText = """
                Discover $name located at $location.
                Why you should visit:
                $description
            """.trimIndent()

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Check out this destination!")
                putExtra(Intent.EXTRA_TEXT, shareText)
            }

            try {
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            } catch (e: Exception) {
                Toast.makeText(this, "No app available to share the content.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
