package com.candroid.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
mAuth= FirebaseAuth.getInstance()
textView5.setOnClickListener {
    val intent=Intent(this,SingInActivity::class.java)

startActivity(intent)
    finish()

}

        login.setOnClickListener {
    val email = email.text.toString()
    val password =password.text.toString()
if(email.isNotBlank()&&password.isNotBlank()){
    userLogin(email,password)

}



}


    }


fun userLogin(email:String,password:String){
    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
        if(it.isSuccessful){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    else{

            Toast.makeText(this,"wrong",Toast.LENGTH_SHORT).show()


        }

    }

}


}