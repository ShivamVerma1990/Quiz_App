package com.candroid.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.candroid.quizapp.module.Question
import com.candroid.quizapp.module.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
var quiz:MutableList<Quiz>?=null
    var question:MutableMap<String,Question>?=null
    var index=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
      firestore= FirebaseFirestore.getInstance()


getFirestoreData()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        previous.setOnClickListener {
            index--
            bindData()
        }

        next.setOnClickListener {
            index++
            bindData()
        }
        submit.setOnClickListener {
            Log.d("FINALQUIZ", question.toString())
        val intent=Intent(this,ResultActivity::class.java)
            val json= Gson().toJson(quiz!![0])
intent.putExtra("quizData",json)
            startActivity(intent)

        }
    }

    private fun getFirestoreData() {
        val date = intent.getStringExtra("DATE")

        if (date != null) {
            firestore.collection("quizshock").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if(!it.isEmpty&&it!=null) {
                        quiz=it.toObjects(Quiz::class.java)
                        question= quiz!![0].question
                  bindData()
                    }
                    }
        }

    }
    private fun bindData() {
        previous.visibility = View.GONE
        submit.visibility = View.GONE
        next.visibility = View.GONE

        if(index == 1){ //first question
            next.visibility = View.VISIBLE
        }
        else if(index == question!!.size) { // last question
            submit.visibility = View.VISIBLE
            previous.visibility = View.VISIBLE
        }
        else{ // Middle
            previous.visibility = View.VISIBLE
            next.visibility = View.VISIBLE
        }


        val questions= question!!["question$index"]
  questions?.let {
      description.text = it.description
      recyclerQuiz.layoutManager = LinearLayoutManager(this)
      recyclerQuiz.adapter = QuestionAdapter(this, questions)
      recyclerQuiz.setHasFixedSize(true)
  }
  }

}