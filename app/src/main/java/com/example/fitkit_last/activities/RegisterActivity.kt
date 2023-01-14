package com.example.fitkit_last.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fitkit_last.MainActivity
import com.example.fitkit_last.databinding.ActivityRegisterBinding
import com.example.fitkit_last.side_funcs.replaceActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerProcess()
    }

    private fun registerProcess() {
        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginNow.setOnClickListener {
            replaceActivity(LoginActivity())
        }
        binding.buttonRegister.setOnClickListener {
            val email = binding.editTextEmailRegister.text.toString()
            val password = binding.editTextPasswordRegister.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
                        replaceActivity(MainActivity())
                        RegisterActivity().finish()
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