package com.example.fitkit_last.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitkit_last.MainActivity
import com.example.fitkit_last.databinding.ActivityLoginBinding
import com.example.fitkit_last.side_funcs.replaceActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginProcess()
    }

    private fun loginProcess() {
        firebaseAuth = FirebaseAuth.getInstance()
        binding.registerNow.setOnClickListener {
            replaceActivity(RegisterActivity())
        }
        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmailLogin.text.toString()
            val password = binding.editTextPasswordLogin.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
                        replaceActivity(MainActivity())
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Fill all the fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
