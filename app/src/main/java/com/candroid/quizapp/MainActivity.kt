package com.candroid.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.candroid.quizapp.databinding.ActivityMainBinding
import com.candroid.quizapp.module.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var adapter: RecyclerAdapter
lateinit var firestore:FirebaseFirestore
    lateinit var binding: ActivityMainBinding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    var quizList = mutableListOf<Quiz>(
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        firestore=FirebaseFirestore.getInstance()
        setUpToolbar(topAppBar)



    }

    fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        setDrawer()
        setRecycler()
setFirestore()
    setDatePicker()
    }

    private fun setDatePicker() {

            btnDate.setOnClickListener {
                val datePicker= MaterialDatePicker.Builder.datePicker().build()
                datePicker.show(supportFragmentManager,"datePiker")
                datePicker.addOnPositiveButtonClickListener {
                    val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                    val date = dateFormatter.format(Date(it))


                                    val intent= Intent(this,QuizActivity::class.java)

                    intent.putExtra("DATE",date)

                    startActivity(intent)


                }
           datePicker.addOnNegativeButtonClickListener{
               Log.d("My",datePicker.headerText)


           }
            datePicker.addOnCancelListener {
                Log.d("My",datePicker.headerText)

            }

            }
        }

    private fun setFirestore() {
        val collectionData=firestore.collection("quizshock")
collectionData.addSnapshotListener { value, error ->
    if(value==null&&error!=null){
Toast.makeText(this,"something wrong",Toast.LENGTH_SHORT).show()
return@addSnapshotListener
}

    quizList.addAll((value!!.toObjects(Quiz::class.java)))
   adapter.notifyDataSetChanged()
}

    }


    private fun setDrawer() {
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        actionBarDrawerToggle.syncState()
        navigation.setNavigationItemSelectedListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            drawerLayout.closeDrawers()
            true
        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true

        }

        return super.onOptionsItemSelected(item)
    }

    private fun setRecycler() {
        adapter = RecyclerAdapter(this, quizList)
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(this, 2)

    }


}