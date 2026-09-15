package com.example.s8130002Application2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s8130002Application2.R
import com.example.s8130002Application2.models.Instructor
import com.squareup.picasso.Picasso


class InstructorsAdapter(private val instructors: List<Instructor>) :
    RecyclerView.Adapter<InstructorsAdapter.InstructorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_instructor, parent, false)
        return InstructorViewHolder(view)
    }

    override fun onBindViewHolder(holder: InstructorViewHolder, position: Int) {
        holder.bind(instructors[position])
    }

    override fun getItemCount() = instructors.size

    inner class InstructorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileImage: ImageView = itemView.findViewById(R.id.instructorImage)
        private val instructorName: TextView = itemView.findViewById(R.id.instructorName)
        private val followersText: TextView = itemView.findViewById(R.id.followersText)

        fun bind(instructor: Instructor) {
            Picasso.get().load(instructor.profileImageUrl).into(profileImage)
            instructorName.text = instructor.name
            followersText.text = "${instructor.followers} followers"
        }
    }
}
