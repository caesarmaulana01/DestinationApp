package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val imgProfile: ImageView = findViewById(R.id.img_profile)
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvEmail: TextView = findViewById(R.id.tv_email)

        imgProfile.setImageResource(R.drawable.profile_picture)
        tvName.text = "T. Muhammad Caesar Maulana"
        tvEmail.text = "caesarm1611@gmail.com"

        // Menambahkan tombol kembali ke action bar
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "About"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // Kembali ke MainActivity saat tombol back di-klik
        onBackPressed()
        return true
    }
}
