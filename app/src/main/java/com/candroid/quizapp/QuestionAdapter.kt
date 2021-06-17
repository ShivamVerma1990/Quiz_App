package com.candroid.quizapp

import android.content.Context
import android.view.LayoutInflater
import com.candroid.quizapp.module.Quiz
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.candroid.quizapp.module.Question
import org.w3c.dom.Text

class QuestionAdapter(val context:Context,val question:Question) :RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>()
{

    val questions= listOf<String>(question.option1,question.option2,question.option3,question.option4)
inner class QuestionViewHolder(view:View):RecyclerView.ViewHolder(view)
{
var quizOption:TextView=view.findViewById(R.id.quiz_option)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
val view=LayoutInflater.from(parent.context).inflate(R.layout.option_layout,parent,false)
return QuestionViewHolder(view)
    }




    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {

   holder.quizOption.text=questions[position]
holder.quizOption.setOnClickListener{
question.userAnswer=questions[position]
    notifyDataSetChanged()
}
if(question.userAnswer==questions[position]){

    holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
}
else{

    holder.itemView.setBackgroundResource(R.drawable.option_item_bg)

}

    }

    override fun getItemCount(): Int {
return questions.size
    }


}