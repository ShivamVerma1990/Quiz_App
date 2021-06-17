package com.candroid.quizapp
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.candroid.quizapp.module.Quiz
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.candroid.quizapp.util.Images

class RecyclerAdapter(val context: Context,val quizList: MutableList<Quiz>):RecyclerView.Adapter<RecyclerAdapter.RecyclerViewAdapter>() {
inner class RecyclerViewAdapter(view:View):RecyclerView.ViewHolder(view)
{
    var txtTime:TextView=view.findViewById(R.id.txtTime)
    var card:CardView=view.findViewById(R.id.card)
var img:ImageView=view.findViewById(R.id.img)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter {
val view=LayoutInflater.from(parent.context).inflate(R.layout.quiz_layout,parent,false)
return RecyclerViewAdapter(view)

    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter, position: Int) {
val cool=quizList[position]
        holder.txtTime.text=cool.title
holder.img.setImageResource(Images.getImage())

        holder.card.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
    holder.txtTime.setOnClickListener {
        val intent= Intent(context,QuizActivity::class.java)
intent.putExtra("DATE",cool.title)
        context.startActivity(intent)

    }

    }

    override fun getItemCount(): Int {
return quizList.size

    }

}