package com.candroid.quizapp.util

import com.candroid.quizapp.R

object Images {
val image= arrayOf(R.drawable.iconfinder_3209459_class_education_pencil_reler_stationary_icon_512px,R.drawable.iconfinder_3586373_book_learning_school_icon_512px,R.drawable.iconfinder_4213590_bell_doodle_education_line_school_icon_512px,R.drawable.iconfinder_4213592_doodle_education_letter_school_study_icon_512px,R.drawable.iconfinder_4213598_doodle_education_line_pen_pencil_icon_512px)
var currentImage=0
    fun getImage():Int{
        currentImage= (currentImage+1)% image.size
        return image[currentImage]
    }
}