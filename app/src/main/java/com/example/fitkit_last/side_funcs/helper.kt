package com.example.fitkit_last.side_funcs

import com.google.firebase.auth.FirebaseAuth

lateinit var AUTH: FirebaseAuth

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
}