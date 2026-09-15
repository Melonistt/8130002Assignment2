package com.example.s8130002Application2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.s8130002Application2.databinding.ItemCourseBinding
import com.example.s8130002Application2.models.TeachingItem
import com.squareup.picasso.Picasso

class CoursesAdapter(private val courses: List<TeachingItem>) :
    RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(course: TeachingItem) {
            binding.apply {
                courseTitleTextView.text = course.title
                courseCategoryTextView.text = course.category
                courseRatingTextView.text = course.rating.toString()
                courseRatingBar.rating = course.rating

                Picasso.get()
                    .load(course.imageUrl)
                    .placeholder(com.example.s8130002Application2.R.drawable.placeholder_course)
                    .into(courseImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount(): Int = courses.size
}
