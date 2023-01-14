package com.example.fitkit_last.side_funcs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

