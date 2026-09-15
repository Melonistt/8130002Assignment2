package com.example.s8130002Application2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.s8130002Application2.databinding.ItemInstructorBinding
import com.example.s8130002Application2.models.Instructor

class InstructorsAdapter(private val instructors: MutableList<Instructor> = mutableListOf()) :
    RecyclerView.Adapter<InstructorsAdapter.InstructorViewHolder>() {

    inner class InstructorViewHolder(private val binding: ItemInstructorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(instructor: Instructor) {
            binding.instructorName.text = instructor.name
            binding.instructorSpecialty.text = instructor.specialty
            // Load image using Glide or Coil if you have it
            // Glide.with(binding.root).load(instructor.profileImage).into(binding.instructorImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructorViewHolder {
        val binding = ItemInstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InstructorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstructorViewHolder, position: Int) {
        holder.bind(instructors[position])
    }

    override fun getItemCount() = instructors.size

    fun updateList(newList: List<Instructor>) {
        instructors.clear()
        instructors.addAll(newList)
        notifyDataSetChanged()
    }
}
