package com.candroid.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.candroid.quizapp.databinding.ActivityGetStartedBinding
import com.google.firebase.auth.FirebaseAuth

class GetStartedActivity : AppCompatActivity() {
lateinit var binding:ActivityGetStartedBinding
lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
mAuth= FirebaseAuth.getInstance()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_get_started)
        binding.button.setOnClickListener {
redirect("Login")
        }
    if(mAuth.currentUser!=null){

        redirect("MainActivity")
    }


    }

    fun redirect(name:String){

        val intent=when(name){
            "Login"-> Intent(this,LoginActivity::class.java)
            "MainActivity"->Intent(this,MainActivity::class.java)
        else->{
            throw Exception("something wrong")
        }

        }
startActivity(intent)
        finish()

    }

    }
