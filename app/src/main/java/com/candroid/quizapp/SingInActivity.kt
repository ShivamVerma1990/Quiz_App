package com.candroid.quizapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.candroid.quizapp.databinding.ActivitySingInBinding
import com.google.firebase.auth.FirebaseAuth

class SingInActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var binding: ActivitySingInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sing_in)
        mAuth = FirebaseAuth.getInstance()
        binding.signIn.setOnClickListener {
            val email = binding.emailSing.text.toString()
            val password = binding.passawordSing.text.toString()
            if (email.isNotBlank() && password.isNotBlank()) {
                createUser(email, password)


            }
        }

    }


    private fun createUser(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {

                Toast.makeText(this, "successfully", Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(this, "successfully", Toast.LENGTH_SHORT).show()


            }
        }
    }
}